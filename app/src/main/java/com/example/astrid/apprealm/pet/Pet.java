package com.example.astrid.apprealm.pet;

import android.widget.Toast;

import com.example.astrid.apprealm.person.Person;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class Pet extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String type;
    private Person owner;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

}
