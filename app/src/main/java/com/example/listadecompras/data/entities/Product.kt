package com.example.listadecompras.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class Product(@PrimaryKey(autoGenerate = true) val uid: Int = 0,
                   @ColumnInfo(name = "name") val name: String?,
                   @ColumnInfo(name = "quantity") val quantity: Int = 1,
                   @ColumnInfo(name = "price") val price: Double = 0.0){
    fun totalCost() = price * quantity
}