package com.hms.mohamedseliem.httpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientMenuActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);
        Button    ApButton= (Button) findViewById(R.id.btn_appointment);
        Button NeedDoctor=(Button) findViewById(R.id.btn_need_doctor);
         Button  NeedAdmin=(Button) findViewById(R.id.btn_need_admin);
        Button NeedHospital=(Button) findViewById(R.id.btn_hospital);

        ApButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(PatientMenuActivity2.this,AppointmentActivity.class);
                startActivity(intent);
            }
        });


        NeedDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(PatientMenuActivity2.this,NeedDoctorActivity.class);
                startActivity(intent);

            }
        });

        NeedAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(PatientMenuActivity2.this,NeedAdminActivity.class);
                startActivity(intent);
            }
        });

        NeedHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(PatientMenuActivity2.this,NeedHospitalActivity.class);
                startActivity(intent);
            }
        });
    }
}
