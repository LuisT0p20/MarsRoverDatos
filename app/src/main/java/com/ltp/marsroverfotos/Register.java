package com.ltp.marsroverfotos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmailRegister,edtPasswordRegister;
    Button btnCreate;
    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPassRegister);
        btnCreate = findViewById(R.id.btnCreateAccount);
        btnCreate.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edtEmailRegister, Patterns.EMAIL_ADDRESS,R.string.validation_email);
        awesomeValidation.addValidation(this,R.id.edtPassRegister, ".{6,}",R.string.validation_password);
    }

    @Override
    public void onClick(View v) {
        String email =edtEmailRegister.getText().toString();
        String password =edtPasswordRegister.getText().toString();
        if (awesomeValidation.validate()){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}