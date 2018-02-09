package com.example.astrid.apprealm.pet;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astrid.apprealm.ConnectFragment;
import com.example.astrid.apprealm.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ListIterator;

import io.realm.RealmResults;

@EFragment
public class ListarPetFragment extends ConnectFragment {


    public ListarPetFragment() {
        // Required empty public constructor
    }

    @ViewById(R.id.texto)
    TextView texto;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        RealmResults<Pet> results =  realm.where(Pet.class).findAll();
        ListIterator li=results.listIterator();
        String status;
        Pet pet;
        while(li.hasNext()) {
            pet = (Pet) li.next();
            status = "\n" + "Id: " + pet.getId()+ " Type: " + pet.getType() + " Name: " + pet.getName()
                    + " Owner: " + pet.getOwner().getName() + "\n";
            updateUi(status);
        }

        return view;
    }

    @UiThread
    void updateUi(String status) {
        texto.setText(texto.getText() + status);
    }

}
