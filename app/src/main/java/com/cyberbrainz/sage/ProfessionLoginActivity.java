package com.cyberbrainz.sage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessionLoginActivity extends AppCompatActivity {

    private EditText memail,mpassword;
    private Button mlogin,mregister;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession_login);
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user!=null){
                Intent intent = new Intent(ProfessionLoginActivity.this, ProfessionMapActivity.class);
                startActivity(intent);
                finish();
                return;

            }
            }
        };


         memail = (EditText) findViewById(R.id.email);
         mpassword = (EditText) findViewById(R.id.password);

         mlogin = (Button) findViewById(R.id.login);
         mregister = (Button) findViewById(R.id.register);

         mregister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final String email = memail.getText().toString();
                 final String password = mpassword.getText().toString();
                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(ProfessionLoginActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (!task.isSuccessful())
                             Toast.makeText(ProfessionLoginActivity.this, "Sign up error", Toast.LENGTH_SHORT).show();


                         else {
                             String user_id = mAuth.getCurrentUser().getUid();
                             DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Profession").child(user_id);
                             current_user_db.setValue(true);
                         }
                     }


                 });
             }
         });

         mlogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final String email = memail.getText().toString();
                 final String password = mpassword.getText().toString();
                 mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(ProfessionLoginActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (!task.isSuccessful())
                             Toast.makeText(ProfessionLoginActivity.this, "Sign in error", Toast.LENGTH_SHORT).show();

                     }
                 });
             }
         });

}

    @Override
    protected void onStart() {
                super.onStart();
                mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
