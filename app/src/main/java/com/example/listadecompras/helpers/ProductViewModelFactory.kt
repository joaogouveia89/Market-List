package com.example.listadecompras.helpers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.data.repositories.ProductRepository

class ProductViewModelFactory (
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(ProductRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("ProductViewModelFactory", e.message.toString())
        }
        return super.create(modelClass)
    }
}