package com.example.listadecompras.list

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product

class ProductItemListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val name: TextView by lazy {
        view.findViewById(R.id.product_name)
    }
    private val totalPrice: TextView by lazy {
        view.findViewById(R.id.product_total_price)
    }

    fun setProduct(product: Product, onClicked: (p: Product) -> Unit){
        name.text = product.name
        totalPrice.text = view.resources.getString(R.string.price_placeholder, (product.price * product.quantity))
        view.setOnClickListener {
            onClicked.invoke(product)
        }
    }
}