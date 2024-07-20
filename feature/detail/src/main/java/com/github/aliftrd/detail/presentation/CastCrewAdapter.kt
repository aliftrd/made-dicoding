package com.github.aliftrd.detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.aliftrd.core.databinding.CastCrewItemBinding
import com.github.aliftrd.core.domain.castcrew.model.CastCrew
import com.github.aliftrd.core.utils.ConstVar

class CastCrewAdapter : ListAdapter<CastCrew, CastCrewAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(private val binding: CastCrewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastCrew) {
            with(binding) {
                castCrewName.text = item.name
                if (item.profilePath.isNullOrEmpty()) {
                    castCrewImage.setImageDrawable(
                        AppCompatResources.getDrawable(
                            castCrewImage.context,
                            com.github.aliftrd.core.R.drawable.no_image
                        )
                    )
                } else {
                    castCrewImage.load(ConstVar.BASE_IMAGE_URL + item.profilePath)
                }
                castCrewJob.text = item.job
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: CastCrewItemBinding =
            CastCrewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        val itemView = holder.itemView
        val isLastItem = position == itemCount - 1
        val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
        params.marginEnd = if (!isLastItem) 18 else 0
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastCrew>() {
            override fun areItemsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem == newItem
            }
        }
    }
}