package com.example.astrid.apprealm;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EActivity;

import io.realm.Realm;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;

    FragmentManager fragmentManager;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            ConnectFragment connectFragment = new ConnectFragment();
            fragmentManager.beginTransaction().add(R.id.contenido, connectFragment)
                    .addToBackStack(null).commit();
        }

        // Create the Realm instance
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }


    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else if ( count == 1) {
            //Shows an alert dialog on quit
            onShowQuitDialog();
        }
    }

    public void onShowQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("Do You Want To Quit?");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

}
