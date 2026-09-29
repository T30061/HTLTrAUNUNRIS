package com.example.webuntisapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webuntisapp.R;
import com.example.webuntisapp.model.AbsenceResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Adapter for displaying absence entries in a RecyclerView.
 */
public class AbsenceAdapter extends ListAdapter<AbsenceResponse.AbsenceEntry, AbsenceAdapter.AbsenceViewHolder> {

    protected AbsenceAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<AbsenceResponse.AbsenceEntry> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<AbsenceResponse.AbsenceEntry>() {
                @Override
                public boolean areItemsTheSame(@NonNull AbsenceResponse.AbsenceEntry oldItem,
                                               @NonNull AbsenceResponse.AbsenceEntry newItem) {
                    // Compare by ID if available
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull AbsenceResponse.AbsenceEntry oldItem,
                                                  @NonNull AbsenceResponse.AbsenceEntry newItem) {
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId())
                            && oldItem.getType().equals(newItem.getType())
                            && oldItem.getDate().equals(newItem.getDate())
                            && oldItem.getPeriod().equals(newItem.getPeriod())
                            && oldItem.getReason().equals(newItem.getReason());
                }
            };

    @NonNull
    @Override
    public AbsenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_absence, parent, false);
        return new AbsenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenceViewHolder holder, int position) {
        AbsenceResponse.AbsenceEntry entry = getItem(position);
        holder.bind(entry);
    }

    static class AbsenceViewHolder extends RecyclerView.ViewHolder {
        private final TextView typeTextView;
        private final TextView dateTextView;
        private final TextView periodTextView;
        private final TextView reasonTextView;
        private final TextView statusTextView;

        AbsenceViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            periodTextView = itemView.findViewById(R.id.periodTextView);
            reasonTextView = itemView.findViewById(R.id.reasonTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }

        void bind(AbsenceResponse.AbsenceEntry entry) {
            typeTextView.setText(entry.getType());

            // Format date
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
                Date date = inputFormat.parse(entry.getDate());
                String formattedDate = outputFormat.format(date);
                dateTextView.setText(formattedDate);
            } catch (Exception e) {
                dateTextView.setText(entry.getDate());
            }

            periodTextView.setText(entry.getPeriod());
            reasonTextView.setText(entry.getReason());

            // Status with color coding
            statusTextView.setText(entry.getStatus());
            String status = entry.getStatus().toLowerCase();
            if (status.contains("excused") || status.contains("approved")) {
                statusTextView.setTextColor(android.graphics.Color.GREEN);
            } else if (status.contains("unexcused") || status.contains("rejected")) {
                statusTextView.setTextColor(android.graphics.Color.RED);
            } else if (status.contains("pending")) {
                statusTextView.setTextColor(android.graphics.Color.rgb(255, 165, 0)); // Orange
            } else {
                statusTextView.setTextColor(android.graphics.Color.BLACK);
            }
        }
    }
}