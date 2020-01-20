package com.example.thofiq;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    private int CONTACT_PERMISSION_CODE=1;
    public static final int RequestPermissionCode = 7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_details);


        username = (EditText) findViewById(R.id.fullName);
        username.setText(";nsdxjvhdsxzn");

        abc = (EditText) findViewById(R.id.userEmailId);
        location = (EditText) findViewById(R.id.location);
        term = (CheckBox) findViewById(R.id.terms_conditions);

        findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                email = abc.getText().toString();
                name = username.getText().toString();
                abc.setText("aaaaaaaaaaaaa");
                EmailValidate(email);


                // If All permission is enabled successfully then this block will execute.
                if (CheckingPermissionIsEnabledOrNot()) {
                    Toast.makeText(FillDetails.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
                }

                // If, If permission is not enabled then else condition will execute.
                else {

                    //Calling method to enable permission.
                    RequestMultiplePermission();

                }
            }
        });
    }

            //    private void requestStoragePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_CONTACTS)) {
//
//            new AlertDialog.Builder(this)
//                    .setTitle("Permission needed")
//                    .setMessage("This permission is needed because of this and that")
//                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(FillDetails.this,
//                                    new String[] {Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
//                        }
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .create().show();
//
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[] {Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode ==CONTACT_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
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