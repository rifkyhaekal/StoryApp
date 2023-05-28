package com.albro.storyapp.detail_story

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment
import com.albro.storyapp.core.common.ui.LoadingDialogFragment
import com.albro.storyapp.core.data.source.local.entity.StoryEntity
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.utils.NavigationHelper
import com.albro.storyapp.core.utils.Status
import com.albro.storyapp.core.utils.UiState
import com.albro.storyapp.core.utils.mapToDomain
import com.albro.storyapp.detail_story.databinding.ActivityDetailStoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DetailStoryActivity : AppCompatActivity() {

    private val binding: ActivityDetailStoryBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            val toolbar = appBar.toolbar
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.title = getString(com.albro.storyapp.core.R.string.detail)

            val storyIntent =
                intent.getParcelableExtra<StoryEntity>(NavigationHelper.DETAIL_EXTRA) as StoryEntity

            val story = storyIntent.mapToDomain()

            imgStory.load(story.photoUrl) {
                error(com.albro.storyapp.core.R.drawable.img_error)
                placeholder(com.albro.storyapp.core.R.drawable.ic_loading)
            }
            tvStoryName.text = story.name.capitalize()
            tvStoryDescription.text = story.description
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}