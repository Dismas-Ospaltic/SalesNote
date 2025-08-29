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
import androidx.compose.foundation.shape.CircleShape
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
import com.st11.salesnote.utils.formatDateToReadable
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.ShareAlt
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.raspberry)
    DynamicStatusBar(colorResource(id = R.color.white))
    // ✅ Define states for search
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf("") }



    val context = LocalContext.current

    val books = listOf(
        Book("The Name of the Wind", 662, "Fiction", "Fantasy"),
        Book("Dune", 688, "Fiction", "Sci-Fi"),
        Book("Atomic Habits", 320, "Non-Fiction", "Self-Help"),
        Book("The Pragmatic Programmer", 352, "Non-Fiction", "Tech")
    )




    Scaffold(
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

            // Fixed-position Back Button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .background(
                        color = colorResource(id = R.color.raspberry),
                        shape = CircleShape
                    )
                    .align(Alignment.Start)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content


            // Title
            Text(
                text = "Reports",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "View your Sales Reports here",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F4F7) // greyish
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    for (index in books.indices) {
                        val book = books[index] // Access each book

                        // Book row
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Handle click here if needed
                                }
                        ) {
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = book.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Spacer(Modifier.height(4.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Pages: ${book.pages}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Type: ${book.type}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Genre: ${book.genre}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(Modifier.height(4.dp))

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                IconButton(
                                    onClick = {
                                        selectedNotes = book.title
                                        showSheet = true
                                    },
                                    modifier = Modifier
                                        .size(56.dp) // total button size
                                        .clip(RoundedCornerShape(16.dp)) // round corners
                                ) {
                                    Icon(
                                        imageVector = FontAwesomeIcons.Solid.InfoCircle,
                                        contentDescription = "Info",
                                        tint = colorResource(id = R.color.bittersweet),
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }

                        // Divider except after last item
                        if (index < books.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = Color(0xFFE0E0E0)
                            )
                        }
                    }












                }
            }


        }
    }


    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Additional Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(selectedNotes)
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CircleNotch,
                            contentDescription = "update",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "update Progress", fontSize = 16.sp)
                    }

                    // Edit Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
//                                showEditDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Pen,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit", fontSize = 16.sp)
                    }


                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "Delete",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete", fontSize = 16.sp)
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {





                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ShareAlt,
                            contentDescription = "share",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Share Watchlist", fontSize = 16.sp)
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.ThumbsUp,
                            contentDescription = "Mark as complete",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Mark as complete", fontSize = 16.sp)
                    }
                }
//                Button(
//                    onClick = { showSheet = false },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Close")
//                }
            }
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
fun ReportsScreenPreview() {
    ReportsScreen(navController = rememberNavController())
}




