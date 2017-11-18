package com.hms.mohamedseliem.httpclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.MenuItem;
import android.widget.Button;

/**
 * Created by WIN\c00300901 on 11/17/17.
 */

public class PatientMenuActivity extends AppCompatActivity {
    private Button ApButton;
    private Button NeedDoctor;
    private Button NeedAdmin;
    private Button NeedHospital;
    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_patient_menu2);
        ApButton= (Button) findViewById(R.id.btn_appointment);
        NeedDoctor=(Button) findViewById(R.id.btn_need_doctor);
        NeedAdmin=(Button) findViewById(R.id.btn_need_admin);
        NeedHospital=(Button) findViewById(R.id.btn_hospital);

       ApButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(PatientMenuActivity.this,AppointmentActivity.class);
                startActivity(intent);
            }
        });



        NeedDoctor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(PatientMenuActivity.this,NeedDoctorActivity.class);
                startActivity(intent);
            }
        });




        NeedAdmin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(PatientMenuActivity.this,NeedAdminActivity.class);
                startActivity(intent);
            }
        });



        NeedHospital.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent(PatientMenuActivity.this,NeedHospitalActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



}