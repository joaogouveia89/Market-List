package com.example.listadecompras.data.repositories

import com.example.listadecompras.data.database.ProductDatabase
import com.example.listadecompras.data.entities.Product

class ProductRepository(
    private val productDatabase: ProductDatabase
) {
    suspend fun fetchProducts() = productDatabase.getProductDao().fetchProducts()

    suspend fun insertProducts(products: List<Product>) = productDatabase.getProductDao().insertProducts(products)

    suspend fun cleanTable() = productDatabase.getProductDao().cleanTable()

    suspend fun deleteProduct(product: Product) = productDatabase.getProductDao().deleteProduct(product)

    suspend fun updateProduct(product: Product) = productDatabase.getProductDao().updateProduct(product)
}