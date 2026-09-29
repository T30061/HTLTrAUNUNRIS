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
import com.example.webuntisapp.model.TimetableResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Adapter for displaying timetable entries in a RecyclerView.
 */
public class TimetableAdapter extends ListAdapter<TimetableResponse.TimetableEntry, TimetableAdapter.TimetableViewHolder> {

    protected TimetableAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<TimetableResponse.TimetableEntry> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TimetableResponse.TimetableEntry>() {
                @Override
                public boolean areItemsTheSame(@NonNull TimetableResponse.TimetableEntry oldItem,
                                               @NonNull TimetableResponse.TimetableEntry newItem) {
                    // Compare by ID if available, otherwise by all fields
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull TimetableResponse.TimetableEntry oldItem,
                                                  @NonNull TimetableResponse.TimetableEntry newItem) {
                    return oldItem.getId() != null && oldItem.getId().equals(newItem.getId())
                            && oldItem.getSubject().equals(newItem.getSubject())
                            && oldItem.getTeacher().equals(newItem.getTeacher())
                            && oldItem.getRoom().equals(newItem.getRoom())
                            && oldItem.getStartTime().equals(newItem.getStartTime())
                            && oldItem.getEndTime().equals(newItem.getEndTime())
                            && oldItem.getDate().equals(newItem.getDate());
                }
            };

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timetable, parent, false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        TimetableResponse.TimetableEntry entry = getItem(position);
        holder.bind(entry);
    }

    static class TimetableViewHolder extends RecyclerView.ViewHolder {
        private final TextView subjectTextView;
        private final TextView teacherTextView;
        private final TextView roomTextView;
        private final TextView timeTextView;
        private final TextView dateTextView;
        private final TextView typeTextView;

        TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            teacherTextView = itemView.findViewById(R.id.teacherTextView);
            roomTextView = itemView.findViewById(R.id.roomTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }

        void bind(TimetableResponse.TimetableEntry entry) {
            subjectTextView.setText(entry.getSubject());
            teacherTextView.setText(entry.getTeacher());
            roomTextView.setText(entry.getRoom());

            // Format time
            String time = entry.getStartTime() + " - " + entry.getEndTime();
            timeTextView.setText(time);

            // Format date (optional, if we want to show date on each item)
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd MMM", Locale.getDefault());
                Date date = inputFormat.parse(entry.getDate());
                String formattedDate = outputFormat.format(date);
                dateTextView.setText(formattedDate);
            } catch (Exception e) {
                dateTextView.setText(entry.getDate());
            }

            // Set type with color coding
            String type = entry.getType();
            typeTextView.setText(type);
            // TODO: Add color coding based on type (regular, exam, substitution, etc.)
        }
    }
}