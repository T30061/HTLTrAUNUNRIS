package com.example.webuntisapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.webuntisapp.model.AbsenceResponse;
import com.example.webuntisapp.network.RetrofitClient;
import com.example.webuntisapp.network.WebUntisApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ViewModel for managing absence data.
 */
public class AbsenceViewModel extends AndroidViewModel {

    private final MutableLiveData<AbsenceResponse> absenceLiveData;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> error;
    private WebUntisApiService apiService;

    public AbsenceViewModel(@NonNull Application application) {
        super(application);
        absenceLiveData = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        error = new MutableLiveData<>();
        // Initialize API service
        apiService = RetrofitClient.getInstance(application).getApiService();
    }

    public LiveData<AbsenceResponse> getAbsenceLiveData() {
        return absenceLiveData;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    /**
     * Load absence data from the WebUntis API.
     * @param token Authentication token
     * @param userId User ID
     * @param startDate Start date (YYYY-MM-DD)
     * @param endDate End date (YYYY-MM-DD)
     */
    public void loadAbsences(String token, String userId, String startDate, String endDate) {
        if (isLoading.getValue() != null && isLoading.getValue()) {
            return; // Avoid multiple simultaneous requests
        }

        isLoading.setValue(true);
        error.setValue(null);

        apiService.getAbsences("Bearer " + token, startDate, endDate, userId)
                .enqueue(new Callback<AbsenceResponse>() {
                    @Override
                    public void onResponse(Call<AbsenceResponse> call, Response<AbsenceResponse> response) {
                        isLoading.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            absenceLiveData.setValue(response.body());
                        } else {
                            error.setValue(response.message() != null ? response.message() : "Unknown error");
                        }
                    }

                    @Override
                    public void onFailure(Call<AbsenceResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        error.setValue(t.getMessage() != null ? t.getMessage() : "Network error");
                    }
                });
    }
}