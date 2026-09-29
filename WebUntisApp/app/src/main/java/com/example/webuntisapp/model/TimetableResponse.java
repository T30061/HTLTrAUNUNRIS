package com.example.webuntisapp.model;

import java.util.List;

/**
 * Placeholder model for timetable response.
 * Adjust according to actual WebUntis API response.
 */
public class TimetableResponse {
    private List<TimetableEntry> entries;
    // Add other fields as needed

    public List<TimetableEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TimetableEntry> entries) {
        this.entries = entries;
    }

    public static class TimetableEntry {
        private String subject;
        private String teacher;
        private String room;
        private String startTime;
        private String endTime;
        // Add other fields

        // Getters and setters
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }
        public String getRoom() { return room; }
        public void setRoom(String room) { this.room = room; }
        public String getStartTime() { return startTime; }
        public void setStartTime(String startTime) { this.startTime = startTime; }
        public String getEndTime() { return endTime; }
        public void setEndTime(String endTime) { this.endTime = endTime; }
    }
}