package com.project_moviles2.miShopVirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project_moviles2.miShopVirtual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    TextView jtvmensaje1;
    EditText jetemail, jetpassword;
    Button jbtiniciar, jbtregistrar;
    ImageView jivimagen1;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        getSupportActionBar().hide();

        jtvmensaje1=findViewById(R.id.tvmensaje1);
        jetemail=findViewById(R.id.etemail);
        jetpassword=findViewById(R.id.etpassword);
        jbtiniciar=findViewById(R.id.btiniciar);
        jbtregistrar=findViewById(R.id.btregistrar);
        jivimagen1 =findViewById(R.id.ivimagen1);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_abajo);
        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_abajo);

        jtvmensaje1.setAnimation(animation1);
        jivimagen1.setAnimation(animation2);

        String email_retorno=getIntent().getStringExtra("coleccion");
        if(email_retorno == null){
            jetemail.setText("");
        }else{
            jetemail.setText(email_retorno);
        }

    }
    public void login (View view){
       final String email, Password ;

        email=jetemail.getText().toString();
        Password=jetpassword.getText().toString();

        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Email no ingresado.", Toast.LENGTH_SHORT).show();
            jetemail.requestFocus();
        }else if(Password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe ingresar la contraseña.", Toast.LENGTH_SHORT).show();
            jetpassword.requestFocus();
        }else{
            DocumentReference docRef = db.collection("users").document(email);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("Mensaje1", "DocumentSnapshot data: " + document.getData());
                            String password = document.getString("Password");

                            if (password.equals(Password)) {
                                String rol = document.getString("Rol");
                                if (rol.equals("Seller")){
                                    Intent intent = new Intent(getApplicationContext(), View_UserSeller.class);
                                    intent.putExtra("coleccion", email);
                                    intent.putExtra("rol", rol);
                                    intent.putExtra("password", password);
                                    //guardarPreferencias();
                                    startActivity(intent);
                                }
                                else if (rol.equals("user")){
                                    Intent intent = new Intent(getApplicationContext(), View_User.class);
                                    intent.putExtra("coleccion", email);
                                    intent.putExtra("rol", rol);
                                    intent.putExtra("password", password);
                                    startActivity(intent);
                                }
                                jetpassword.setText("");

                            } else {
                                Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                jetpassword.setText("");
                                jetpassword.requestFocus();
                            }

                        } else {
                            Log.d("mensaje2", "No such document");
                            Toast.makeText(getApplicationContext(), "Usuario no extiste, por favor confirmar", Toast.LENGTH_LONG).show();

                            jetemail.requestFocus();
                            jetpassword.setText("");
                        }
                    } else {

                        Log.d("Mensaje3", "get failed with ", task.getException());
                        //Toast.makeText(getApplicationContext(), "Usuario no extiste, por favor confirmar", Toast.LENGTH_LONG).show();

                        jetemail.requestFocus();
                        jetpassword.setText("");
                    }

                }
                
            });
        }
    }

    public void Register (View view){

        Intent intent= new Intent(getApplicationContext(),shop_register.class);
        startActivity(intent);

    }
}