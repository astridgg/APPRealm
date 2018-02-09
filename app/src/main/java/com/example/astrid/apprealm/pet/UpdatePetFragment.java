package com.example.astrid.apprealm.pet;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astrid.apprealm.ConnectFragment;
import com.example.astrid.apprealm.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

@EFragment
public class UpdatePetFragment extends ConnectFragment {


    public UpdatePetFragment() {
        // Required empty public constructor
    }

    @ViewById(R.id.etPetName)
    EditText etxt_name;
    @ViewById(R.id.etPetNewName)
    EditText etxt_name_new;
    @ViewById(R.id.etOwnerName)
    EditText etxt_owner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_pet, container, false);
        return view;
    }

    @Click(R.id.btnUpdatePet)
    public void update() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Pet> results = realm.where(Pet.class).findAll();
                ListIterator li = results.listIterator();
                Pet pet = null;
                while(li.hasNext()) {
                    pet = (Pet) li.next();
                    if (pet.getOwner().getName().equalsIgnoreCase(etxt_owner.getText().toString()) &&
                            pet.getName().equalsIgnoreCase(etxt_name.getText().toString())) {
                        pet.setName(etxt_name_new.getText().toString());
                        Toast.makeText(getActivity(), "Pet update", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "No se pudo actualizar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Click(R.id.btnReset)
    public void clean() {
        updateUi();
    }

    @UiThread
    void updateUi() {
        etxt_name.setText("");
        etxt_name_new.setText("");
        etxt_owner.setText("");
    }

}
