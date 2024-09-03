package com.barisscebeci.trendyolyemek.data.datasource

import com.barisscebeci.trendyolyemek.R
import com.barisscebeci.trendyolyemek.data.entity.Category

class CategoryDataSource {
    fun getCategory(): List<Category> {
        return listOf(
            Category(
                categoryId = 1,
                name = "Çiğ Köfte",
                imageUrl = R.drawable.cigkofte_resim
            ),
            Category(
                categoryId = 2,
                name = "Hamburger",
                imageUrl = R.drawable.hamburger_resim
            ),
            Category(
                categoryId = 3,
                name = "Pizza",
                imageUrl = R.drawable.pizza_resim
            ),
            Category(
                categoryId = 4,
                name = "Lahmacun - Pide",
                imageUrl = R.drawable.lahmacun_resim
            ),
            Category(
                categoryId = 5,
                name = "Tatlı",
                imageUrl = R.drawable.tatli_resim
            ),
            Category(
                categoryId = 6,
                name = "Döner",
                imageUrl = R.drawable.doner_resim
            )
        )
    }
}