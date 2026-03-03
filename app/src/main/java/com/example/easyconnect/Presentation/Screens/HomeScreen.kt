package com.example.easyconnect.Presentation.Screens

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easyconnect.Presentation.Navigation.AddContactScreen
import com.example.easyconnect.Presentation.ViewModel.ContactAppViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easyconnect.Presentation.State.ContactState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavHostController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState,
    onDelete: () -> Unit
) {
//         val state = viewModel.state.collectAsState()

         val context = LocalContext.current
    Scaffold (floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(AddContactScreen){
          state.id.value = null
            state.name.value = ""
            state.phoneNumber.value = ""
            state.email.value = ""
            state.dob.value = null
            state.image.value = null
        }
        }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }, modifier = Modifier.background(MaterialTheme.colorScheme.background),
        containerColor = MaterialTheme.colorScheme.background
    ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)) {
                    items(state.contactList){
                        Card ( //onClick = {}
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White

                            ),
                             modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 10.dp)
                            .combinedClickable (
                                onClick = {
//                                    navController.navigate(AddContactScreen)
                                },
                                onDoubleClick = {
                                    state.name.value = it.name
                                    state.phoneNumber.value = it.phoneNumber
                                    state.email.value = it.email
                                    state.id.value = it.id
                                    state.image.value = it.image
                                    navController.navigate(AddContactScreen)
                                },
                                onLongClick = {
                                    val callIntent = Intent(Intent.ACTION_CALL)
                                     callIntent.setData(Uri.parse("tel:${it.phoneNumber}"))

                                    context.startActivity(callIntent)
                                }
                            )


                        ) {
                            Row(    modifier = Modifier.padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                if (it.image == null){

                                    val text = it.name.split(" ", ignoreCase = false)
                                    val text2 = text.filter { it.isNotBlank() }
                                    val abc = text2.map { it.first() }
                                     Text(text = abc.joinToString(separator = ""))

                                }else {
                                    Image(
                                        bitmap = BitmapFactory.decodeByteArray(
                                            it.image,
                                            0,
                                            it.image.size!!
                                        ).asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier.size(40.dp)
                                            .clip(CircleShape)

                                        )
                                }
                            Column(modifier = Modifier.padding(start = 10.dp)) {

                                Text(text = it.name)
                                Text(text = it.phoneNumber)
                                Text(text = it.email)
                            }
                                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null,
                                    modifier = Modifier.clickable {
                                        state.id.value = it.id
                                        state.name.value = it.name
                                        state.phoneNumber.value = it.phoneNumber
                                        state.email.value = it.email
                                        state.dob.value = it.dob
                                        state.image.value = it.image

                                        onDelete()


                                    }
                                    )

                        }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
        }


}