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
import com.example.webuntisapp.model.HomeworkResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Adapter for displaying homework entries in a RecyclerView.
 */
public class HomeworkAdapter extends ListAdapter<HomeworkResponse.HomeworkEntry, HomeworkAdapter.HomeworkViewHolder> {

    protected HomeworkAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<HomeworkResponse.HomeworkEntry> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<HomeworkResponse.HomeworkEntry>() {
                @Override
                public boolean areItemsTheSame(@NonNull HomeworkResponse.HomeworkEntry oldItem,
                                               @NonNull HomeworkResponse.HomeworkEntry newItem) {
                    // Compare by ID if available
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull HomeworkResponse.HomeworkEntry oldItem,
                                                  @NonNull HomeworkResponse.HomeworkEntry newItem) {
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId())
                            && oldItem.getSubject().equals(newItem.getSubject())
                            && oldItem.getTitle().equals(newItem.getTitle())
                            && oldItem.getDescription().equals(newItem.getDescription())
                            && oldItem.getDueDate().equals(newItem.getDueDate())
                            && oldItem.getStatus().equals(newItem.getStatus());
                }
            };

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_homework, parent, false);
        return new HomeworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        HomeworkResponse.HomeworkEntry entry = getItem(position);
        holder.bind(entry);
    }

    static class HomeworkViewHolder extends RecyclerView.ViewHolder {
        private final TextView subjectTextView;
        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private final TextView dueDateTextView;
        private final TextView statusTextView;
        private final TextView priorityTextView;

        HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);
        }

        void bind(HomeworkResponse.HomeworkEntry entry) {
            subjectTextView.setText(entry.getSubject());
            titleTextView.setText(entry.getTitle());
            descriptionTextView.setText(entry.getDescription());

            // Format due date
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
                Date date = inputFormat.parse(entry.getDueDate());
                String formattedDate = outputFormat.format(date);
                dueDateTextView.setText(formattedDate);
            } catch (Exception e) {
                dueDateTextView.setText(entry.getDueDate());
            }

            // Status with color coding
            statusTextView.setText(entry.getStatus());
            String status = entry.getStatus().toLowerCase();
            if (status.contains("completed")) {
                statusTextView.setTextColor(android.graphics.Color.GREEN);
            } else if (status.contains("overdue")) {
                statusTextView.setTextColor(android.graphics.Color.RED);
            } else {
                statusTextView.setTextColor(android.graphics.Color.BLACK);
            }

            // Priority with indicator
            String priority = entry.getPriority();
            priorityTextView.setText(priority);
            switch (priority.toLowerCase()) {
                case "high":
                    priorityTextView.setTextColor(android.graphics.Color.RED);
                    break;
                case "medium":
                    priorityTextView.setTextColor(android.graphics.Color.rgb(255, 165, 0)); // Orange
                    break;
                case "low":
                    priorityTextView.setTextColor(android.graphics.Color.GREEN);
                    break;
                default:
                    priorityTextView.setTextColor(android.graphics.Color.BLACK);
                    break;
            }
        }
    }
}