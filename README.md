# Movie Catalog App

Welcome to the Movie Catalog App repository!

## Overview

The Movie Catalog App is an Android application designed to browse and view trending movies. Built with the latest technologies, the app showcases your ability to utilize MVI (Model-View-Intent) architecture and Jetpack Compose, combined with clean architecture principles.

This app provides a smooth user experience, fetching movie data from the TMDB API and displaying it in a user-friendly format. It supports a minimum SDK version of 24 and targets the latest Android version.

## Key Features-**Popular Movies List:** Displays a list of trending movies fetched from the TMDB API.
-**Movie Details:** Provides detailed information about each selected movie.

## Technical Requirements### 1. Architecture and Design Patterns- Implement the MVI (Model-View-Intent) architecture pattern alongside clean architecture principles.
- Structure the project into the following modules:
  -**app:** The main application module.
  -**data:** Manages network requests and local data storage.
  -**domain:** Contains business logic and domain models.
  -**presentation:** Handles UI logic and user interaction.
  -**utils:** Provides utility functions and extensions.
- Ensure a clear separation of concerns across different application layers.

### 2. Network and Data Handling- Use Retrofit for making network requests to the TMDB API.
- Implement data caching and offline support with appropriate techniques, such as Room for database management and SharedPreferences for simple data storage.

### 3. UI and User Experience- Design a smooth and responsive user interface adhering to the latest Android design guidelines.
- Efficiently load and cache images using a library like Glide or Coil.
- Maintain a consistent and intuitive user experience throughout the application.

### 4. Testing- Write unit tests using JUnit and Mockito to ensure feature correctness.
- Demonstrate a solid understanding of testing principles and practices.
