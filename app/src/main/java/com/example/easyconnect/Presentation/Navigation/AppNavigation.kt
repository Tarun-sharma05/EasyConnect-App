package com.example.easyconnect.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyconnect.Presentation.Screens.AddContactScreenUI
import com.example.easyconnect.Presentation.Screens.HomeScreenUI
import com.example.easyconnect.Presentation.ViewModel.ContactAppViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewModel: ContactAppViewModel = hiltViewModel()){
  val navController = rememberNavController()
    val state = viewModel.state.collectAsState()
  NavHost(navController = navController, startDestination = HomeScreen) {
       composable<HomeScreen> {
           HomeScreenUI(navController = navController, state = state.value,
               onDelete = { viewModel.deleteContact() })
       }
      composable<AddContactScreen> {

          AddContactScreenUI(navController = navController, state = state.value,
              onEvent = { viewModel.upsertContact() })
      }
  }
}