package com.project_moviles2.miShopVirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class shop_register extends AppCompatActivity {

    TextView jtvmensaje2;
    EditText jetname, jetemailregister, jetcountry, jetcity, jetpassword2, jetrole, jetshop;
    Button jbtregistro, jbtregresar;
    RadioButton jrbuser, jrbseller;
    ImageView jivimagen2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        String name, emailregister,country, city, password2, role, shop;

        name=jetname.getText().toString();
        emailregister=jetemailregister.getText().toString();
        country=jetcountry.getText().toString();
        city=jetcity.getText().toString();
        password2=jetpassword2.getText().toString();
        role=jetrole.getText().toString();
        shop=jetrole.getText().toString();

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
        else if (role.isEmpty()){

            Toast.makeText(getApplicationContext(),"Se debe ingresar role",Toast.LENGTH_LONG).show();
            jetrole.requestFocus();

        }
        else if (shop.isEmpty()){

            Toast.makeText(getApplicationContext(),"Se debe ingresar shop",Toast.LENGTH_LONG).show();
            jetshop.requestFocus();

        }else{
            Toast.makeText(getApplicationContext(), "El Registro se ha realizado correctamente", Toast.LENGTH_SHORT).show();
        }

    }


    public void Back (View view){

        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}