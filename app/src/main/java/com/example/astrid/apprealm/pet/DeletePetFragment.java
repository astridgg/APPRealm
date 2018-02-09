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

import io.realm.Realm;
import io.realm.RealmResults;

@EFragment
public class DeletePetFragment extends ConnectFragment {


    public DeletePetFragment() {
        // Required empty public constructor
    }

    @ViewById
    EditText etxt_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }

    @Click(R.id.button_borrar)
    public void delete() {
        try{
            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    RealmResults<Pet> rows= realm.where(Pet.class).equalTo("name",
                            etxt_name.getText().toString()).findAll();
                    if(rows.isEmpty()){
                        Toast.makeText(getActivity(), "Pet not found", Toast.LENGTH_LONG).show();

                    }else{
                        rows.deleteAllFromRealm();
                        updateUi();
                        Toast.makeText(getActivity(), "Pet deleted", Toast.LENGTH_LONG).show();
                    }

                }
            });

        } catch (Exception e){
            Toast.makeText(getActivity(), "Pet cannot be deleted", Toast.LENGTH_LONG).show();
        }
    }

    @Click(R.id.button_res)
    public void limpiar() {
        updateUi();
    }

    @UiThread
    void updateUi() {
        etxt_name.setText("");
    }


}
