package com.example.webuntisapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.webuntisapp.model.TimetableResponse;
import com.example.webuntisapp.network.RetrofitClient;
import com.example.webuntisapp.network.WebUntisApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ViewModel for managing timetable data.
 */
public class TimetableViewModel extends AndroidViewModel {

    private final MutableLiveData<TimetableResponse> timetableLiveData;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> error;
    private WebUntisApiService apiService;

    public TimetableViewModel(@NonNull Application application) {
        super(application);
        timetableLiveData = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        error = new MutableLiveData<>();
        // Initialize API service
        apiService = RetrofitClient.getInstance(application).getApiService();
    }

    public LiveData<TimetableResponse> getTimetableLiveData() {
        return timetableLiveData;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    /**
     * Load timetable data from the WebUntis API.
     * @param token Authentication token
     * @param userId User ID
     * @param startDate Start date (YYYY-MM-DD)
     * @param endDate End date (YYYY-MM-DD)
     */
    public void loadTimetable(String token, String userId, String startDate, String endDate) {
        if (isLoading.getValue() != null && isLoading.getValue()) {
            return; // Avoid multiple simultaneous requests
        }

        isLoading.setValue(true);
        error.setValue(null);

        apiService.getTimetable("Bearer " + token, startDate, endDate, userId)
                .enqueue(new Callback<TimetableResponse>() {
                    @Override
                    public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                        isLoading.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            timetableLiveData.setValue(response.body());
                        } else {
                            error.setValue(response.message() != null ? response.message() : "Unknown error");
                        }
                    }

                    @Override
                    public void onFailure(Call<TimetableResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        error.setValue(t.getMessage() != null ? t.getMessage() : "Network error");
                    }
                });
    }
}