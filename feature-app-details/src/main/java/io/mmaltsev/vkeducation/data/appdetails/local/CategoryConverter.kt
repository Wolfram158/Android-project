package io.mmaltsev.vkeducation.data.appdetails.local

import androidx.room.TypeConverter
import ru.vk.common.domain.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): Category = Category.valueOf(categoryName)
}