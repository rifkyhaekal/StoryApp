package com.albro.storyapp.stories

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.utils.NavigationHelper.DETAIL_EXTRA
import com.albro.storyapp.core.utils.NavigationHelper.DETAIL_ROUTE
import com.albro.storyapp.core.utils.invisible
import com.albro.storyapp.core.utils.mapToEntity
import com.albro.storyapp.core.utils.visible
import com.albro.storyapp.stories.adapter.LoadingStateAdapter
import com.albro.storyapp.stories.adapter.StoriesAdapter
import com.albro.storyapp.stories.databinding.FragmentStoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class StoriesFragment : Fragment(R.layout.fragment_stories) {

    private val binding: FragmentStoriesBinding by viewBinding()
    private val viewModel: StoriesViewModel by viewModels()
    private val adapter: StoriesAdapter = StoriesAdapter {story, imgStory,  name, description ->
        val intent = Intent(
            requireActivity(),
            Class.forName(DETAIL_ROUTE)
        )

        intent.putExtra(DETAIL_EXTRA, story.mapToEntity())

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            val toolbar = appBar.toolbar
            toolbar.title = getString(com.albro.storyapp.core.R.string.story_app)

            srlStory.setOnRefreshListener {
                getStories()
            }

            val linearLayoutManager = LinearLayoutManager(requireActivity())
            linearLayoutManager.scrollToPosition(0)
            rvStory?.setLayoutManager(linearLayoutManager)
            rvStory.addVeiledItems(5)

            getStories()
        }
    }

    private fun getStories() {
        binding.rvStory.setAdapter(adapter.withLoadStateFooter(footer = LoadingStateAdapter {
            adapter.retry()
        }))
        viewModel.tokenKey().observe(viewLifecycleOwner) { token ->
            lifecycleScope.launch {
                viewModel.getStories(token).observe(viewLifecycleOwner, ::manageAllStoriesResponse)
            }
        }
    }

    private fun manageAllStoriesResponse(response: PagingData<Story>) {
        adapter.submitData(lifecycle, response)

        adapter.addLoadStateListener { loadState ->
            with(binding) {
                if (loadState.mediator?.refresh is LoadState.Loading) {
                    rvStory.visible()
                    rvStory.veil()
                    llLayoutError.invisible()
                    srlStory.isRefreshing = false
                } else {
                    rvStory.unVeil()
                    srlStory.isRefreshing = false
                    val error = when {
                        loadState.mediator?.prepend is LoadState.Error ->
                            loadState.mediator?.prepend as LoadState.Error
                        loadState.mediator?.append is LoadState.Error ->
                            loadState.mediator?.append as LoadState.Error
                        loadState.mediator?.refresh is LoadState.Error ->
                            loadState.mediator?.refresh as LoadState.Error
                        else -> null
                    }

                    error?.let {
                        if (adapter.snapshot().isEmpty()) {
                            llLayoutError.visible()
                            rvStory.invisible()
                            tvErrorMessage.text = it.error.localizedMessage
                            btnTryAgain.setOnClickListener {
                                getStories()
                            }
                        }
                    }
                }
            }
        }
    }
}