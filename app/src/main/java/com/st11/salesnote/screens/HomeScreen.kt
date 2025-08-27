package com.st11.salesnote.screens




import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import com.st11.salesnote.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.salesnote.navigation.Screen
import com.st11.salesnote.utils.DynamicStatusBar

import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.ShareAlt
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.raspberry)
    DynamicStatusBar(colorResource(id = R.color.white))
    // ✅ Define states for search
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val selectedWatchlistIds = remember { mutableStateOf<Set<String>>(emptySet()) }



    val context = LocalContext.current


    val books = listOf(
        Book("The Name of the Wind", 662, "Fiction", "Fantasy"),
        Book("Dune", 688, "Fiction", "Sci-Fi"),
        Book("Atomic Habits", 320, "Non-Fiction", "Self-Help"),
        Book("The Pragmatic Programmer", 352, "Non-Fiction", "Tech")
    )




    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearching) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    "Search...",
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                            },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                    } else {
                        Text("Home", color = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { isSearching = !isSearching }) {
                        Icon(
                            imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    if (!isSearching) {
//                    Icon(
//                        imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color.White
//                    )
                        IconButton(onClick = {
//                        isSearching = !isSearching
                            navController.navigate(Screen.Settings.route)
                        }) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Cog,
                                contentDescription = "settings",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor, // dark green
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },


        bottomBar = {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .background(colorResource(id = R.color.white))
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.AddToWatchlist.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Plus,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add To Watchlist",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }



        }

    ) { paddingValues ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.white))
        ) {


            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content


            // Title
            Text(
                text = "My Watchlist",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.raspberry)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "Do you have Tv shows or Books in your watchlist? add them now",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )


        }

    }
}

// ✅ Local book model + list
data class Book(
    val title: String,
    val pages: Int,
    val type: String,
    val genre: String
)


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}


@Composable
fun ActionItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
            .size(width = 100.dp, height = 70.dp), // uniform size for consistency
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // soft grey background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color(0xFF1976D2), // blue accent
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = text,
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontWeight = FontWeight.SemiBold
//                ),
                color = Color.Black
            )
        }
    }
}


