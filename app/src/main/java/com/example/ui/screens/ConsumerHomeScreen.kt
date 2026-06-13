package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun ConsumerHomeScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToProduct: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text("Deliver to", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("New York 10001", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                        }
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = onNavigateToCart) {
                        BadgedBox(badge = { Badge { Text("3") } }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Mobile Search
            Box(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search products...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Default.Mic, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                )
            }

            // Promotional Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAD5JLVCvpnQuNHXfhLbUD_iwKfUlJLtMBASIT9SVtZIDrz8fQ-9rfv66mQim3NPEiyibui5YcE1oHFGr44wFJ4K1PEldN7T4vqUouxXf3V3xKIvbvem8xGTpayRdofDff3JjHbquWtN6cyQ7wheDP1M9vLk__rzI3LYMUR2nywEbI3fIX9lYqljUCzDgTWUuyhmGjsxksaXdq48teeID79hGo7IigB0vfuaQDXZMvomFc7K4r0Kbm_9_JORYv-x6T8tmxo-FgdFw6w",
                    contentDescription = "Sale",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Daily Deal",
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Up to 50% Off Electronics",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Upgrade your tech with our exclusive limited-time offers on top brands.",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text("Shop Now")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Categories
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Explore Categories", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                TextButton(onClick = { /*TODO*/ }) {
                    Text("See All")
                }
            }

            val categories = listOf<Pair<String, androidx.compose.ui.graphics.vector.ImageVector>>(
                "Electronics" to Icons.Default.Phone,
                "Fashion" to Icons.Default.Face,
                "Grocery" to Icons.Default.ShoppingCart,
                "Home" to Icons.Default.Home,
                "Beauty" to Icons.Default.Face,
                "Sports" to Icons.Default.PlayArrow,
                "Books" to Icons.Default.List,
                "Toys" to Icons.Default.Star
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .height(200.dp)
                    .padding(horizontal = 8.dp),
                userScrollEnabled = false
            ) {
                items(categories.size) { index ->
                    val cat = categories[index]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(cat.second, contentDescription = cat.first, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(cat.first, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            // Recommended
            Text(
                "Recommended for You",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                ProductCard(
                    title = "Premium Wireless Headphones",
                    subtitle = "Noise Cancelling, 30hr Battery",
                    price = "$299.00",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAB8XRDRSUqvQPN6bhuv7JbF0Bkw4gQco0JYwiFNG2YXqHMzE_2jz2I0XKxFTQIRzF82F8IxY41smrSuovKscHgSNC-ErHw3jlGNKfKnrISYMkPQFUMtIFabgHGgjTZIxkQ13J1uKg1cvr_8RjV_hz7sNez5I4zq_0ITNoa2Kx2wJb_p7ZuEMcsEO0jywevDYPKfMS-9VJwcmVOHH32U4SsEDPlwMosokwYwvWpPIJMNF6Xo_tLZvQ7KgnGFitxj4Sm422c4-A9rblg",
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToProduct
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProductCard(
                    title = "Minimalist Smart Watch",
                    subtitle = "Fitness tracking, Heart rate",
                    price = "$149.99",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAJPPB8rUqfPTnmpdnM-9TLSH46_nthX0DOc5rZ94McHqifM4PQV1SEeGFsuQNDK4CaQNAc1gv4rBj0PeRDvP1cQnqu_skKy4NBNqiThgZODd6ebjIl-mK8SsmMH4hxn-qgyrl22zYUSkXihPNUXcx74oL3k8u0yiBZet1Mfsl9SZipPTY24pqELYBTK2VxuZUI7aWBhnGxwWdEGKiVRcas7LKrdefmlMNFWcItOkwjS9t_s9PGvbQ7PARd0poyt0tUWdFMom_X5lEf",
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToProduct
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun ProductCard(
    title: String,
    subtitle: String,
    price: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(price, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(32.dp).background(MaterialTheme.colorScheme.primary, CircleShape)) {
                        Icon(Icons.Default.Add, contentDescription = "Add to cart", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
