package com.intern.seatmatedriver;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    private EditText phoneText;
    Button verifyButton;
    private Button sendButton;
    // private Button resendButton;
    // private Button signoutButton;

    String number;
    SpotsDialog waitingdilog;
    FirebaseAuth mAuth;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private FirebaseAuth fbAuth;
    CountryCodePicker ccp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        phoneText = (EditText) findViewById(R.id.phoneText);
        //   codeText = (EditText) findViewById(R.id.codeText);
        verifyButton = (Button) findViewById(R.id.verifyotp);

        sendButton = (Button) findViewById(R.id.send_otp);



        //  resendButton = (Button) findViewById(R.id.resendButton);

        // root=findViewById(R.id.root);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneText);
        mAuth=FirebaseAuth.getInstance();


        verifyButton.setEnabled(false);
//       resendButton.setEnabled(false);
//       signoutButton.setEnabled(false);
//       statusText.setText("Signed Out");

        fbAuth = FirebaseAuth.getInstance();
        waitingdilog=new SpotsDialog(LoginActivity.this);



    }


    ///ON click sendbtn
    public void sendCode(View view) {
        waitingdilog.show();
        number = ccp.getFullNumberWithPlus();


        // Snackbar.make(root, "Wait A While..", Snackbar.LENGTH_SHORT).show();
        Toast.makeText(this, "Please Wait!", Toast.LENGTH_SHORT).show();
        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);
        waitingdilog.dismiss();

    }

    private void setUpVerificatonCallbacks() {

        verificationCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential){
                        signInWithPhoneAuthCredential(credential);


                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            Toast.makeText(LoginActivity.this, "Invalid Credential: "
                                    + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // SMS quota exceeded
                            Toast.makeText(LoginActivity.this, "Error: "
                                    + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Please Try Again Later. "
                                    , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        Toast.makeText(LoginActivity.this, "One Time Password Sent "
                                , Toast.LENGTH_SHORT).show();
                        phoneVerificationId = verificationId;
                        resendToken = token;

                        verifyButton.setEnabled(true);
                        sendButton.setEnabled(false);
                        // resendButton.setEnabled(true);
                    }
                };
    }

    //On click verifybtn;
    public void verifyCode(View view) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        //alertDialog.setTitle("Withdraw Money");
        //  alertDialog.setMessage("Plz Fill Your Details...");

        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View quantity_layout = inflater.inflate(R.layout.entermanually_layout ,null);
        alertDialog.setView(quantity_layout);
        final EditText a,b,c,d,e,f;
        a=quantity_layout.findViewById(R.id.a);
        b=quantity_layout.findViewById(R.id.b);
        c=quantity_layout.findViewById(R.id.c);
        d=quantity_layout.findViewById(R.id.d);
        e=quantity_layout.findViewById(R.id.e);
        f=quantity_layout.findViewById(R.id.f);
        alertDialog.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String code = a.getText().toString().trim()+
                        b.getText().toString().trim()+ c.getText().toString().trim()+
                        d.getText().toString().trim()+ e.getText().toString().trim()+
                        f.getText().toString().trim();
                if(code.length()>=6) {
                    waitingdilog.show();


                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
                    signInWithPhoneAuthCredential(credential);
                    waitingdilog.dismiss();
                }else {
                    Toast.makeText(LoginActivity.this, "Invalid One Time Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.show();






    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {



                            // signoutButton.setEnabled(true);
                            phoneText.setText("");
                            // statusText.setText("Signed In");
                            // resendButton.setEnabled(false);
                            // verifyButton.setEnabled(false);
                            FirebaseUser user = task.getResult().getUser();
                            String phoneNumber = user.getPhoneNumber();
                            waitingdilog.dismiss();

                            Intent intent=new Intent(LoginActivity.this,Home.class);
                            startActivity(intent);
                            finish();



                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this, "Invalid Request", Toast.LENGTH_SHORT).show();
                                // Snackbar.make(root, "Error:"+task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    //On Click Resend Code btn
    public void resendCode(View view) {

        number = ccp.getFullNumberWithPlus();

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }

    public void signOut(View view) {
        fbAuth.signOut();
        //  statusText.setText("Signed Out");
        //  signoutButton.setEnabled(false);
        sendButton.setEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth
                firebaseAuth;
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(this,Home.class);
            startActivity(intent);
            finish();
        }



    }
}

