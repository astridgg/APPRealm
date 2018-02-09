package com.example.astrid.apprealm.person;

import com.example.astrid.apprealm.pet.Pet;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {

    // All fields are by default persisted.
    private String name, foto;
    private int age;
    @PrimaryKey
    private long id;

    private RealmList<Pet> pets;

    // Let your IDE generate getters and setters for you!
    // Or if you like you can even have public fields and no accessors! See Dog.java and Cat.java
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getAge() {
        return age;
    }

    public void setAge(String age) {
        int n=0;
        try{
            n=Integer.parseInt(age);
        }catch(Exception e){
            n=0;
        }

        this.age = n;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setPets(RealmList<Pet> pets){
        this.pets = pets;
    }

    public RealmList<Pet> getPets() {
        return pets;
    }
}
