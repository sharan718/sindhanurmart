package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartCheckoutScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OmniMarket", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Total", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("$712.00", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Proceed to Checkout")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text("Your Cart (2 Items)", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                CartItem(
                    title = "Enterprise Smartwatch Pro",
                    desc = "Space Grey / 44mm",
                    price = "$299.00",
                    img = "https://lh3.googleusercontent.com/aida-public/AB6AXuChyEPYi4_A9fThNvspoVkmmvRtImgdPbU7Waqu2NK4uB9aSCOgxZOKJeGvvJHYuDjYDowv9sirs7Imcb4z4pvQb5MWA5VUNQAJxcc-_LVCrs2kNx7s6UCteC9wWBF0TatiGr4hMIFPDDhJ2eJjmuSYj1bWD48UNIw3-dnkCtYZCz3nVJXII_5kZZw-DTHhzCJ67yiPGtktSRHKXLRE1nwtajPoIY0sYtw3uaNpsxSKIKld6Jlj2rgdHJ7VduWNF_pMFsc8wOrRbTNW"
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                CartItem(
                    title = "Noise-Cancelling Headphones",
                    desc = "Matte Black",
                    price = "$398.00",
                    img = "https://lh3.googleusercontent.com/aida-public/AB6AXuDWSnA1FOnx8nw2PoZ8FoqTcNn0i7I5jfop9REmkzieYQkXbOMIaLzjfrV2HlCCD6NqEv8LGbMtjnI9PMAiTqN-sr66Uj2T8GHfIKRhgWF20UUTYAZVspix2qLK5LzxP0XIQgubmcNjZa9LqzHnOSfKL9NGkMp_yJuJULfuhCK7HXpn0oHjSXSUpRx9UdfzBpEynxo0Yozky7-223AHBDK0RgIpnKAdsEU6o0cMksNnDpWZV7XdQh6DeFPfFdNxRHlcDtsmMWnvgeLF"
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text("Order Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Promo Code") },
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                    trailingIcon = { TextButton(onClick = {}) { Text("Apply") } },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal")
                    Text("$697.00", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Shipping")
                    Text("$15.00", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Discount", color = MaterialTheme.colorScheme.primary)
                    Text("-$0.00", color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun CartItem(title: String, desc: String, price: String, img: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.Delete, contentDescription = "Remove", tint = MaterialTheme.colorScheme.error)
                }
                Text(desc, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHigh
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {}, modifier = Modifier.size(24.dp)) { Icon(Icons.Default.Clear, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            Text("1", modifier = Modifier.padding(horizontal = 8.dp))
                            IconButton(onClick = {}, modifier = Modifier.size(24.dp)) { Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        }
                    }
                    Text(price, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
