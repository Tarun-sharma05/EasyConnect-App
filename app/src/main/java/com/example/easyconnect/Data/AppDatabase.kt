package com.example.easyconnect.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.easyconnect.Data.Dao.ContactDao
import com.example.easyconnect.Data.Tables.Contact

@Database(entities = arrayOf(Contact::class), version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
     abstract fun contactDao(): ContactDao


}