package com.example.astrid.apprealm.person;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.astrid.apprealm.BuildConfig;
import com.example.astrid.apprealm.ConnectFragment;
import com.example.astrid.apprealm.R;

import java.io.File;

import io.realm.Realm;

public class AddFragment extends ConnectFragment {

    private EditText etxt_name;
    private EditText etxt_age;

    private Button btn_add, btn_limpiar;
    private ImageButton img;

    private String nameFoto;
    private String[] archivos;


    Context context;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_add, container, false);

        etxt_name = (EditText)view.findViewById(R.id.etxt_name);
        etxt_age = (EditText) view.findViewById(R.id.etxt_age);

        nameFoto = etxt_name.getText().toString() + etxt_age.getText().toString();

        img = (ImageButton)view.findViewById(R.id.imageButton);
        btn_add = (Button)view.findViewById(R.id.button_add);
        btn_limpiar = (Button)view.findViewById(R.id.button_reset);

        btn_add.setOnClickListener(this);
        btn_limpiar.setOnClickListener(this);
        img.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        if (view == btn_add){
            Toast.makeText(getActivity(), "Usuario añadido", Toast.LENGTH_SHORT).show();
            nuevo();
        } else if (view == btn_limpiar){
            Toast.makeText(getActivity(),"Pulsado Boton LIMPIAR", Toast.LENGTH_SHORT).show();
            limpiar();
        } else if (view == img){
            Toast.makeText(getActivity(),"Hacer foto", Toast.LENGTH_SHORT).show();
            hacerFoto();
        }
    }

    public void nuevo() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                int id = (int)(Math.random()*1000)+1;
                Person person = realm.createObject(Person.class, id);
                //person.setId((int)(Math.random()*1000)+1);
                person.setName(etxt_name.getText().toString());
                person.setAge(etxt_age.getText().toString());
                if (recuperarFoto()== true) {
                    person.setFoto(nameFoto);
                } else {
                    person.setFoto("");
                }
                person.setPets(null);

            }
        });
    }

    public void hacerFoto() {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File storageDir = getActivity().getExternalFilesDir(null);
        File foto = new File(storageDir, nameFoto);
        Uri photoURI = FileProvider.getUriForFile(getActivity(),
                BuildConfig.APPLICATION_ID + ".provider", foto);
        intento1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivity(intento1);
    }

    public boolean recuperarFoto() {
        File dir = getActivity().getExternalFilesDir(null);
        archivos = dir.list();

        for (int i = 0; i < archivos.length; i++){
            if (archivos[1] == nameFoto){
                return true;
            }
        }
        return false;
    }

    public void limpiar() {
        etxt_name.setText("");
        etxt_age.setText("");

    }

}
