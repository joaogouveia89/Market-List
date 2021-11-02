package com.example.listadecompras.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listadecompras.data.daos.ProductDao
import com.example.listadecompras.data.entities.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object{
        private const val DB_NAME = "product_database.db"
        @Volatile private var instance: ProductDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            DB_NAME
        ).build()
    }
}