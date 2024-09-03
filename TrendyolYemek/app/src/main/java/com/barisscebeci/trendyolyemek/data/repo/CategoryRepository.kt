package com.barisscebeci.trendyolyemek.data.repo

import com.barisscebeci.trendyolyemek.data.datasource.CategoryDataSource
import com.barisscebeci.trendyolyemek.data.entity.Category

class CategoryRepository {
    private val CategoryDataSource: CategoryDataSource = CategoryDataSource()
    fun getCategory(): List<Category> {
        return CategoryDataSource.getCategory()
    }

}