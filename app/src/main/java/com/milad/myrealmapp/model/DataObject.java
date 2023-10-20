package com.milad.myrealmapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class DataObject extends RealmObject{
    // on below line creating a long variable for id of each item of db which will be unique.
    @PrimaryKey
    private long id;
    private String name;
    private String age;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    // on below line we are creating getter and setter methods for name and age variables.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    // on below line we are
    // creating an empty constructor for Data Object class..
    public DataObject() {
    }
}