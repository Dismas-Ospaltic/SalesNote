package com.st11.salesnote.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.st11.salesnote.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.salesnote.utils.DynamicStatusBar
import com.st11.salesnote.viewmodel.SingleSaleViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
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
    var selectedNotes by remember { mutableStateOf("") }

    val singleSaleViewModel: SingleSaleViewModel = koinViewModel()
   val dailyReports = singleSaleViewModel.dailySalesReports.collectAsState()





    val context = LocalContext.current

    val reports = listOf(
        Report("2025-08-01", 620, 680, 590, 710, 4800f),
        Report("2025-08-02", 650, 710, 605, 720, 4950f),
        Report("2025-08-03", 670, 725, 615, 730, 5100f),
        Report("2025-08-04", 640, 690, 600, 700, 4700f),
        Report("2025-08-05", 680, 740, 620, 740, 5200f),
        Report("2025-08-06", 660, 715, 610, 720, 5050f),
        Report("2025-08-07", 675, 735, 625, 735, 5150f),
        Report("2025-08-08", 662, 700, 600, 700, 5000f),
        Report("2025-08-09", 685, 750, 630, 750, 5300f),
        Report("2025-08-10", 695, 760, 640, 760, 5400f)
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
//                    for (index in reports.indices) {
//                        val report = reports[index] // Access each book

                    if (dailyReports.value.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.shop), // Replace with your image in res/drawable
                                    contentDescription = "No Data",
                                    modifier = Modifier.size(120.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "No data Sales available!",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }else{


                    for (index in dailyReports.value.indices) {
                        val report = dailyReports.value[index] // Access each book

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
                                text = report.date,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = "Totals Sales: ${report.total.toString()}",
                            )
                            Spacer(Modifier.height(4.dp))

//                            Row(
//                                Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
                            Text(
                                "Cash Payments: ${report.cash}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Bank Payments: ${report.bank}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "M-pesa payments: ${report.mpesa}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                "Other payments: ${report.other}",
                                style = MaterialTheme.typography.bodyMedium
                            )
//                            }
                            Spacer(Modifier.height(4.dp))

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                IconButton(
                                    onClick = {
                                        selectedNotes = report.date
//                                        showSheet = true
                                        navController.navigate("singleSalesReport/${report.date}")
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
                        if (index < reports.lastIndex) {
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
    }



}



// ✅ Local book model + list
data class Report(
    val date: String,
    val cash: Int,
    val bank: Int,
    val mpesa: Int,
    val other: Int,
    val total: Float
)


@Preview(showBackground = true)
@Composable
fun ReportsScreenPreview() {
    ReportsScreen(navController = rememberNavController())
}




