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

import io.realm.Realm;
import io.realm.RealmResults;

public class DeleteFragment extends ConnectFragment {

    private Button btn_delete, btnLimp;
    private EditText etxt_name;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        etxt_name = (EditText)view.findViewById(R.id.etxt_name);

        btn_delete = (Button) view.findViewById(R.id.button_borrar);
        btnLimp = (Button) view.findViewById(R.id.button_res);
        btn_delete.setOnClickListener(this);
        btnLimp.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btn_delete) {
            borrarPersona();
        } else if (view == btnLimp) {
            limpiar();


        }
    }

    public void borrarPersona() {

        try{
            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    // Add a person


                    RealmResults<Person> rows= realm.where(Person.class).equalTo("name", etxt_name.getText().toString()).findAll();;

                    if(rows.isEmpty()){
                        Toast.makeText(getActivity(), "No se encontro usuario",
                                        Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Usuario borrado", Toast.LENGTH_SHORT).show();
                        rows.deleteAllFromRealm();
                        etxt_name.setText("");
                    }

                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "No se pudo borrar usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar() {
        etxt_name.setText("");

    }

}
