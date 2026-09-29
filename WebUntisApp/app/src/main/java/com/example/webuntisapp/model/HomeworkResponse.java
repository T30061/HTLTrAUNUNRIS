package com.example.webuntisapp.model;

import java.util.List;

/**
 * Placeholder model for homework response.
 * Adjust according to actual WebUntis API response.
 */
public class HomeworkResponse {
    private List<HomeworkEntry> entries;
    // Add other fields as needed

    public List<HomeworkEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HomeworkEntry> entries) {
        this.entries = entries;
    }

    public static class HomeworkEntry {
        private String subject;
        private String title;
        private String description;
        private String dueDate;
        // Add other fields

        // Getters and setters
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getDueDate() { return dueDate; }
        public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    }
}