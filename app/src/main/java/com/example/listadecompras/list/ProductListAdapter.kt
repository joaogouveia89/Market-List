package com.example.listadecompras.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product

class ProductListAdapter: RecyclerView.Adapter<ProductItemListViewHolder>() {
    private val productList = mutableListOf<Product>()

    private val _selectedListItem = MutableLiveData<Product>()

    val selectedListItem: LiveData<Product>
        get() = _selectedListItem

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
        holder.setProduct(productList[position]){
            _selectedListItem.postValue(it)
        }
    }

    override fun getItemCount(): Int = productList.size
}