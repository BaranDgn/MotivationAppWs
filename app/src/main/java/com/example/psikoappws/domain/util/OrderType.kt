package com.example.psikoappws.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
