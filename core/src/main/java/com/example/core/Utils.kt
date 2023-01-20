package com.example.movee

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(parser.parse(date) as Date)
}