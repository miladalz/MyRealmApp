package com.milad.myrealmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.StackView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.milad.myrealmapp.model.DataObject;

import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private EditText nameEdt, ageEdt;
    private Button addDataBtn;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdt = findViewById(R.id.idEdtName);
        ageEdt = findViewById(R.id.idEdtAge);
        addDataBtn = findViewById(R.id.idBtnAddData);
        // on below line initializing the variable for realm.
        realm = Realm.getDefaultInstance();

        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEdt.getText().toString().isEmpty() && ageEdt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your name and age", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToDB(nameEdt.getText().toString(), ageEdt.getText().toString());
                    Toast.makeText(MainActivity.this, "Data has been added to database..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addDataToDB(String name, String age) {
        DataObject dataObject = new DataObject();
        // on below line we are getting id for the course which we are storing.
        Number id = realm.where(DataObject.class).max("id");
        // on below line we are creating a variable for our id.
        long nextId;
        if (id == null) {
            nextId = 1;
        } else {
            nextId = id.intValue() + 1;
        }
        dataObject.setId(nextId);
        dataObject.setName(name);
        dataObject.setAge(age);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dataObject);
            }
        });
    }
}