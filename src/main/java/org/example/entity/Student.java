package org.example.entity;

import java.util.Objects;

public class Student {

    private String groupNumber;
    private Double averageGrade;
    private Integer recordBookNumber;

    public Student(){
    }

    public Student(String groupNumber, Double averageGrade, Integer recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public Integer getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setRecordBookNumber(Integer recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber='" + groupNumber + '\'' +
                ", averageGrade=" + averageGrade +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return Objects.equals(groupNumber, student.groupNumber) && Objects.equals(averageGrade, student.averageGrade) && Objects.equals(recordBookNumber, student.recordBookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageGrade, recordBookNumber);
    }

    public static class StudentBuilder {

        private String groupNumber;
        private Double averageGrade;
        private Integer recordBookNumber;

        public StudentBuilder groupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder averageGrade(Double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public StudentBuilder recordBookNumber(Integer recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(groupNumber, averageGrade, recordBookNumber);
        }
    }
}

