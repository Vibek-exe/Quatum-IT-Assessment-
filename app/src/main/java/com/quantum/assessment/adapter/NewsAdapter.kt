package com.quantum.assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quantum.assessment.R
import com.quantum.assessment.databinding.ItemBinding
import com.quantum.assessment.model.Articles

class NewsAdapter (var list: ArrayList<Articles>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var dataBinding: ItemBinding? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item, parent, false
        )
        return ViewHolder(dataBinding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Articles = list[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: ArrayList<Articles>) {
        this.list = data
        notifyDataSetChanged()
    }


    class ViewHolder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Articles) {

            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(binding.itemImage)

            binding.title.text = article.title
            binding.publishAt.text = article.publishedAt.toString()
            binding.itemDescription.text = article.description
        }
    }
}
