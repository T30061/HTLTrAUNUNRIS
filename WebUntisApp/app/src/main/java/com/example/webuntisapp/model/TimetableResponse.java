package com.example.webuntisapp.model;

import java.util.List;

/**
 * Enhanced model for timetable response.
 * Based on typical WebUntis API response structure.
 */
public class TimetableResponse {
    private List<TimetableEntry> entries;
    private String message; // For API messages
    private boolean success; // For API success status

    public List<TimetableEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<TimetableEntry> entries) {
        this.entries = entries;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class TimetableEntry {
        private String id;
        private String subject;
        private String subjectName;
        private String teacher;
        private String teacherName;
        private String room;
        private String roomName;
        private String startTime;
        private String endTime;
        private String date;
        private String period;
        private String periodName;
        private String type; // e.g., "regular", "exam", "substitution"
        private String code; // Subject code
        private String className; // For class/subject info
        private String comment; // Additional comments

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public String getSubjectName() { return subjectName; }
        public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }

        public String getTeacherName() { return teacherName; }
        public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

        public String getRoom() { return room; }
        public void setRoom(String room) { this.room = room; }

        public String getRoomName() { return roomName; }
        public void setRoomName(String roomName) { this.roomName = roomName; }

        public String getStartTime() { return startTime; }
        public void setStartTime(String startTime) { this.startTime = startTime; }

        public String getEndTime() { return endTime; }
        public void setEndTime(String endTime) { this.endTime = endTime; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getPeriod() { return period; }
        public void setPeriod(String period) { this.period = period; }

        public String getPeriodName() { return periodName; }
        public void setPeriodName(String periodName) { this.periodName = periodName; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }
}