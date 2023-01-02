package com.example.applicationmoviesearch.models;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultActor implements Serializable {
    public boolean adult;
    public Integer gender;
    public int id;
    public ArrayList<KnownFor> known_for;
    public String known_for_department;
    public String name;
    public double popularity;
    public String profile_path;

    public ResultActor(boolean adult, Integer gender, int id, ArrayList<KnownFor> known_for, String known_for_department, String name, double popularity, String profile_path) {
        this.adult = adult;
        this.gender = gender;
        this.id = id;
        this.known_for = known_for;
        this.known_for_department = known_for_department;
        this.name = name;
        this.popularity = popularity;
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<KnownFor> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(ArrayList<KnownFor> known_for) {
        this.known_for = known_for;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
