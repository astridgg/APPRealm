package com.example.astrid.apprealm.person;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astrid.apprealm.ConnectFragment;
import com.example.astrid.apprealm.R;
import com.example.astrid.apprealm.pet.Pet;

import java.util.ListIterator;

import io.realm.RealmList;
import io.realm.RealmResults;

public class ListarFragment extends ConnectFragment {

    private TextView texto;
    String info;

    public ListarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        texto = (TextView)view.findViewById(R.id.texto);


        realm();
        return view;
    }


    public void realm() {
        RealmResults<Person> results = realm.where(Person.class).findAll();
        ListIterator li=results.listIterator();
        String status="";
        String mascotas = "";
        Person per=null;
        Pet pet=null;
        RealmList<Pet> pets = null;
        while(li.hasNext()) {
            per=(Person)li.next();
            pets = per.getPets();

            if (pets.size() != 0){
                ListIterator listIterator = pets.listIterator();
                while (listIterator.hasNext()){
                    pet = (Pet)listIterator.next();
                    mascotas = mascotas + "\nType: " + pet.getType() + ";" + "Name: " + pet.getName() + ".\n";
                }
                status= "\n" +"Id:"+per.getId()+";"+"Nombre:"+per.getName()+"; "+ "Edad:"+ per.getAge()
                        + ";" + "\n" + "Pets: " + "\n" + mascotas + "\n\n";
                mascotas = "";
            } else {
                status= "\n" + "Id:"+per.getId()+";"+"Nombre:"+per.getName()+"; "+ "Edad:"+ per.getAge()
                        + ";" + "\n" + "No Pets.\n\n";
            }

            showStatus(status);
        }

    }
    private void showStatus(String txt) {
        info = (String) texto.getText();
        texto.setText(info + txt);
    }

}
