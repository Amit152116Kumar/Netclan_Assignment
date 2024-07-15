package com.example.netclanexplorer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class Profile(
    val name: String,
    val position: String,
    val location: String,
    val distance: String,
    val profileScore: Int,
    val tags: List<Purpose>,
) {
    val initials: String
        get() = name.split(" ").map { it.first() }.joinToString("")
}

sealed class Purpose(
    val tag: String,
    val color: Color,
    val icon: ImageVector
) {
    data object Coffee : Purpose("Coffee", Color(0xFFD2691E), Icons.Default.Coffee)
    data object Business : Purpose("Business", Color(0xFF4682B4), Icons.Default.Business)
    data object Hobbies : Purpose("Hobbies", Color(0xFF9ACD32), Icons.Outlined.Work)
    data object Friendship : Purpose("Friendship", Color(0xFFFFA500), Icons.Outlined.People)
    data object Movies : Purpose("Movies", Color(0xFF8B0000), Icons.Default.MovieCreation)
    data object Dining : Purpose("Dining", Color(0xFFFF5722), Icons.Filled.LocalDining)
    data object Dating : Purpose("Dating", Color(0xFFDB7093), Icons.Default.Favorite)
    data object Matrimony : Purpose("Matrimony", Color(0xFF4B0082), Icons.Default.CheckCircle)
}

val profileList = listOf(
    Profile(
        name = "Nikhil Garg",
        position = "Software Engineer",
        location = "Mumbai",
        distance = "10 KM",
        profileScore = 55,
        tags = listOf(
            Purpose.Coffee, Purpose.Business, Purpose.Friendship
        )
    ),
    Profile(
        name = "Amit Sharma",
        position = "Data Scientist",
        location = "Delhi",
        distance = "5 KM",
        profileScore = 65,
        tags = listOf(
            Purpose.Dining, Purpose.Hobbies, Purpose.Dating,Purpose.Business,Purpose.Friendship
        )
    ),
    Profile(
        name = "Priya Singh",
        position = "Product Manager",
        location = "Bangalore",
        distance = "20 KM",
        profileScore = 70,
        tags = listOf(
            Purpose.Matrimony, Purpose.Movies, Purpose.Business
        )
    ),
    Profile(
        name = "Rohit Verma",
        position = "Designer",
        location = "Hyderabad",
        distance = "15 KM",
        profileScore = 80,
        tags = listOf(
            Purpose.Coffee, Purpose.Hobbies, Purpose.Friendship,Purpose.Matrimony
        )
    ),
    Profile(
        name = "Anjali Mehta",
        position = "Marketing Specialist",
        location = "Chennai",
        distance = "12 KM",
        profileScore = 90,
        tags = listOf(
            Purpose.Dining, Purpose.Dating, Purpose.Movies
        )
    ),
    Profile(
        name = "Suresh Kumar",
        position = "Business Analyst",
        location = "Kolkata",
        distance = "8 KM",
        profileScore = 40,
        tags = listOf(
            Purpose.Coffee, Purpose.Matrimony, Purpose.Business,Purpose.Friendship
        )
    ),
    Profile(
        name = "Neha Gupta",
        position = "Developer Advocate",
        location = "Pune",
        distance = "18 KM",
        profileScore = 75,
        tags = listOf(
            Purpose.Friendship, Purpose.Dining, Purpose.Hobbies
        )
    ),
    Profile(
        name = "Ravi Patel",
        position = "Consultant",
        location = "Ahmedabad",
        distance = "22 KM",
        profileScore = 85,
        tags = listOf(
            Purpose.Dating, Purpose.Movies, Purpose.Business
        )
    ),
    Profile(
        name = "Sneha Reddy",
        position = "Project Manager",
        location = "Jaipur",
        distance = "7 KM",
        profileScore = 60,
        tags = listOf(
            Purpose.Coffee, Purpose.Friendship, Purpose.Matrimony
        )
    ),
    Profile(
        name = "Arjun Desai",
        position = "Quality Analyst",
        location = "Surat",
        distance = "3 KM",
        profileScore = 35,
        tags = listOf(
            Purpose.Dining, Purpose.Hobbies, Purpose.Business
        )
    )
)
