package com.albro.storyapp.stories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.albro.storyapp.core.domain.models.Story
import com.albro.storyapp.core.utils.capitalize
import com.albro.storyapp.stories.databinding.ItemStoryBinding
import timber.log.Timber

class StoriesAdapter(val clickListener: (Story, ImageView, TextView, TextView) -> Unit) : PagingDataAdapter<Story, StoriesAdapter.StoriesAdapterViewHolder>(DIFF_CALLBACK) {

    inner class StoriesAdapterViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            with(binding) {
                tvStoryName.text = story.name.capitalize()
                tvStoryDescription.text = story.description
                imgStory.load(story.photoUrl) {
                    error(com.albro.storyapp.core.R.drawable.img_error)
                    placeholder(com.albro.storyapp.core.R.drawable.ic_loading)
                }

                root.setOnClickListener {
                    clickListener(story, imgStory, tvStoryName, tvStoryDescription)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesAdapterViewHolder {
        return StoriesAdapterViewHolder(
            ItemStoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoriesAdapterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }
        }
    }
}