package com.example.appnews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.appnews.R
import com.example.appnews.models.New
import kotlinx.android.synthetic.main.item_new_everything.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val list: List<New>, var context: Context?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onclick: ((Int) -> Unit)? = null
    fun setOnClickItem(event: ((it: Int) -> Unit)?) {
        onclick = event
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(new: New) {
            val requestListener = object : RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("", "onLoadFailed: ${e?.message}")
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }
            Glide.with(context!!).asBitmap()
                .load(new.urlToImage)
                .listener(requestListener)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(itemView.imgNew)
            itemView.tvTitleNew.text = new.title
            itemView.tvDateNew.text = formatTime(new.publishedAt)
            itemView.tvDescriptionNew.text = new.description
            itemView.tvSourceName.text = new.source.name
        }

        @SuppressLint("SimpleDateFormat")
        private fun formatTime(time: String): String {
            val formatted = SimpleDateFormat("EEE MMM d hh:mm aaa",Locale.ENGLISH)
            val type = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            return formatted.format(type.parse(time))
        }
        init {
            itemView.setOnClickListener {
                onclick?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_new_everything, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun release() {
        context = null
    }
}