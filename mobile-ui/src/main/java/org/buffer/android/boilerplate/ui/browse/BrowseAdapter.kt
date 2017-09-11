package org.buffer.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.buffer.android.boilerplate.ui.R
import org.buffer.android.boilerplate.ui.model.BufferooViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var bufferoos: List<BufferooViewModel> = emptyList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bufferoo = bufferoos[position]
        holder.nameText.text = bufferoo.name
        holder.titleText.text = bufferoo.title

        Glide.with(holder.itemView.context)
                .load(bufferoo.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_bufferoo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = bufferoos.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImage: ImageView = view.findViewById(R.id.image_avatar)
        val nameText: TextView = view.findViewById(R.id.text_name)
        val titleText: TextView = view.findViewById(R.id.text_title)

    }

}