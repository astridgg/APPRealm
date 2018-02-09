package com.example.astrid.apprealm;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.astrid.apprealm.person.AddFragment;
import com.example.astrid.apprealm.person.DeleteFragment;
import com.example.astrid.apprealm.person.ListarFragment;
import com.example.astrid.apprealm.person.UpdateFragment;
import com.example.astrid.apprealm.pet.AddPetFragment_;
import com.example.astrid.apprealm.pet.DeletePetFragment_;
import com.example.astrid.apprealm.pet.ListarPetFragment_;
import com.example.astrid.apprealm.pet.UpdatePetFragment_;

import io.realm.Realm;

public class ConnectFragment extends Fragment implements View.OnClickListener {

    protected Realm realm;
    Button add, listar, update, delete;
    Button addPet, listarPet, updatePet, deletePet;

    public ConnectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_connect, container, false);

        add = (Button)view.findViewById(R.id.btn_add);
        listar = (Button)view.findViewById(R.id.btn_list);
        update = (Button)view.findViewById(R.id.btn_update);
        delete = (Button)view.findViewById(R.id.btn_delete);

        add.setOnClickListener(this);
        listar.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

        addPet = (Button)view.findViewById(R.id.addPet);
        listarPet = (Button)view.findViewById(R.id.listPet);
        updatePet = (Button)view.findViewById(R.id.updatePet);
        deletePet = (Button)view.findViewById(R.id.deletePet);

        addPet.setOnClickListener(this);
        listarPet.setOnClickListener(this);
        updatePet.setOnClickListener(this);
        deletePet.setOnClickListener(this);

        return view;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == add){
            add();
        } else if (view == listar){
            listar();
        } else if (view == update){
            update();
        } else if (view == delete){
            delete();
        } else if (view == addPet){
            Toast.makeText(getActivity(), "ADD PET", Toast.LENGTH_SHORT).show();
            addPet();
        } else if (view == listarPet){
            listarPet();
        } else if (view == updatePet){
            updatePet();
        } else if (view == deletePet){
            deletePet();
        }
    }

    public void add() {
        AddFragment addFragment = new AddFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, addFragment)
                .addToBackStack(null).commit();
    }

    public void listar() {
        ListarFragment listarFragment = new ListarFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, listarFragment)
                .addToBackStack(null).commit();
    }

    public void update() {
        UpdateFragment updateFragment = new UpdateFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, updateFragment)
                .addToBackStack(null).commit();
    }

    public void delete() {
        DeleteFragment deleteFragment = new DeleteFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, deleteFragment).addToBackStack(null).commit();
    }

    public void addPet() {
        AddPetFragment_ addPetFragment = new AddPetFragment_();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, addPetFragment)
                .addToBackStack(null).commit();
    }

    public void listarPet() {
        ListarPetFragment_ listarPetFragment = new ListarPetFragment_();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, listarPetFragment)
                .addToBackStack(null).commit();
    }

    public void updatePet() {
        UpdatePetFragment_ updatePetFragment = new UpdatePetFragment_();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, updatePetFragment)
                .addToBackStack(null).commit();
    }


    public void deletePet() {
        DeletePetFragment_ deletePetFragment = new DeletePetFragment_();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenido, deletePetFragment).addToBackStack(null).commit();
    }


}
