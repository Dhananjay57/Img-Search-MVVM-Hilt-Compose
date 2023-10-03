# Img-Search-MVVM-Hilt-Compose
Image search application by using MVVM, Hilt, Compose, and Navigation
MVVM (Model-View-ViewModel):
MVVM is an architectural pattern used in Android development to separate the application logic into three main components:

Model: Represents the data and business logic.
View: Represents the user interface elements[In my case it's compose].
ViewModel: Acts as a bridge between the Model and the View. It contains the presentation logic and maintains the UI-related data.
MVVM helps in achieving a clean separation of concerns and makes the codebase more maintainable and testable. It is widely used in modern Android app development.

Hilt:
Hilt is a dependency injection library for Android that simplifies the process of providing and injecting dependencies into various parts of your Android application. It is built on top of Dagger and offers annotations and utilities to streamline dependency injection.

Hilt simplifies the setup of Dagger and provides a consistent way to manage dependencies throughout your app. It's especially useful for large-scale applications and helps improve code maintainability and testability.

Jetpack Compose:
Jetpack Compose is a modern Android UI toolkit that allows you to build native user interfaces using a declarative and functional approach. Instead of XML layouts and imperative code, you define your UI elements in a more intuitive and concise way using Kotlin functions.

Jetpack Compose simplifies UI development, provides a more predictable and efficient way to create UIs, and enables UI components to react to changes in data seamlessly. It's a significant departure from the traditional View-based UI framework.

Navigation Component:
The Navigation Component is a part of Android's Jetpack library that simplifies navigation between different screens or destinations within your app. It provides a higher-level way to handle navigation, including handling back stack, deep linking, and transitions.

Navigation Component helps in structuring and managing the navigation flow of your app, making it easier to navigate between fragments or activities while maintaining a consistent user experience.

Retrofit:
Retrofit is a popular Android library for making network requests. It simplifies the process of defining and sending HTTP requests to a web service or API. With Retrofit, you can define API endpoints as interfaces and use annotations to specify request parameters, request types, and response types.

Retrofit integrates seamlessly with other libraries like OkHttp and Gson for efficient network communication and JSON parsing.

Coil:
Coil is an image loading library for Android that is designed for simplicity and performance. It allows you to load and display images from various sources (e.g., URLs, local files) into your UI components efficiently. Coil supports features like caching, transformations, and custom request handling.

Coil is often chosen for its ease of use and modern approach to image loading, making it a popular choice among Android developers.

By using these technologies and libraries in your Image search application, you've leveraged modern best practices and tools to create a well-structured, efficient, and user-friendly Android app
