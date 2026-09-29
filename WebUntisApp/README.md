# WebUntis Android App

This is an Android application that demonstrates how to integrate with the WebUntis API to fetch timetable, homework, and absence data with a modern UI featuring glassmorphism effects.

## Features

- Modern UI with glassmorphism effects
- Tabbed interface for Timetable, Homework, and Absences
- Material Design components
- Efficient data loading with ViewModel and LiveData
- Pull-to-refresh functionality
- Error handling and loading states
- Secure token storage
- Optimized for performance on lightweight devices

## Screens

1. **Timetable**: View your class schedule in a card-based layout
2. **Homework**: Track assignments with due dates and status indicators
3. **Absences**: Monitor attendance records with type and status color-coding

## Configuration Required

### 1. Update the API Base URL

In `RetrofitClient.java`, change the `BASE_URL` to your school's actual WebUntis API endpoint:
```java
private static final String BASE_URL = "https://your-school.webuntis.com/"; // <-- UPDATE THIS
```

### 2. Update Authentication

The example uses Bearer token authentication. Adjust according to your school's WebUntis API requirements:
- Some installations might use different auth headers
- Some might require username/password instead of token
- Some might use OAuth or other methods

### 3. Update API Endpoints

In `WebUntisApiService.java`, adjust the endpoints and parameters to match the actual WebUntis API:
```java
// Example - adjust these based on actual API documentation
@GET("WebUntis/mobile/timetable")
Call<TimetableResponse> getTimetable(...);
```

### 4. Update Data Models

The model classes (`TimetableResponse.java`, `HomeworkResponse.java`, `AbsenceResponse.java`) are designed to match typical WebUntis API responses. You may need to adjust them based on your specific API version and school configuration.

## Getting WebUntis API Access

To use this app, you need:
1. Access to your school's WebUntis system
2. API credentials (token or username/password) from your school's IT administrator
3. The correct API endpoint URL for your school's WebUntis installation

## Building and Running

1. Open this project in Android Studio
2. Connect an Android device or use an emulator
3. Click "Run" to build and install the app
4. Enter your WebUntis API token when prompted
5. Use the tabs to navigate between timetable, homework, and absence views
6. Pull down to refresh data in any tab

## Permissions

The app requires internet permission (already included in AndroidManifest.xml):
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

## Notes on Glassmorphism

The app implements glassmorphism effects using translucent backgrounds and blur effects where supported. On devices running Android 12+, native blur APIs are used. On older devices, a fallback with semi-transparent backgrounds is provided.

## Disclaimer

This app is for educational purposes only. Always follow your school's policies regarding accessing and using educational data.

## Implementation Details

- **Architecture**: MVVM with ViewModel and LiveData
- **Dependency Injection**: Manual (for simplicity)
- **Networking**: Retrofit 2 with Gson converter
- **UI**: Material Design Components, ConstraintLayout for efficient view hierarchies
- **Data Binding**: ViewBinding not used (findViewById approach for compatibility)
- **Performance**: Optimized for lightweight systems with efficient RecyclerViews, proper lifecycle handling, and minimized overdraw

## Libraries Used

- AndroidX AppCompat, Material Components, ConstraintLayout
- Lifecycle/ViewModel/ViewModelFactory
- Navigation/Fragment
- RecyclerView/CardView
- ViewPager2/TabLayout
- SwipeRefreshLayout
- Retrofit 2 + Gson Converter
- Glide (for image loading)
- Material CalendarView