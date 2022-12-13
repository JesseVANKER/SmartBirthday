package com.example.firstproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.Date
@Parcelize
@Entity
data class Personne(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val prenom : String,
    val nom : String,
    val anniversaire : String
) : Parcelable
