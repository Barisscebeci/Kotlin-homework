package com.barisscebeci.trendyolyemek.data.repo

import com.barisscebeci.trendyolyemek.data.datasource.ProductItemDataSource
import com.barisscebeci.trendyolyemek.data.entity.ProductItem

class ProductItemRepository {
    private val productItemDataSource: ProductItemDataSource = ProductItemDataSource()
    fun getProductItems(): List<ProductItem> {
        return productItemDataSource.getProductItems()
    }

}