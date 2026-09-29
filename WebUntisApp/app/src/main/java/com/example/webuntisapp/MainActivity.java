package com.example.webuntisapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.webuntisapp.model.AbsenceResponse;
import com.example.webuntisapp.model.HomeworkResponse;
import com.example.webuntisapp.model.TimetableResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main activity of the WebUntis app.
 * Provides a simple UI to fetch timetable, homework, and absence data from the WebUntis API.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextToken;
    private Button buttonTimetable;
    private Button buttonHomework;
    private Button buttonAbsences;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextToken = findViewById(R.id.editTextToken);
        buttonTimetable = findViewById(R.id.buttonTimetable);
        buttonHomework = findViewById(R.id.buttonHomework);
        buttonAbsences = findViewById(R.id.buttonAbsences);
        textViewResult = findViewById(R.id.textViewResult);

        // Set up button click listeners
        buttonTimetable.setOnClickListener(v -> loadTimetable());
        buttonHomework.setOnClickListener(v -> loadHomework());
        buttonAbsences.setOnClickListener(v -> loadAbsences());
    }

    private void loadTimetable() {
        String token = editTextToken.getText().toString().trim();
        if (token.isEmpty()) {
            textViewResult.setText("Please enter your API token");
            return;
        }

        // Show loading state
        textViewResult.setText("Loading timetable...");

        // Make API call
        RetrofitClient.getInstance().getApiService()
                .getTimetable("Bearer " + token, "2026-09-01", "2026-09-30")
                .enqueue(new Callback<TimetableResponse>() {
                    @Override
                    public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            TimetableResponse timetableResponse = response.body();
                            StringBuilder sb = new StringBuilder();
                            sb.append("Timetable:\n");
                            for (TimetableResponse.TimetableEntry entry : timetableResponse.getEntries()) {
                                sb.append("Subject: ").append(entry.getSubject()).append("\n");
                                sb.append("Teacher: ").append(entry.getTeacher()).append("\n");
                                sb.append("Room: ").append(entry.getRoom()).append("\n");
                                sb.append("Time: ").append(entry.getStartTime()).append(" - ").append(entry.getEndTime()).append("\n\n");
                            }
                            textViewResult.setText(sb.toString());
                        } else {
                            textViewResult.setText("Failed to load timetable: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<TimetableResponse> call, Throwable t) {
                        textViewResult.setText("Error: " + t.getMessage());
                    }
                });
    }

    private void loadHomework() {
        String token = editTextToken.getText().toString().trim();
        if (token.isEmpty()) {
            textViewResult.setText("Please enter your API token");
            return;
        }

        // Show loading state
        textViewResult.setText("Loading homework...");

        // Make API call
        RetrofitClient.getInstance().getApiService()
                .getHomework("Bearer " + token, "2026-09-29")
                .enqueue(new Callback<HomeworkResponse>() {
                    @Override
                    public void onResponse(Call<HomeworkResponse> call, Response<HomeworkResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            HomeworkResponse homeworkResponse = response.body();
                            StringBuilder sb = new StringBuilder();
                            sb.append("Homework:\n");
                            for (HomeworkResponse.HomeworkEntry entry : homeworkResponse.getEntries()) {
                                sb.append("Subject: ").append(entry.getSubject()).append("\n");
                                sb.append("Title: ").append(entry.getTitle()).append("\n");
                                sb.append("Description: ").append(entry.getDescription()).append("\n");
                                sb.append("Due: ").append(entry.getDueDate()).append("\n\n");
                            }
                            textViewResult.setText(sb.toString());
                        } else {
                            textViewResult.setText("Failed to load homework: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<HomeworkResponse> call, Throwable t) {
                        textViewResult.setText("Error: " + t.getMessage());
                    }
                });
    }

    private void loadAbsences() {
        String token = editTextToken.getText().toString().trim();
        if (token.isEmpty()) {
            textViewResult.setText("Please enter your API token");
            return;
        }

        // Show loading state
        textViewResult.setText("Loading absences...");

        // Make API call
        RetrofitClient.getInstance().getApiService()
                .getAbsences("Bearer " + token, "2026-09-01", "2026-09-30")
                .enqueue(new Callback<AbsenceResponse>() {
                    @Override
                    public void onResponse(Call<AbsenceResponse> call, Response<AbsenceResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            AbsenceResponse absenceResponse = response.body();
                            StringBuilder sb = new StringBuilder();
                            sb.append("Absences:\n");
                            for (AbsenceResponse.AbsenceEntry entry : absenceResponse.getEntries()) {
                                sb.append("Type: ").append(entry.getType()).append("\n");
                                sb.append("Date: ").append(entry.getDate()).append("\n");
                                sb.append("Period: ").append(entry.getPeriod()).append("\n");
                                sb.append("Reason: ").append(entry.getReason()).append("\n\n");
                            }
                            textViewResult.setText(sb.toString());
                        } else {
                            textViewResult.setText("Failed to load absences: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<AbsenceResponse> call, Throwable t) {
                        textViewResult.setText("Error: " + t.getMessage());
                    }
                });
    }
}