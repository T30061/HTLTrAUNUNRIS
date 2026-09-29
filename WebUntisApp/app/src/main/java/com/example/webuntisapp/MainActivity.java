package com.example.webuntisapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.webuntisapp.ui.absence.AbsenceFragment;
import com.example.webuntisapp.ui.homework.HomeworkFragment;
import com.example.webuntisapp.ui.timetable.TimetableFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * Main activity of the WebUntis app.
 * Provides a tabbed interface to view timetable, homework, and absence data.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextToken;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_main);

        // Initialize shared preferences for storing token
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Initialize views
        editTextToken = findViewById(R.id.editTextToken);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Set up ViewPager with adapter
        viewPager.setAdapter(new ScreenSlidePagerAdapter(this));

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText(R.string.tab_timetable);
                            break;
                        case 1:
                            tab.setText(R.string.tab_homework);
                            break;
                        case 2:
                            tab.setText(R.string.tab_absences);
                            break;
                    }
                }).attach();

        // Load saved token if available
        String savedToken = sharedPreferences.getString("auth_token", "");
        if (!savedToken.isEmpty()) {
            editTextToken.setText(savedToken);
        }

        // Set up token save on focus loss or enter key
        editTextToken.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                saveToken();
            }
        });

        editTextToken.setOnEditorActionListener((v, actionId, event) -> {
            saveToken();
            return true;
        });
    }

    /**
     * Save the API token to SharedPreferences.
     */
    private void saveToken() {
        String token = editTextToken.getText().toString().trim();
        if (!token.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("auth_token", token);
            editor.apply();
            Toast.makeText(this, "Token saved", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Adapter for the ViewPager2 to manage fragments.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new TimetableFragment();
                case 1:
                    return new HomeworkFragment();
                case 2:
                    return new AbsenceFragment();
                default:
                    return new TimetableFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3; // Three tabs: Timetable, Homework, Absences
        }
    }
}