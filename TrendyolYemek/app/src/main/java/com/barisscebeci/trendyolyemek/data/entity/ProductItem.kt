package com.barisscebeci.trendyolyemek.data.entity

data class ProductItem(
    val id: Int,
    val name: String,
    val description: String,
    val icon: Int,
    val iconStar: Int,
    val imageUrl: Int,
    val rating: Float,
    val ratingCount: Int,
    val deliveryTime: String,
    val distance: String,
    val price: String,
    val isFavorite: Boolean,
    val isClosed: Boolean,
    val tags: List<String>
) {
}

