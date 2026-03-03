package com.example.easyconnect.Data.Repositories

import com.example.easyconnect.Data.AppDatabase
import com.example.easyconnect.Data.Tables.Contact
import kotlinx.coroutines.flow.Flow

class Repository(val database: AppDatabase) {

    suspend fun upsertContact(contact: Contact){
        database.contactDao().upsertContact(contact)

    }
    suspend fun deleteContact(contact: Contact){
        database.contactDao().deleteContact(contact)
    }
 fun getAllContacts(): Flow<List<Contact>> {
      return  database.contactDao().getAllContacts()
   }
}