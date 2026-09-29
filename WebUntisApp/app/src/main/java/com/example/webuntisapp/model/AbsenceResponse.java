package com.example.webuntisapp.model;

import java.util.List;

/**
 * Enhanced model for absence response.
 * Based on typical WebUntis API response structure.
 */
public class AbsenceResponse {
    private List<AbsenceEntry> entries;
    private String message; // For API messages
    private boolean success; // For API success status

    public List<AbsenceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AbsenceEntry> entries) {
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

    public static class AbsenceEntry {
        private String id;
        private String type; // e.g., "excused", "unexcused", "late", "exempt"
        private String typeName; // Human readable type
        private String date;
        private String period;
        private String periodName;
        private String reason;
        private String status; // e.g., "approved", "pending", "rejected"
        private String studentClass; // Class of student
        private String subject; // Subject missed (if applicable)
        private String teacher; // Teacher (if applicable)
        private String createdAt; // When absence was recorded
        private String updatedAt; // When absence was last updated

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getTypeName() { return typeName; }
        public void setTypeName(String typeName) { this.typeName = typeName; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getPeriod() { return period; }
        public void setPeriod(String period) { this.period = period; }

        public String getPeriodName() { return periodName; }
        public void setPeriodName(String periodName) { this.periodName = periodName; }

        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getStudentClass() { return studentClass; }
        public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    }
}