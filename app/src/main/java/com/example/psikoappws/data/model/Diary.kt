package com.example.psikoappws.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.psikoappws.ui.theme.*

@Entity
data class Diary(
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val diaryColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            SeaFoam,
            DustyRose,
            BlueGray,
            Pewter,
            Yellow,
            Coral
        )
        val PageColors = listOf(

            LightGreen,
            BabyBlue,
            SeaFoam,
            DustyRose,
            BlueGray,
            Pewter,

        )
    }
}

class InvalidDiaryException(message: String): Exception(message)