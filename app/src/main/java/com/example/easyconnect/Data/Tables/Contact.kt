package com.example.easyconnect.Data.Tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val dob: Long?,
    val image: ByteArray?
)