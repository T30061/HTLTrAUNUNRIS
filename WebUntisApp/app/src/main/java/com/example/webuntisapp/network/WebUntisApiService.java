package com.example.webuntisapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * WebUntis API service interface.
 * Updated with more realistic endpoints based on common WebUntis API patterns.
 *
 * NOTE: This is still a starting point. The actual endpoints and parameters
 * need to be adjusted based on the official WebUntis API documentation for your school's installation.
 */
public interface WebUntisApiService {

    // Endpoint for fetching timetable
    // Common WebUntis API pattern: /WebUntis/mobile/timetable?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD&user=USERID
    @GET("WebUntis/mobile/timetable")
    Call<TimetableResponse> getTimetable(
            @Header("Authorization") String authToken,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("user") String userId  // Often requires user/school ID
    );

    // Endpoint for fetching homework
    // Common WebUntis API pattern: /WebUntis/mobile/homework?date=YYYY-MM-DD&user=USERID
    @GET("WebUntis/mobile/homework")
    Call<HomeworkResponse> getHomework(
            @Header("Authorization") String authToken,
            @Query("date") String date,
            @Query("user") String userId
    );

    // Endpoint for fetching absences
    // Common WebUntis API pattern: /WebUntis/mobile/absences?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD&user=USERID
    @GET("WebUntis/mobile/absences")
    Call<AbsenceResponse> getAbsences(
            @Header("Authorization") String authToken,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("user") String userId
    );

    // Optional: Endpoint for user/profile info
    @GET("WebUntis/mobile/user")
    Call<UserInfoResponse> getUserInfo(
            @Header("Authorization") String authToken,
            @Query("user") String userId
    );

    // Optional: Endpoint for announcements/news
    @GET("WebUntis/mobile/news")
    Call<NewsResponse> getNews(
            @Header("Authorization") String authToken,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("user") String userId
    );

    // User info response model
    class UserInfoResponse {
        private String message;
        private boolean success;
        private UserInfo data;

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public UserInfo getData() { return data; }
        public void setData(UserInfo data) { this.data = data; }

        public static class UserInfo {
            private String id;
            private String name;
            private String firstName;
            private String lastName;
            private String className;
            private String email;

            // Getters and setters
            public String getId() { return id; }
            public void setId(String id) { this.id = id; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public String getFirstName() { return firstName; }
            public void setFirstName(String firstName) { this.firstName = firstName; }
            public String getLastName() { return lastName; }
            public void setLastName(String lastName) { this.lastName = lastName; }
            public String getClassName() { return className; }
            public void setClassName(String className) { this.className = className; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
        }
    }

    // News/announcements response model
    class NewsResponse {
        private String message;
        private boolean success;
        private List<NewsItem> data;

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public List<NewsItem> getData() { return data; }
        public void setData(List<NewsItem> data) { this.data = data; }

        public static class NewsItem {
            private String id;
            private String title;
            private String content;
            private String date;
            private String type; // e.g., "info", "warning", "event"
            private String priority; // e.g., "low", "medium", "high"

            // Getters and setters
            public String getId() { return id; }
            public void setId(String id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public String getContent() { return content; }
            public void setContent(String content) { this.content = content; }
            public String getDate() { return date; }
            public void setDate(String date) { this.date = date; }
            public String getType() { return type; }
            public void setType(String type) { this.type = type; }
            public String getPriority() { return priority; }
            public void setPriority(String priority) { this.priority = priority; }
        }
    }
}