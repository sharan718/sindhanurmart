package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToProduct: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OmniMarket", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                         Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search Input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "Wireless Headphones",
                    onValueChange = {},
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Default.Mic, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))) {
                    Icon(Icons.Default.Settings, contentDescription = "Filter")
                }
            }

            // Filter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    AssistChip(
                        onClick = { },
                        label = { Text("Price") },
                        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) }
                    )
                }
                item {
                    AssistChip(
                        onClick = { },
                        label = { Text("Rating") },
                        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) }
                    )
                }
                item {
                    AssistChip(
                        onClick = { },
                        label = { Text("Brand") },
                        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) }
                    )
                }
            }

            Text(
                text = "Showing 1-12 of 145 results",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Results Grid
            val products = listOf(
                ProductItem("Sony WH-1000XM5 Noise Canceling...", "$348.00", "4.8", "https://lh3.googleusercontent.com/aida-public/AB6AXuBM3lBRE6uwAVUuD9JYIWElyy1xEQmKcMlATGlV4Hc0-p1dSMRU9XzebOi5sCHkjQ72Mac7u1e7QqVhX2YleEFej_r1L5WGJ9xxppxH3PYrx60csW3ZO9VJ3alZQQI7pqAQ19WhWztvY2xeH9xBxqc1Apr959V2bmv7mNRN63GkJPmRrPwciCoIQM32ZYShB4pIElevBSw2Ljsv54H5ShWYeLiVVuXagK9mbhVuIwmnScltUWKhnul7C4_NMu593lnUHVODJjCWql-S"),
                ProductItem("Bose QuietComfort 45 Wireless...", "$279.00", "4.5", "https://lh3.googleusercontent.com/aida-public/AB6AXuCjKr2MloQQIto8z1W0WT88vkOrForWRj_b2z8OpeVSVLQRuEF0P_OoGkTTpSGg_qmICo90C02VUxSBhJROIgQ3Y3FqX1AnU5aHGmivi3Yq4D9kNGBpcCXr3amPrMbKppruEeWQR6G-iPMoXc_iOPDD8CDSUhboyrZoaFcE0XLL3o-WDyP0rL2L395_ggJdUTiyXiKBNpLcerXixz5mJGFrKB28ToCoyIfapDZoKNrVpEdt-cemA3KQYR_Fe6yYFbq7R4LtFRg3Y7Bi"),
                ProductItem("Audio-Technica ATH-M50xBT2...", "$199.00", "4.2", "https://lh3.googleusercontent.com/aida-public/AB6AXuDxZ0-1-uO2i-Y-I_yUov89Rc_POMtenml5FqJIRKf1_YEKAHeA5sc-W6Or3JMUXZHv5vohRDBYTr8W4SBCyjy4CZYAjzS1sVLfwujipdvyJNDVCAghJYX215qon9umn5WGtl5j-QdyBxkAbjdTmrsxq679Jv5m7YIO8dvhPLqwNniewNEVGONvhRCO4BhH-CONHWmQUG1kdKODdkUfx-L067w95mr8twSwSLr7baQAzvKw_D0EQ0nCEtQ1BhYP2hOZVhdBqHopyi9k"),
                ProductItem("Apple AirPods Pro (2nd Gen)", "$249.00", "4.6", "https://lh3.googleusercontent.com/aida-public/AB6AXuCUlibYUWXw0YiZrSsIelAMjqTq6rBXQKRle1y3lzEk8POrBwQGbg-MBAkzwXQeFdNYUQ8nG8V8Vh-AfKrZ1pJgn7aeP9jsv2uw-MnRVz-f-Fak1aImBryA3NsCz2E2ZY0ppXWVZgNwCSxIfODOuPvpj9iv9HiHNytydWqWVYRX2dyZzRT1sKU9Ph85XjGQBk3Nl9YSzfVJ-OAVbee_GJYi7bL_4lOlyqDPl-kMsav1p65h9X70uXB-fKS4kJVDvZ5y9B_UuflnKUwJ")
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products.size) { index ->
                    val prod = products[index]
                    SearchResultCard(prod, onClick = onNavigateToProduct)
                }
            }
        }
    }
}

data class ProductItem(val title: String, val price: String, val rating: String, val img: String)

@Composable
fun SearchResultCard(item: ProductItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                AsyncImage(
                    model = item.img,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFEAB308), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(item.rating, style = MaterialTheme.typography.labelSmall)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(item.title, style = MaterialTheme.typography.labelMedium, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.height(36.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(item.price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}
