package com.example.webuntisapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * WebUntis API service interface.
 *
 * NOTE: This is a placeholder. The actual endpoints and parameters
 * need to be adjusted based on the official WebUntis API documentation.
 */
public interface WebUntisApiService {

    // Example endpoint for fetching timetable
    @GET("webuntis?timetable")
    Call<TimetableResponse> getTimetable(
            @Header("Authorization") String authToken,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate
    );

    // Example endpoint for fetching homework
    @GET("webuntis?homework")
    Call<HomeworkResponse> getHomework(
            @Header("Authorization") String authToken,
            @Query("date") String date
    );

    // Example endpoint for fetching absences
    @GET("webuntis?absences")
    Call<AbsenceResponse> getAbsences(
            @Header("Authorization") String authToken,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate
    );
}