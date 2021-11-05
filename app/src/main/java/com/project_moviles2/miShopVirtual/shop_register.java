package com.project_moviles2.miShopVirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class shop_register extends AppCompatActivity {

    TextView jtvmensaje2;
    EditText jetname, jetemailregister, jetcountry, jetcity, jetpassword2, jetrole, jetshop;
    Button jbtregistro, jbtregresar;
    RadioButton jrbuser, jrbseller;
    ImageView jivimagen2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);

        getSupportActionBar().hide();

        jtvmensaje2=findViewById(R.id.tvmensaje2);
        jetname=findViewById(R.id.etname);
        jetemailregister=findViewById(R.id.etemailregister);
        jetcountry=findViewById(R.id.etcountry);
        jetcity=findViewById(R.id.etcity);
        jetpassword2=findViewById(R.id.etpassword2);
        jrbuser = findViewById(R.id.rbuser);
        jrbseller = findViewById(R.id.rbseller);
        jetshop=findViewById(R.id.etshop);
        jbtregistro=findViewById(R.id.btregistro);
        jbtregresar=findViewById(R.id.btregresar);
        jivimagen2=findViewById(R.id.ivimagen2);

        jbtregistro.setCursorVisible(false);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_abajo);
        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.opaco);

        jtvmensaje2.setAnimation(animation1);
        jivimagen2.setAnimation(animation2);

    }
    public void Registrar (View view){
        final String name, emailregister,country, city, password2, role, shop;

        name=jetname.getText().toString();
        emailregister=jetemailregister.getText().toString();
        country=jetcountry.getText().toString();
        city=jetcity.getText().toString();
        password2=jetpassword2.getText().toString();
        shop=jetshop.getText().toString();

        if (name.isEmpty() ){

            Toast.makeText(getApplicationContext(),"El campo 'name' debe ser diligenciado",Toast.LENGTH_LONG).show();
            jetname.requestFocus();
        }
        else if (emailregister.isEmpty()){
            Toast.makeText(getApplicationContext(),"El campo 'E-mail' debe ser diligenciado",Toast.LENGTH_LONG).show();
            jetemailregister.requestFocus();
        }

        else if (country.isEmpty()){
            Toast.makeText(getApplicationContext(),"El campo 'Pais' debe ser diligenciado",Toast.LENGTH_LONG).show();
            jetcountry.requestFocus();

        }
        else if (city.isEmpty()){
            Toast.makeText(getApplicationContext(),"El campo 'Ciudad' debe ser diligenciado",Toast.LENGTH_LONG).show();
            jetcity.requestFocus();

        }
        else if (password2.isEmpty()){

            Toast.makeText(getApplicationContext(),"Se debe ingresar clave",Toast.LENGTH_LONG).show();
            jetpassword2.requestFocus();

        }
        else if (password2.length()<6){

            Toast.makeText(getApplicationContext(),"La contraseña debe contener  6 o más caracteres",Toast.LENGTH_LONG).show();
            jetpassword2.requestFocus();

        }
        else if (shop.isEmpty()){

            Toast.makeText(getApplicationContext(),"Se debe ingresar shop",Toast.LENGTH_LONG).show();
            jetshop.requestFocus();

        }else{

            DocumentReference docRef= db.collection("users").document(emailregister);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document =task.getResult();
                        if(document.exists()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(shop_register.this);
                            builder.setTitle("Usuario ya existe!!");
                            builder.setMessage("Deseas iniciar sesion con:\n "+emailregister+"?")
                                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.putExtra("coleccion", emailregister);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "Por favor crear cuenta con otro nombre de usuario", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }).show();


                        }
                        else{
                            Map<String, Object> user = new HashMap<>();

                            user.put("Password", password2);

                            if(jrbseller.isChecked()){
                                user.put("Rol","Seller");
                            }else if (jrbuser.isChecked()){
                                user.put("Rol", "user");
                            }

                            user.put("Name", name);
                            user.put("Country", country);
                            user.put("City", city);

                            user.put("Name", name);
                            user.put("Country", country);
                            user.put("City", city);
                            user.put("Shop", shop);

                            db.collection("users").document(emailregister)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("Registro ok", "DocumentSnapshot successfully written!");
                                            Toast.makeText(getApplicationContext(), "El Registro de Usuario se ha realizado correctamente", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                                            intent.putExtra("coleccion", emailregister);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("Registro ok", "Error writing document",e);
                                        }
                                    });

                        }


                    }


                }

            });

        }

    }

    public void Autenticar (){

        String email= jetemailregister.getText().toString();
        String password=jetpassword2.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(shop_register.this,"no funciona esta mierda",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void Back (View view){

        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}