# EasyConnect - Android Contact Book App

EasyConnect is a modern Android application built to manage contacts efficiently. Developed using Kotlin and following the latest Android development practices, it provides a seamless user experience for viewing, adding, and managing contacts. The app leverages Jetpack Compose for a declarative UI and Room Database for robust local storage.

## 📱 Features

*   **View Contacts:** Browse through a list of all saved contacts.
*   **Add New Contacts:** Add contacts with details including:
    *   Name
    *   Phone Number
    *   Email Address
    *   Date of Birth
    *   Profile Image
*   **Modern UI:** Built entirely with Jetpack Compose, offering a responsive and smooth user interface.
*   **Offline Support:** All contacts are stored locally using Room Database, ensuring the app works perfectly offline.

## 🛠️ Tech Stack & Architecture

This project is built using modern Android development tools and follows the **MVVM (Model-View-ViewModel)** architecture pattern, adhering to Clean Architecture principles.

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose (Declarative UI)
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **Dependency Injection:** Dagger Hilt
*   **Local Database:** Room Database
*   **Navigation:** Navigation Compose
*   **Serialization:** Kotlinx Serialization
*   **Asynchronous Programming:** Kotlin Coroutines & Flow

## 📂 Project Structure

The project code is organized into clear packages to separate concerns:

*   **`DI` (Dependency Injection):** Contains Hilt modules for providing dependencies like the Room Database and DAOs.
*   **`Data`:** Handles the data layer.
    *   `Tables`: Room Entities (e.g., `Contact` data class).
    *   `Dao`: Data Access Objects for database operations.
    *   `Repositories`: Repository classes to abstract database interactions from the ViewModels.
*   **`Presentation`:** Contains the UI layer.
    *   `Screens`: Jetpack Compose composables for the UI (e.g., `HomeScreen`, `AddContactScreen`).
    *   `Navigation`: Defines the app's navigation graph (`AppNavigation.kt`, `Routs.kt`).
    *   `ViewModel`: ViewModels that hold UI state and interact with the repositories.
    *   `State`: Data classes representing the UI state.
*   **`ui`:** Contains the core UI components, theme definitions, colors, and typography for Jetpack Compose.

## 🚀 Getting Started

To run this project locally, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Tarun-sharma05/EasyConnect-App.git
    ```
2.  **Open the project:**
    Open the cloned directory in **Android Studio** (Koala or newer recommended).
3.  **Sync Gradle:**
    Allow Android Studio to sync the project and download all necessary dependencies.
4.  **Run the app:**
    Connect an Android device or start an emulator running Android SDK 27 (Android 8.1) or higher, and click the "Run" button in Android Studio.

## 📝 License

This project is open-source. Feel free to use and modify it as per your needs.
