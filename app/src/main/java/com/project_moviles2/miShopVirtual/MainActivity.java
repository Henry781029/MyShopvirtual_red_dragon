package com.project_moviles2.miShopVirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView jtvmensaje1;
    EditText jetemail, jetpassword;
    Button jbtiniciar, jbtregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jtvmensaje1=findViewById(R.id.tvmensaje1);
        jetemail=findViewById(R.id.etemail);
        jetpassword=findViewById(R.id.etpassword);
        jbtiniciar=findViewById(R.id.btiniciar);
        jbtregistrar=findViewById(R.id.btregistrar);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_abajo);

        jtvmensaje1.setAnimation(animation1);

    }
    public void login (View view){
        String email = null, password = null;

        email=jetemail.getText().toString();
        password=jetpassword.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Email no ingresado.", Toast.LENGTH_SHORT).show();
            jetemail.requestFocus();
        }else if(password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe ingresar la contraseña.", Toast.LENGTH_SHORT).show();
            jetpassword.requestFocus();
        }else{
            Toast.makeText(getApplicationContext(), "Ha ingresado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void Register (View view){

        Intent intent= new Intent(getApplicationContext(),shop_register.class);
        startActivity(intent);

    }
}