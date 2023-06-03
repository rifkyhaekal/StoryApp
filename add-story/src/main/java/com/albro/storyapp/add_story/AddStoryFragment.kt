package com.albro.storyapp.add_story

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.activity.viewBinding
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.albro.storyapp.add_story.databinding.FragmentAddStoryBinding
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment
import com.albro.storyapp.core.common.ui.LoadingDialogFragment
import com.albro.storyapp.core.domain.models.UploadStory
import com.albro.storyapp.core.utils.Status
import com.albro.storyapp.core.utils.UiState
import com.albro.storyapp.core.utils.createCustomTempFile
import com.albro.storyapp.core.utils.reduceFileImage
import com.albro.storyapp.core.utils.rotateBitmap
import com.albro.storyapp.core.utils.uriToFile
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shashank.sony.fancytoastlib.FancyToast
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale

@AndroidEntryPoint
class AddStoryFragment : Fragment(R.layout.fragment_add_story), EasyPermissions.PermissionCallbacks {

    private val binding: FragmentAddStoryBinding by viewBinding()
    private val viewModel: AddStoryViewModel by viewModels()
    private val loadingFragment: LoadingDialogFragment by lazy {
        LoadingDialogFragment()
    }
    private lateinit var launcherIntentCamera: ActivityResultLauncher<Intent>
    private lateinit var launcherIntentGallery: ActivityResultLauncher<Intent>
    private lateinit var currentPhotoPath: String
    private var myImgStory: File? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var lat: Double? = null
    private var lng: Double? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        initCameraIntent()
        initGalleryIntent()
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnCamera.setOnClickListener {
                if (hasCameraPermission()) {
                    startCamera()
                } else {
                    requestCameraPermission()
                }
            }

            btnGallery.setOnClickListener {
                if (hasStoragePermission()) {
                    startGallery()
                } else {
                    requestStoragePermission()
                }
            }

            btnLocateMe.setOnClickListener {
                if (hasLocationPermission()) {
                    getLocation()
                } else {
                    requestLocationPermission()
                }
            }

            edAddDescription.doAfterTextChanged {
                viewModel.descriptionValue.value = it.toString()
            }

            btnUpload.setOnClickListener {
                val file = reduceFileImage(myImgStory as File)
                uploadStory(edAddDescription.text.toString(), file)
            }

            lifecycleScope.launch {
                viewModel.isUploadButtonEnable.collect {
                    btnUpload.isEnabled = it
                }
            }
        }
    }

    private fun initCameraIntent() {
        launcherIntentCamera = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val myFile = File(currentPhotoPath)
                val bitmapResult = rotateBitmap(
                    BitmapFactory.decodeFile(myFile.path),
                    true
                )

                viewModel.isImagePicked.value = true
                myImgStory = myFile
                binding.imgStory.setImageBitmap(bitmapResult)
            } else {
                binding.imgStory.setImageResource(com.albro.storyapp.core.R.drawable.ic_loading)
            }
        }
    }

    private fun initGalleryIntent() {
        launcherIntentGallery = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImg: Uri = result.data?.data as Uri
                myImgStory = uriToFile(selectedImg, requireContext())
                viewModel.isImagePicked.value = true
                binding.imgStory.setImageURI(selectedImg)
            } else {
                binding.imgStory.setImageResource(com.albro.storyapp.core.R.drawable.ic_loading)
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)
        createCustomTempFile(requireActivity().application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.albro.storyapp",
                it
            )

            currentPhotoPath = it.absolutePath
            myImgStory = File(currentPhotoPath)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        FancyToast.makeText(
            requireContext(),
            getString(R.string.permission_granted),
            FancyToast.LENGTH_SHORT,
            FancyToast.SUCCESS,
            false
        ).show()
    }

    private fun hasCameraPermission() =
        EasyPermissions.hasPermissions(requireContext(), Manifest.permission.CAMERA)

    private fun requestCameraPermission() {
        if (!hasCameraPermission()) {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_storage_request),
                REQUEST_CAMERA_PERMISSION,
                Manifest.permission.CAMERA
            )
        }
    }

    private fun requestStoragePermission() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.permission_storage_request),
            REQUEST_STORAGE_PERMISSION,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun hasStoragePermission() =
        EasyPermissions.hasPermissions(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)

    private fun uploadStory(description: String, img: File) {
        viewModel.tokenKey().observe(requireActivity()) {
            viewModel.uploadStory(it, description, img, lat, lng).observe(
                requireActivity(),
                ::manageUploadStoryResponse
            )
        }
    }

    private fun manageUploadStoryResponse(uiState: UiState<UploadStory>) {
        when (uiState.status) {
            Status.LOADING -> {
                loadingFragment.show(parentFragmentManager, LoadingDialogFragment.TAG)
            }
            Status.HIDE_LOADING -> {
                loadingFragment.dismiss()
            }
            Status.SUCCESS -> {
                FancyToast.makeText(
                    requireActivity(),
                    getString(R.string.upload_story_success),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.SUCCESS,
                    false
                ).show()
                binding.edAddDescription.text?.clear()
                navigateToStoriesFragment()
            }
            Status.ERROR -> {
                ErrorBottomSheetDialogFragment(
                    uiState.message ?: getString(com.albro.storyapp.core.R.string.something_went_wrong)
                ).show(parentFragmentManager, ErrorBottomSheetDialogFragment.TAG)
            }
        }
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.permission_location_request),
            REQUEST_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                lat = location.latitude
                lng = location.longitude
                binding.tvLatLon.text = getAddress(lat as Double, lng as Double)
            }
        }.addOnFailureListener {
            FancyToast.makeText(
                requireContext(),
                it.message,
                FancyToast.LENGTH_SHORT,
                FancyToast.ERROR,
                false
            )
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        val myGeoCoder = Geocoder(requireActivity(), Locale.getDefault())
        val getAddress = myGeoCoder.getFromLocation(latitude, longitude, 1)
        return getAddress?.get(0)?.getAddressLine(0) ?: "Unknown"
    }

    private fun navigateToStoriesFragment() {
        findNavController().navigateUp()
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 1
        private const val REQUEST_STORAGE_PERMISSION = 2
        private const val REQUEST_LOCATION_PERMISSION = 3
    }
}