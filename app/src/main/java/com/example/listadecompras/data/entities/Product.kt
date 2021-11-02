package com.example.listadecompras.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = ["name"], unique = true)]
)
data class Product(@PrimaryKey(autoGenerate = true) val uid: Int = 0,
                   @ColumnInfo(name = "name") val name: String?,
                   @ColumnInfo(name = "quantity") val quantity: Int = 1,
                   @ColumnInfo(name = "price") val price: Double = 0.0): Parcelable{
    fun totalCost() = price * quantity
}