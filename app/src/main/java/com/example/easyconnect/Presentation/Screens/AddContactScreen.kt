package com.example.easyconnect.Presentation.Screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.easyconnect.R
import com.example.easyconnect.Presentation.State.ContactState
import com.example.easyconnect.Presentation.ViewModel.ContactAppViewModel
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddContactScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState,
    onEvent:() -> Unit

) {
    val image by remember { mutableStateOf(R.drawable.blue_contacts) }
    val context = LocalContext.current
//    val launcher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
//            if (it != null) {
//                val inputStream = context.contentResolver.openInputStream(it)
//                val byte = inputStream?.readBytes()
//                state.image.value = byte
//            }
//        }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            // Get the image bytes from URI
            val inputStream = context.contentResolver.openInputStream(uri)
            val byteArray = inputStream?.readBytes()

     //        Compress the image here before saving to state
            val compressedImage = compressImage(byteArray.toString())
            state.image.value = compressedImage


        }
    }


//    val state = viewModel.state.collectAsState()
    var emailError by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                navigationIcon = { IconButton(onClick = navController::navigateUp){
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
                     },
                title = {Text(text = "Add Contact")})
        }
    ) {innerPadding ->
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (state.image.value == null)
            Image(
                painter = painterResource(id = R.drawable.blue_contacts,),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .clickable {
                        launcher.launch("image/*")
                    }
            ) else {
            Image(
                bitmap = BitmapFactory.decodeByteArray(
                    state.image.value,
                    0,
                    state.image.value!!.size
                ).asImageBitmap(), contentDescription = null,

                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    }
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state.name.value,
            onValueChange = {
                state.name.value = it
            },
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Rounded.Person, contentDescription = null)
            },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state.phoneNumber.value, onValueChange = {
                state.phoneNumber.value = it
            },

            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Rounded.Call, contentDescription = null)
            },
            label = { Text(text = "Number") },
            placeholder = { Text(text = "Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state.email.value, onValueChange = {
                state.email.value = it
                emailError = if (isEmailValid(it)) "" else "Invalid email address"
            },

            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Email") },
            isError = emailError.isNotEmpty(),
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
//                    viewModel.upsertContact()
                onEvent.invoke()
                navController.navigateUp()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ), elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 30.dp
            )
        ) {
            Text(text = "Save Contact")
        }
    }
}

    }


fun isEmailValid(email: String):  Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}

fun compressImage(imagePath: String, quality: Int = 80): ByteArray? {
    // Decode the image file to a Bitmap
    val bitmap = BitmapFactory.decodeFile(imagePath)

    // Check if the Bitmap is valid
    if (bitmap == null) {
        return null
    }

    // Create a ByteArrayOutputStream to hold the compressed image
    val byteArrayOutputStream = ByteArrayOutputStream()

    // Compress the Bitmap to JPEG format (quality can be between 0 to 100)
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)

    // Return the compressed image as a ByteArray
    return byteArrayOutputStream.toByteArray()
}