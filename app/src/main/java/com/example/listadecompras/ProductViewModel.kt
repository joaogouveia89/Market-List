package com.example.listadecompras

import android.util.Log
import androidx.lifecycle.*
import com.example.listadecompras.data.entities.Product
import com.example.listadecompras.data.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    private var productsLock = Mutex()
    private var _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    fun fetchProducts(){
        viewModelScope.launch {
            productsLock.withLock {
                _products.postValue(productRepository.fetchProducts())
            }
        }
    }

    fun insertProducts(newProducts: List<Product>){
        viewModelScope.launch {
            productsLock.withLock {
                productRepository.insertProducts(newProducts)
            }
        }
    }

    fun cleanTable(){
        viewModelScope.launch {
            productsLock.withLock {
                productRepository.cleanTable()
                _products.postValue(emptyList())
            }
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            productsLock.withLock {
                withContext(Dispatchers.IO){
                    productRepository.deleteProduct(product)
                }
            }
        }
    }

    fun updateProduct(product: Product){
        viewModelScope.launch {
            productsLock.withLock {
                withContext(Dispatchers.IO){
                    productRepository.updateProduct(product)
                }
            }
        }
    }
}