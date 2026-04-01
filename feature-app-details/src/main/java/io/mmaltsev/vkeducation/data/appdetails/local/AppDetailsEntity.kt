package io.mmaltsev.vkeducation.data.appdetails.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AppDatabase.APP_DETAILS_TABLE)
data class AppDetailsEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshots: String? = null,
    val description: String,
    val lastUpdated: Long = System.currentTimeMillis(),
    val isInWishlist: Boolean = false
)
