package com.example.webuntisapp.ui.absence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.webuntisapp.R;
import com.example.webuntisapp.model.AbsenceResponse;
import com.example.webuntisapp.viewmodel.AbsenceViewModel;
import com.example.webuntisapp.adapter.AbsenceAdapter;

import java.util.List;

/**
 * Fragment for displaying absence data.
 */
public class AbsenceFragment extends Fragment {

    private AbsenceViewModel absenceViewModel;
    private RecyclerView recyclerView;
    private AbsenceAdapter adapter;
    private ProgressBar progressBar;
    private TextView emptyTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public AbsenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        absenceViewModel = new ViewModelProvider(this).get(AbsenceViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_absence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        emptyTextView = view.findViewById(R.id.emptyTextView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        // Setup RecyclerView
        adapter = new AbsenceAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // Setup SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> loadAbsences());
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        // Observe absence data
        absenceViewModel.getAbsenceLiveData().observe(getViewLifecycleOwner(), absenceResponse -> {
            // Hide loading indicators
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);

            if (absenceResponse != null && absenceResponse.isSuccess()) {
                List<AbsenceResponse.AbsenceEntry> entries = absenceResponse.getEntries();
                if (entries != null && !entries.isEmpty()) {
                    adapter.submitList(entries);
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyTextView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyTextView.setVisibility(View.VISIBLE);
                    emptyTextView.setText(R.string.no_absence_data);
                }
            } else {
                recyclerView.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.VISIBLE);
                String message = absenceResponse != null ? absenceResponse.getMessage() : getString(R.string.unknown_error);
                emptyTextView.setText(message);
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        // Observe loading state
        absenceViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.GONE);
            }
        });

        // Observe error state
        absenceViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            if (error != null && !error.isEmpty()) {
                emptyTextView.setVisibility(View.VISIBLE);
                emptyTextView.setText(error);
                recyclerView.setVisibility(View.GONE);
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        // Load initial data
        loadAbsences();
    }

    private void loadAbsences() {
        // In a real implementation, we would get the token and user ID from secure storage
        // and the date from the selected date in the calendar
        // For now, we'll use placeholder values
        String token = "placeholder_token"; // TODO: Get from secure storage
        String userId = "placeholder_user"; // TODO: Get from secure storage
        String startDate = android.text.format.DateFormat.format("yyyy-MM-01", System.currentTimeMillis()).toString(); // First day of month
        String endDate = android.text.format.DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()).toString(); // Today

        absenceViewModel.loadAbsences(token, userId, startDate, endDate);
    }
}