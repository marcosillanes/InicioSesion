package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.custom.Registrarse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class INICIARSESION extends AppCompatActivity {

    private EditText correo;
    private EditText contraseña;
    private Button iniciar, crear;
    private FirebaseAuth mAuth;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);

        Intent intento = new Intent(this, intento3.class);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
        correo = findViewById(R.id.EntradaEmailInicio);
        contraseña = findViewById(R.id.EntradaContraseñaInicio);
        iniciar = findViewById(R.id.button6);
        crear = findViewById(R.id.button7);
        mAuth = FirebaseAuth.getInstance();


        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(INICIARSESION.this, intento3.class);
                startActivity(intent);
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                final String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";
                final String EntradaEmailInicio = correo.getText().toString().trim();
               String EntradacontraseñaInicio = contraseña.getText().toString().trim();

                if (TextUtils.isEmpty(EntradaEmailInicio)) {
                    Toast.makeText(INICIARSESION.this, "Debes ingresar tu email", Toast.LENGTH_SHORT).show();
                    correo.requestFocus();
                    return;
                }
                if (!EntradaEmailInicio.matches(regex)) {
                    Toast.makeText(INICIARSESION.this, "Formato de email incorrecro", Toast.LENGTH_SHORT).show();
                    correo.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(EntradacontraseñaInicio)) {
                    Toast.makeText(INICIARSESION.this, "Debes ingresar tu contraseña", Toast.LENGTH_SHORT).show();
                    contraseña.requestFocus();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(EntradaEmailInicio, EntradacontraseñaInicio).
                        addOnCompleteListener(INICIARSESION.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                                    startActivity(intento);


                                    Query query = databaseReference.orderByChild("correo").equalTo(EntradaEmailInicio);
                                    query.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@androidx.annotation.NonNull DataSnapshot datasnapshot) {
                                            for (DataSnapshot datos : datasnapshot.getChildren()) {

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                                        }
                                    });

                                } else {
                                    Toast.makeText(INICIARSESION.this, "Correo y/o contrasena no es valido", Toast.LENGTH_SHORT).show();
                                     correo.setText("");
                                     contraseña.setText("");
                                }
                            }
                        });
            }
        });

    }
}