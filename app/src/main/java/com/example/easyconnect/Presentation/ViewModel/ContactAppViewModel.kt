package com.example.easyconnect.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyconnect.Data.Repositories.Repository
import com.example.easyconnect.Data.Tables.Contact
import com.example.easyconnect.Presentation.State.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class ContactAppViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

   private val contactList = repository.getAllContacts()
       .stateIn(
           scope = viewModelScope,
           started = SharingStarted.WhileSubscribed(),
           initialValue =  emptyList())
    private val _state = MutableStateFlow(ContactState())
     val state = combine(_state, contactList){
          _state, contactList ->
         _state.copy(contactList = contactList)

     }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun upsertContact() {
        viewModelScope.launch {
            repository.upsertContact(
                Contact(
                     id = state.value.id.value,
                    name = state.value.name.value,
                    phoneNumber = state.value.phoneNumber.value,
                    email = state.value.email.value,
                    dob = state.value.dob.value,
                    image = state.value.image.value
                )
            )
        }
    }

    fun deleteContact() {
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            phoneNumber = state.value.phoneNumber.value,
            email = state.value.email.value,
            dob = state.value.dob.value,
            image = state.value.image.value

        )
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }


}

