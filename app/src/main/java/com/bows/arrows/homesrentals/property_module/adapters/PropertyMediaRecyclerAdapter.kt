package com.bows.arrows.homesrentals.property_module.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bows.arrows.homesrentals.R
import com.bumptech.glide.Glide

class PropertyMediaRecyclerAdapter(private val list: List<Uri>, private val context: Context) :
    RecyclerView.Adapter<PropertyMediaRecyclerAdapter.MediaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MediaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val image: Uri = list[position]
        holder.bind(image, context)
    }

    class MediaViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.media_recyler_item, parent, false)) {
        private var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.media_recycler_imageview)
        }

        fun bind(image: Uri, context: Context) {
            Glide.with(context)
                .load(image)
                .into(imageView!!)
        }
    }
}