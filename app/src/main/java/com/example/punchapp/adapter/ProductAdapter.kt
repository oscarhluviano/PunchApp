package com.example.punchapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.punchapp.databinding.ProductElementBinding
import com.example.punchapp.databinding.PurchaseHistoryBinding
import com.example.punchapp.model.Product

class ProductAdapter  (private val context: Context, val products: ArrayList<Product>, val onItemListener: OnItemListener): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductElementBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    interface OnItemListener{
        fun miClick(game: Product)
    }

    class ViewHolder(binding: ProductElementBinding, onItemListener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding = binding
        private val onItemListener = onItemListener
        private lateinit var product: Product

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.miClick(product)
        }

        fun bindData(item: Product){
            with(binding){
                tvTitle.text = item.title
                //tvGenre.text = item.cost
            }
            product = item
        }
    }
}