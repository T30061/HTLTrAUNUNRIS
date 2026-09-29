package com.example.webuntisapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client instance for WebUntis API.
 *
 * NOTE: The base URL needs to be set to the actual WebUntis API endpoint.
 * This is a placeholder and must be configured correctly.
 */
public class RetrofitClient {
    private static final String BASE_URL = "https://your-webuntis-school.webuntis.com/"; // <-- CHANGE THIS
    private static RetrofitClient instance;
    private final WebUntisApiService apiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(WebUntisApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public WebUntisApiService getApiService() {
        return apiService;
    }
}