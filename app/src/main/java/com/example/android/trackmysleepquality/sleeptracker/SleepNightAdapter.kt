/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

// TODO (04) Create variables for Header and SleepNight item types.

// TODO (03) In ListAdapter<>, replace SleepNight with DataItem
// and SleepNightAdapter.ViewHolder with RecyclerView.ViewHolder.
class SleepNightAdapter(val clickListener: SleepNightListener) : ListAdapter<SleepNight,
        SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    // TODO (09) Define a CoroutineScope with Dispatchers.Default.

    // TODO (10) Create a new addHeaderAndSubmitList() function, and using the coroutine
    // you defined above, convert a List<SleepNight> to a List<DataItem>,
    // then submit the list to the adapter on the main thread.

    // TODO (06) Change onBindViewHolder to take a RecyclerView.ViewHolder,
    // and change the item from a SleepNight to a DataItem.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener,item)
    }

    // TODO (05) Change onCreateViewHolder's return type to RecyclerView.ViewHolder,
    // and update it to return the correct ITEM_VIEW_TYPE.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // TODO (02) Copy and paste the TextViewHolder class from the exercise.

    // TODO (07) override getItemViewType() and check whether the item is a Header or a SleepNight.

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: SleepNightListener, item: SleepNight) {
            binding.sleep = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
// TODO (08) Update DiffCallback to compare DataItem instead SleepNight objects.
class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}

// TODO (01) Add a sealed class called DataItem,
// containing a SleepNightItem data class, and a Header object.