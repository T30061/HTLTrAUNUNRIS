package com.example.webuntisapp.model;

import java.util.List;

/**
 * Enhanced model for homework response.
 * Based on typical WebUntis API response structure.
 */
public class HomeworkResponse {
    private List<HomeworkEntry> entries;
    private String message; // For API messages
    private boolean success; // For API success status

    public List<HomeworkEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HomeworkEntry> entries) {
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

    public static class HomeworkEntry {
        private String id;
        private String subject;
        private String subjectName;
        private String title;
        private String description;
        private String dueDate;
        private String assignedDate;
        private String status; // e.g., "assigned", "completed", "overdue"
        private String priority; // e.g., "low", "medium", "high"
        private String teacher; // Teacher who assigned
        private String teacherName;
        private String className; // Class for which homework is assigned
        private String fileAttachments; // Comma-separated list or JSON array
        private String url; // Link to online resource
        private String estimatedTime; // Estimated time to complete

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public String getSubjectName() { return subjectName; }
        public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getDueDate() { return dueDate; }
        public void setDueDate(String dueDate) { this.dueDate = dueDate; }

        public String getAssignedDate() { return assignedDate; }
        public void setAssignedDate(String assignedDate) { this.assignedDate = assignedDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getPriority() { return priority; }
        public void setPriority(String priority) { this.priority = priority; }

        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }

        public String getTeacherName() { return teacherName; }
        public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }

        public String getFileAttachments() { return fileAttachments; }
        public void setFileAttachments(String fileAttachments) { this.fileAttachments = fileAttachments; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }

        public String getEstimatedTime() { return estimatedTime; }
        public void setEstimatedTime(String estimatedTime) { this.estimatedTime = estimatedTime; }
    }
}