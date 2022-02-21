package com.example.google_bd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference database;
 @Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     EditText edtPhone = findViewById(R.id.phone);
     EditText edtEmail = findViewById(R.id.email);
     EditText etpassword = findViewById(R.id.password);
     Button btn = findViewById(R.id.signup);

     btn.setOnClickListener(view -> {

         String phone = edtPhone.getText().toString();
         String email = edtEmail.getText().toString();
         String password = etpassword.getText().toString();
         database = FirebaseDatabase
                 .getInstance("https://bd-71474-default-rtdb.firebaseio.com/")
                 .getReference().child("REIN");
         User user = new User(phone, email, password);

         database.child(phone).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                 edtPhone.getText().clear();
                 edtEmail.getText().clear();
                 etpassword.getText().clear();
             }

         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(getApplicationContext(), "Произвошла ошибка",
                         Toast.LENGTH_LONG).show();
             }
         });
     });
 }
}
