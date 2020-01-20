package com.example.thofiq;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class FillDetails extends AppCompatActivity {

    String email;
    EditText abc;
    String name;
    EditText username;

    CheckBox term;
    EditText location;
    FirebaseFirestore db;
    DocumentReference ref;

    private int CONTACT_PERMISSION_CODE=1;
    public static final int RequestPermissionCode = 7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details);

        db = FirebaseFirestore.getInstance();
//        ref = db.collection("users").document("aaaaa");


        username = (EditText) findViewById(R.id.fullName);


        abc = (EditText) findViewById(R.id.userEmailId);
        location = (EditText) findViewById(R.id.location);
        term = (CheckBox) findViewById(R.id.terms_conditions);

        findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                email = abc.getText().toString();
                name = username.getText().toString();

               EmailValidate(email);


//                // If All permission is enabled successfully then this block will execute.
//                if (CheckingPermissionIsEnabledOrNot()) {
//                    Toast.makeText(FillDetails.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
//            }
//
//            // If, If permission is not enabled then else condition will execute.
//                else {
//
//                //Calling method to enable permission.
//                RequestMultiplePermission();
//
//            }
                // Create a new user with a first and last name
                Map user = new HashMap();
                user.put("name", username);
                user.put("Email",abc);
                user.put("location", location);
//                Toast.makeText(FillDetails.this,"data send",Toast.LENGTH_LONG).show();


                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                               Toast.makeText(FillDetails.this,"data send to database",Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FillDetails.this,"aaaaaaa",Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });
    }


//Permission function starts from here
            private void RequestMultiplePermission() {

                // Creating String Array with Permissions.
                ActivityCompat.requestPermissions(FillDetails.this, new String[]
                        {
                                CAMERA,
                                READ_EXTERNAL_STORAGE,
                                READ_CONTACTS
                        }, RequestPermissionCode);

            }

                // Calling override method.
            @Override
            public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
                switch (requestCode) {

                    case RequestPermissionCode:

                        if (grantResults.length > 0) {

                            boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                            boolean RecordAudioPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                            boolean SendSMSPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;


                            if (CameraPermission && RecordAudioPermission && SendSMSPermission) {

                                Toast.makeText(FillDetails.this, "Permission Granted", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(FillDetails.this, "Permission Denied", Toast.LENGTH_LONG).show();

                            }
                        }

                        break;
                }
            }

            // Checking permission is enabled or not using function starts from here.
            public boolean CheckingPermissionIsEnabledOrNot() {

                int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
                int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);
                int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);


                return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                        SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                        ThirdPermissionResult == PackageManager.PERMISSION_GRANTED;
            }

            void EmailValidate(String email) {

                if (email.matches("[a-zA-Z0-9._-]+@[a-z]*\\.[a-z]{2,3}")) {
                    Toast.makeText(FillDetails.this, "valid", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(FillDetails.this, "invalid", Toast.LENGTH_SHORT).show();
                }


            }

            ;

            void namevalidate(String name) {
                if (name.matches("^[a-zA-Z\\s]+$")) {
                    Toast.makeText(FillDetails.this, "valid name", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FillDetails.this, "invalid name", Toast.LENGTH_SHORT).show();
                }
            }


            void checkbox(CheckBox checkbox) {
                final CheckBox checkBox = (CheckBox) findViewById(R.id.terms_conditions);
                if (checkBox.isChecked()) {
                    Toast.makeText(FillDetails.this, "checked c", Toast.LENGTH_SHORT).show();
                    checkBox.setChecked(false);
                } else {
                    Toast.makeText(FillDetails.this, " not checked ", Toast.LENGTH_SHORT).show();
                }

            }





    }