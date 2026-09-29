package com.example.webuntisapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.webuntisapp.model.HomeworkResponse;
import com.example.webuntisapp.network.RetrofitClient;
import com.example.webuntisapp.network.WebUntisApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ViewModel for managing homework data.
 */
public class HomeworkViewModel extends AndroidViewModel {

    private final MutableLiveData<HomeworkResponse> homeworkLiveData;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> error;
    private WebUntisApiService apiService;

    public HomeworkViewModel(@NonNull Application application) {
        super(application);
        homeworkLiveData = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        error = new MutableLiveData<>();
        // Initialize API service
        apiService = RetrofitClient.getInstance(application).getApiService();
    }

    public LiveData<HomeworkResponse> getHomeworkLiveData() {
        return homeworkLiveData;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    /**
     * Load homework data from the WebUntis API.
     * @param token Authentication token
     * @param userId User ID
     * @param date Date (YYYY-MM-DD)
     */
    public void loadHomework(String token, String userId, String date) {
        if (isLoading.getValue() != null && isLoading.getValue()) {
            return; // Avoid multiple simultaneous requests
        }

        isLoading.setValue(true);
        error.setValue(null);

        apiService.getHomework("Bearer " + token, date, userId)
                .enqueue(new Callback<HomeworkResponse>() {
                    @Override
                    public void onResponse(Call<HomeworkResponse> call, Response<HomeworkResponse> response) {
                        isLoading.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            homeworkLiveData.setValue(response.body());
                        } else {
                            error.setValue(response.message() != null ? response.message() : "Unknown error");
                        }
                    }

                    @Override
                    public void onFailure(Call<HomeworkResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        error.setValue(t.getMessage() != null ? t.getMessage() : "Network error");
                    }
                });
    }
}