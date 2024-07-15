package com.example.netclanexplorer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netclanexplorer.ui.theme.NetclanExplorerTheme

class ExplorerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetclanExplorerTheme {
                ExplorerScreen()
            }
        }
    }
}

@Composable
fun ExplorerScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TabLayout()
                WorkingScreen()
            }
        },
        topBar = { CustomTopAppBar() },
        bottomBar = { CustomBottomAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(50)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Howdy Amit Kumar !!",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Current Location",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                    Text(text = " Old Fazilka, Abohar", fontSize = 13.sp, color = Color.White)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp),
                    contentDescription = "Navigation Menu"
                )
            }
        },
        actions = {
            Button(
                onClick = {
                    context.startActivity(Intent(context, RefineActivity::class.java))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refine),
                        contentDescription = "Refine Results",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Refine",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E2E43),
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun SingleBottomAppBar(
    icon: Painter,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val color = if (isSelected) Color(0xFF1D506B) else Color(0xFF748E9F)
        IconButton(onClick = onClick) {
            Icon(
                painter = icon,
                contentDescription = description,
                modifier = Modifier.size(30.dp),
                tint = color
            )
        }
        Text(
            text = description,
            fontSize = 13.sp,
            fontWeight = FontWeight.ExtraBold,
            color = color
        )
    }
}

@Composable
fun CustomBottomAppBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val icons = listOf(
        R.drawable.eye,
        R.drawable.connection,
        R.drawable.chat,
        R.drawable.contacts,
        R.drawable.hash
    )
    val descriptions = listOf("Explore", "Connections", "Chat", "Contacts", "Groups")

    BottomAppBar {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            icons.forEachIndexed { index, icon ->
                SingleBottomAppBar(
                    icon = painterResource(id = icon),
                    description = descriptions[index],
                    isSelected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}

@Composable
fun TabLayout() {
    val tabItems = listOf("Personal", "Service", "Businesses")
    var tabIndex by remember { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = tabIndex,
        containerColor = Color(0xFF143D59),
        contentColor = Color.White
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = index == tabIndex,
                onClick = { tabIndex = index },
                text = {
                    Text(text = item, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            )
        }
    }
}

@Composable
fun WorkingScreen() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            SearchBar()
            Icon(
                painter = painterResource(id = R.drawable.filter),
                modifier = Modifier
                    .rotate(90f)
                    .scale(-1f, 1f)
                    .size(40.dp),
                contentDescription = "Filter"
            )
        }
        LazyColumn {
            items(profileList.size) { index ->
                UserCard(profile = profileList[index])
            }
        }
    }
}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = { query = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
            disabledLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Bar",
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        placeholder = {
            Text(
                text = "Search ...",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        ),
        singleLine = true,
        modifier = Modifier
            .size(280.dp, 50.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(12.dp)
            )
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserCard(profile: Profile) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedCard(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .padding(start = 35.dp, top = 8.dp),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.secondary)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(45.dp))
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = profile.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "+ INVITE",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${profile.location} | ${profile.position}",
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Within ${profile.distance}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            LinearProgressIndicator(
                                progress = profile.profileScore.toFloat() / 100,
                                modifier = Modifier
                                    .padding(end = 13.dp)
                                    .weight(0.5f)
                                    .size(5.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                color = Color.Gray,
                                trackColor = Color.LightGray
                            )
                            Text(
                                text = "Profile Score - ${profile.profileScore}%",
                                modifier = Modifier.weight(1f),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    profile.tags.forEach { tag ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = tag.icon,
                                contentDescription = "",
                                modifier = Modifier.size(15.dp),
                                tint = tag.color
                            )
                            Text(
                                text = tag.tag,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                color = tag.color
                            )
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Hi community! I am open to new connections 😊",
                        fontSize = 13.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                }
            }
        }
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = Color(0xFFB8C5CD), shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.initials,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D506B)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar() {
    NetclanExplorerTheme {
        CustomTopAppBar()
    }
}

@Preview(showBackground = true)
@Composable
fun TabLayoutPreview() {
    NetclanExplorerTheme {
        TabLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBar() {
    NetclanExplorerTheme {
        CustomBottomAppBar()
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    NetclanExplorerTheme {
        WorkingScreen()
    }
}
