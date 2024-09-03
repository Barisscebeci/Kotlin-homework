package com.barisscebeci.trendyolyemek.uix

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barisscebeci.trendyolyemek.R
import com.barisscebeci.trendyolyemek.data.entity.Category
import com.barisscebeci.trendyolyemek.data.repo.CategoryRepository
import com.barisscebeci.trendyolyemek.data.repo.ProductItemRepository
import com.barisscebeci.trendyolyemek.data.entity.ProductItem
import com.barisscebeci.trendyolyemek.uix.card.CategoryCard
import com.barisscebeci.trendyolyemek.uix.card.CouponCard
import com.barisscebeci.trendyolyemek.uix.card.ProductItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val repository = ProductItemRepository()
    val categoryRepository = CategoryRepository()

    var productItems by remember { mutableStateOf<List<ProductItem>>(emptyList()) }
    var category by remember { mutableStateOf<List<Category>>(emptyList()) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(key1 = true) {
        productItems = repository.getProductItems()
        category = categoryRepository.getCategory()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { /* Kapatma işlemi */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.kapa_resim),
                                    contentDescription = "Kapat",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                        Color.Black
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    ) // Dış kutucuğun padding'i
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.location_resim),
                                        contentDescription = "Konum",
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                            Color(0xFFFFA500)
                                        ) // Turuncu renk
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "ev (Kemalpaşa Mah)",
                                        fontSize = 12.sp,
                                        color = Color.Black
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_resim),
                                        contentDescription = "Aşağı",
                                        modifier = Modifier.size(16.dp),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                            Color.Black
                                        )
                                    )
                                }
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_asistan),
                                    contentDescription = "Asistan",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                        Color.Black
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            IconButton(onClick = { /* Kuponlar işlemi */ }) {
                                Box(contentAlignment = Alignment.TopEnd) {
                                    Image(
                                        painter = painterResource(id = R.drawable.coupon_resim),
                                        contentDescription = "Kuponlar",
                                        modifier = Modifier.size(24.dp),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                            Color.Black
                                        )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .background(Color.Red, shape = CircleShape)
                                            .align(Alignment.TopEnd),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "2",
                                            color = Color.White,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                actions = {}
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search_resim),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color(0xFFFFA500)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                if (searchQuery.text.isEmpty()) {
                                    Text(
                                        text = "Restoran, ürün veya mutfak ara",
                                        color = Color.Gray
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sirala_resim),
                            contentDescription = "Sırala",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFFFA500)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Sırala", color = Color.Black)
                    }

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_resim),
                            contentDescription = "Filtrele",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFFFA500)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Filtrele", color = Color.Black)
                    }
                }
                CouponCard()
                LazyRow(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(category) { category ->
                        CategoryCard(category = category)
                    }
                }
            }
            items(productItems) { productItem ->
                ProductItemCard(product = productItem)
            }
        }
    }
}