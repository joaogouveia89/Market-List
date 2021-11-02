package com.example.listadecompras.data.daos

import androidx.room.*
import com.example.listadecompras.data.entities.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM product")
    suspend fun fetchProducts(): List<Product>

    @Query("DELETE FROM product")
    suspend fun cleanTable()

    @Delete
    suspend fun deleteProduct(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: Product)
}