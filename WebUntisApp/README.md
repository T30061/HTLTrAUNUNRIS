# WebUntis Android App

This is a basic Android application that demonstrates how to integrate with the WebUntis API to fetch timetable, homework, and absence data.

## Important Notes

⚠️ **This is a starting point only**. The WebUntis API integration requires:
1. Correct API endpoint URL
2. Proper authentication method (this example uses Bearer token)
3. Accurate data models matching the actual API response structure
4. Proper error handling and edge cases

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
@GET("webuntis?timetable")
Call<TimetableResponse> getTimetable(...);
```

### 4. Update Data Models
The model classes (`TimetableResponse.java`, `HomeworkResponse.java`, `AbsenceResponse.java`) are placeholders. You must update them to match the actual JSON structure returned by your WebUntis API.

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
5. Use the buttons to load timetable, homework, and absence data

## Permissions

The app requires internet permission (already included in AndroidManifest.xml):
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

## Disclaimer

This app is for educational purposes only. Always follow your school's policies regarding accessing and using educational data.