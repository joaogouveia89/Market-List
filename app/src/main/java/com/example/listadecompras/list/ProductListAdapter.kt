package com.example.listadecompras.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product

class ProductListAdapter: RecyclerView.Adapter<ProductItemListViewHolder>() {
    private val productList = mutableListOf<Product>()

    fun updateList(list: List<Product>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_list, parent, false)
        return ProductItemListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemListViewHolder, position: Int) {
        holder.setProduct(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}