package com.example.netclanexplorer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netclanexplorer.ui.theme.NetclanExplorerTheme

class RefineActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val onBackPressedDispatcher =
                LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

            NetclanExplorerTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Refine") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = Color.White
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    onBackPressedDispatcher?.onBackPressed()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                        modifier = Modifier.size(40.dp),
                                        contentDescription = "BackDrop",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding)
                            .padding(18.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        SelectAvailability()
                        Spacer(modifier = Modifier.height(16.dp))
                        AddStatus()
                        SelectDistance()
                        Spacer(modifier = Modifier.height(16.dp))
                        SelectPurpose()
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = {
                                context.startActivity(Intent(context, ExplorerActivity::class.java))
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(48.dp),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text(
                                text = "Save & Explore",
                                modifier = Modifier.padding(horizontal = 30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectAvailability() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Available | Hey Let Us Connect") }

    val options = listOf(
        "Available | Hey Let Us Connect",
        "Away | Stay Discrete And Watch",
        "Busy | Do not Disturb | will Catch Up Later",
        "SOS | Emergency! Need Help!"
    )

    Column {
        Text(text = "Select Your Availability", fontWeight = FontWeight.Bold)
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
            .clickable { expanded = true }
            .padding(vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Text(text = selectedOption)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(text = {
                        Text(text = option)
                    }, onClick = {
                        selectedOption = option
                        expanded = false
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStatus() {
    var status by remember { mutableStateOf("Hi community! I am open to new connections 😊") }

    Column {
        Text(text = "Add Your Status", fontWeight = FontWeight.Bold)
        TextField(
            minLines = 3,
            maxLines = 6,
            value = status,
            onValueChange = { status = it },
            modifier = Modifier
                .padding(vertical = 12.dp)
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(80.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Text(
            text = "${status.length}/250",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun SelectDistance() {
    var distance by remember { mutableStateOf(50f) }

    Column {
        Text(text = "Select Hyper local Distance", fontWeight = FontWeight.Bold)
        Slider(
            value = distance,
            onValueChange = { distance = it },
            valueRange = 1f..100f,
            steps = 98,
            modifier = Modifier
                .fillMaxWidth()
                .size(12.dp)
                .padding(vertical = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 20.dp)
        ) {
            Text(
                text = "1 Km",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = "100 Km",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectPurpose() {
    val purposes = listOf(
        "Coffee",
        "Business",
        "Hobbies",
        "Friendship",
        "Movies",
        "Dinning",
        "Dating",
        "Matrimony"
    )
    var selectedPurposes by remember { mutableStateOf(setOf<String>()) }

    Column {
        Text(text = "Select Purpose", fontWeight = FontWeight.Bold)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(90.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = 4
        ) {
            purposes.forEach { purpose ->
                PurposeChip(
                    purpose = purpose,
                    isSelected = selectedPurposes.contains(purpose),
                    onSelected = {
                        selectedPurposes = if (selectedPurposes.contains(purpose)) {
                            selectedPurposes - purpose
                        } else {
                            selectedPurposes + purpose
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PurposeChip(purpose: String, isSelected: Boolean, onSelected: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onSelected() }
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(40.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = purpose,
            fontSize = 13.sp,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAvail() {
    SelectAvailability()
}


@Preview(showBackground = true)
@Composable
fun PreviewStatus() {
    AddStatus()
}

@Preview(showBackground = true)
@Composable
fun PreviewDistance() {
    SelectDistance()
}

@Preview(showBackground = true)
@Composable
fun PreviewPurpose() {
    SelectPurpose()
}
