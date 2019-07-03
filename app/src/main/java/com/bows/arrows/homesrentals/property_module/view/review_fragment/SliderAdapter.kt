package com.bows.arrows.homesrentals.property_module.view.review_fragment

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bows.arrows.homesrentals.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(var list: List<Any>) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.image_slider_layout, parent, false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        Glide.with(viewHolder!!.itemView)
            .load(list[position])
            .into(viewHolder.imageView)
    }

    override fun getCount(): Int {
        return list.size
    }

    class SliderAdapterVH(var itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.addPropertySliderImageView)
    }
}