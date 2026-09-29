package com.example.webuntisapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Retrofit client instance for WebUntis API.
 * Includes improved error handling, logging, and authentication support.
 *
 * NOTE: The base URL needs to be set to the actual WebUntis API endpoint.
 * This is a placeholder and must be configured correctly.
 */
public class RetrofitClient {
    private static final String BASE_URL = "https://your-webuntis-school.webuntis.com/"; // <-- CHANGE THIS
    private static RetrofitClient instance;
    private final WebUntisApiService apiService;
    private final Context context;

    private RetrofitClient(Context context) {
        this.context = context.getApplicationContext(); // Prevent activity leaks

        // Set up logging interceptor for debugging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // Set up OkHttp client with timeout and logging
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        apiService = retrofit.create(WebUntisApiService.class);
    }

    /**
     * Get singleton instance of RetrofitClient
     * @param context Application context
     * @return RetrofitClient instance
     */
    public static synchronized RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    /**
     * Get the API service instance
     * @return WebUntisApiService instance
     */
    public WebUntisApiService getApiService() {
        return apiService;
    }

    /**
     * Clear the instance (useful for login/logout scenarios)
     */
    public static void clearInstance() {
        instance = null;
    }

    /**
     * Get authentication token from SharedPreferences
     * @return Auth token or null if not set
     */
    public static String getAuthToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("auth_token", null);
    }

    /**
     * Save authentication token to SharedPreferences
     * @param context Application context
     * @param token Auth token to save
     */
    public static void saveAuthToken(Context context, String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("auth_token", token);
        editor.apply();
    }

    /**
     * Clear authentication token from SharedPreferences
     * @param context Application context
     */
    public static void clearAuthToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("auth_token");
        editor.apply();
    }
}