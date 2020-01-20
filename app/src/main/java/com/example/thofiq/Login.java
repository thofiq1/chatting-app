package com.example.thofiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    EditText otp;
    FirebaseAuth firebaseAuth;
    Button resendBtn;
    String Mob, verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        otp = findViewById(R.id.GetOtp);
        firebaseAuth = FirebaseAuth.getInstance();

        Mob = getIntent().getExtras().getString("mobile");
        verificationId = getIntent().getExtras().getString("OTP");

        findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                String code = otp.getText().toString().trim();

                if ((code.isEmpty() || code.length() < 6)) {

                    otp.setError("Enter code...");
                    otp.requestFocus();
                    return;
                }
                verifyCode(code);

            }
        });
        findViewById(R.id.resendOTP).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                resendVerificationCode(Mob);
            }
        });
    }
    public void resendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                Login.this,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(Login.this, "OTP Sent Successfully",Toast.LENGTH_LONG).show();

            verificationId = s;
//            mob.setText("");



        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
//            Toast.makeText(MainActivity.this, ,Toast.LENGTH_LONG).show();

//            if (code != null) {
////                progressBar.setVisibility(View.VISIBLE);
//                verifyCode(code);
//            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Login.this,"Something went wrong please try again later" ,Toast.LENGTH_LONG).show();

        }
    };

    private void verifyCode(String code) {


        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);


        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        try {
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                                if (isNew) {
                                    Intent intent = new Intent(Login.this, FillDetails.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    startActivity(intent);
                                    return;
                                }
                                Toast.makeText(Login.this, "com", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Login.this, MainPage.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "not com", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
        } catch (Exception e) {
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}
