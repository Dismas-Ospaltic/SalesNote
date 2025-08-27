package com.st11.salesnote.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.st11.salesnote.R
import com.st11.salesnote.navigation.Screen
import com.st11.salesnote.screens.components.SettingCard
import com.st11.salesnote.utils.DynamicStatusBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Lock
import androidx.core.net.toUri


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {

    val backgroundColor = colorResource(id = R.color.raspberry)
    DynamicStatusBar(backgroundColor) // ✅ Keeps status bar consistent
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = colorResource(id = R.color.raspberry)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState()) // ✅ Scrollable content
        ) {

            SettingCard(
                icon = FontAwesomeIcons.Solid.InfoCircle,
                title = "About",
                iconColor = colorResource(id = R.color.jet), // Green
                onClick = { /* Navigate */
                    navController.navigate(Screen.CreditAuthor.route)
                }
            )


            SettingCard(
                icon = FontAwesomeIcons.Solid.Lock,
                title = "Privacy policy",
                iconColor = colorResource(id = R.color.bittersweet), // Green
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://st11-homy.github.io/CompanionWatchlist/privacy-policy.html".toUri()
                    )
                    context.startActivity(intent)
                    /* Navigate */
                }
            )


        }
    }
}



@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}