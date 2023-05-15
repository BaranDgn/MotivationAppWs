package com.example.psikoappws.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteRoom(
    val text : String,
    val author : String,
    @PrimaryKey val id: Int ? = null
)