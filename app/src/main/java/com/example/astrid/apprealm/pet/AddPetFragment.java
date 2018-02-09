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
import com.example.astrid.apprealm.person.Person;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

@EFragment
public class AddPetFragment extends ConnectFragment {


    public AddPetFragment() {
        // Required empty public constructor
    }

    @ViewById(R.id.etxt_person)
    EditText etxt_person;
    @ViewById(R.id.etxt_name)
    EditText etxt_name;
    @ViewById(R.id.etxt_type)
    EditText etxt_type;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);
        return view;
    }


    @Click(R.id.buttonAddPet)
    void buttonAddPetCliked() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.where(Person.class).equalTo("name", etxt_person.getText().toString()).findFirst();
                if(person != null) {
                    Number currentIdNum = realm.where(Pet.class).max("id");
                    int pk = currentIdNum == null ? 1 : currentIdNum.intValue() + 1;
                    Pet pet = realm.createObject(Pet.class, pk);
                    pet.setOwner(person);
                    pet.setName(etxt_name.getText().toString());
                    pet.setType(etxt_type.getText().toString());

                    RealmList<Pet> pets = person.getPets();
                    pets.add(pet);
                    person.setPets(pets);

                    Toast.makeText(getActivity(), "Se ha añadido su mascota", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No se ha encontrado el dueño", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Click(R.id.buttonReset)
    void clean() {
        updateUi();
    }

    @UiThread
    void updateUi() {
        etxt_name.setText("");
        etxt_type.setText("");
        etxt_person.setText("");
    }

}
