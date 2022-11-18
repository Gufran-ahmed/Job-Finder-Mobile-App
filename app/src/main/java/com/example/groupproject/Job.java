package com.example.groupproject;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private String Description;
    private String PostedBy;
    private String StartDate;
    private String Status;
    private String Types;
    List<String> Employee = new ArrayList<>();
    private int Positions;
    private int Points;
    private double Longitude;
    private double Latitude;

    public Job(){}




    public Job(String description, List<String> Employees, int positions, int points, String postedBy, String startDate, String status, String types, double latitude, double longitude) {
        Description = description;
        Employee = Employees;
        Positions = positions;
        Points = points;
        PostedBy = postedBy;
        StartDate = startDate;
        Status = status;
        Types = types;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTypes() {
        return Types;
    }

    public void setTypes(String types) {
        Types = types;
    }


    public int getPositions() {
        return Positions;
    }

    public void setPositions(int positions) {
        Positions = positions;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    @Override
    public String toString() {
        return "Job{" +
                "Description='" + Description + '\'' +
                ", PostedBy='" + PostedBy + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", Status='" + Status + '\'' +
                ", Types='" + Types + '\'' +
                ", Employee=" + Employee +
                ", Positions=" + Positions +
                ", Points=" + Points +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                '}';
    }
}
