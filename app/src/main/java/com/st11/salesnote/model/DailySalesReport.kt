package com.st11.salesnote.model

data class DailySalesReport(
    val date: String,
    val cash: Float,
    val bank: Float,
    val mpesa: Float,
    val other: Float,
    val total: Float
)
