package com.albro.storyapp.map_story

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.utils.Status
import com.albro.storyapp.core.utils.UiState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapStoryFragment : Fragment(R.layout.fragment_map_story), OnMapReadyCallback {

    private val viewModel: MapStoryViewModel by viewModels()
    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_story) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun setMapStyle(map: GoogleMap) {
        map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireActivity(),
                R.raw.map_style
            )
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        setMapStyle(map)
        getStoriesWithLocation()
    }

    private fun getStoriesWithLocation() {
        viewModel.tokenKey().observe(this) {
            viewModel.getStoriesWithLocation(it).observe(this, ::manageAllStoriesResponse)
        }
    }

    private fun manageAllStoriesResponse(response: UiState<ArrayList<Story>>) {
        val boundsBuilder = LatLngBounds.Builder()
        when (response.status) {
            Status.LOADING -> Unit

            Status.HIDE_LOADING -> Unit

            Status.SUCCESS -> {
                response.data?.forEach { story ->
                    val latLng = LatLng(story.lat as Double, story.lon as Double)
                    map.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title(story.name)
                            .icon(getMarkerIconFromDrawable(com.albro.storyapp.core.R.drawable.ic_person_marker))
                    )
                    boundsBuilder.include(latLng)
                }
                val bounds: LatLngBounds = boundsBuilder.build()
                map.animateCamera(
                    CameraUpdateFactory.newLatLngBounds(
                        bounds,
                        resources.displayMetrics.widthPixels,
                        resources.displayMetrics.heightPixels,
                        0
                    )
                )
            }

            Status.ERROR -> {
                ErrorBottomSheetDialogFragment(
                    response.message
                        ?: getString(com.albro.storyapp.core.R.string.something_went_wrong)
                ).show(parentFragmentManager, ErrorBottomSheetDialogFragment.TAG)
            }
        }
    }

    private fun getMarkerIconFromDrawable(@DrawableRes drawable: Int): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(resources, drawable, null)
            ?: return BitmapDescriptorFactory.defaultMarker()

        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}