package com.example.webuntisapp.model;

import java.util.List;

/**
 * Placeholder model for absence response.
 * Adjust according to actual WebUntis API response.
 */
public class AbsenceResponse {
    private List<AbsenceEntry> entries;
    // Add other fields as needed

    public List<AbsenceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AbsenceEntry> entries) {
        this.entries = entries;
    }

    public static class AbsenceEntry {
        private String type; // e.g., "excused", "unexcused"
        private String date;
        private String period;
        private String reason;
        // Add other fields

        // Getters and setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getPeriod() { return period; }
        public void setPeriod(String period) { this.period = period; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
}