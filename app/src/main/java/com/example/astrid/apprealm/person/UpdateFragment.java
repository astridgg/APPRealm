package com.example.astrid.apprealm.person;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astrid.apprealm.ConnectFragment;
import com.example.astrid.apprealm.R;

import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

public class UpdateFragment extends ConnectFragment {

    private EditText etxt_id;
    private EditText etxt_name;
    private EditText etxt_age;


    private Button btn_act, btn_limp;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);


        etxt_id = (EditText)view.findViewById(R.id.etxt_id);
        etxt_name = (EditText)view.findViewById(R.id.etxt_name);
        etxt_age = (EditText)view.findViewById(R.id.etxt_age);

        btn_act = (Button)view.findViewById(R.id.button_act);
        btn_limp = (Button)view.findViewById(R.id.button_limpiar);
        btn_act.setOnClickListener(this);
        btn_limp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btn_act){
            actualizar();
        } else if (view == btn_limp){
            limpiar();
        }
    }


    public void actualizar() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Update a person

                RealmResults<Person> results = realm.where(Person.class).findAll();
                ListIterator li=results.listIterator();

                Person per=null;
                Long lg = null;
                while(li.hasNext()) {
                    per=(Person)li.next();

                    if (per.getId() == lg.valueOf(etxt_id.getText().toString())) {
                        // Found the Persons
                        per.setName(etxt_name.getText().toString());
                        per.setAge(etxt_age.getText().toString());
                        Toast.makeText(getActivity(), "Usuario actualizado", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void limpiar() {
        etxt_id.setText("");
        etxt_name.setText("");
        etxt_age.setText("");

    }

}
