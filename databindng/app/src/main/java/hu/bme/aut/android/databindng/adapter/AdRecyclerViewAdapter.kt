package hu.bme.aut.android.databindng.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import hu.bme.aut.android.databindng.model.Ad

import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.databindng.databinding.AdListItemBinding

class AdRecyclerViewAdapter : ListAdapter<Ad, AdRecyclerViewAdapter.ViewHolder>(itemCallback) {
    companion object {
        object itemCallback : DiffUtil.ItemCallback<Ad>() {
            override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var binding: AdListItemBinding;

    private var adList = emptyList<Ad>()

    var itemClickListener: TodoItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = AdListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ad = adList[position]

        holder.ad = ad
        holder.tvItemTitle.text = ad.title
        holder.tvItemLocation.text = ad.location
        holder.tvItemPrice.text = "${ad.price} Ft"
    }

    fun addItem(ad: Ad) {
        adList += ad
        submitList(adList)
    }

    fun addAll(ads: List<Ad>) {
        adList += ads
        submitList(adList)
    }

    fun deleteRow(position: Int) {
        adList = adList.filterIndexed { index, _ -> index != position }
        submitList(adList)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemTitle: TextView = binding.tvItemTitle
        val tvItemLocation: TextView = binding.tvItemLocation
        val tvItemPrice: TextView = binding.tvItemPrice

        var ad: Ad? = null

        init {
            itemView.setOnClickListener {
                ad?.let { itemClickListener?.onItemClick(it) }
            }
        }
    }

    interface TodoItemClickListener {
        fun onItemClick(ad: Ad)
    }
}
