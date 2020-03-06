package com.example.samuel.appveterinariaseguridad.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samuel.appveterinariaseguridad.R;
import com.example.samuel.appveterinariaseguridad.ui.model.Listado;
import com.example.samuel.appveterinariaseguridad.ui.model.Receta;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class CrearRecetaActivity extends AppCompatActivity {
    private EditText etDueno, etDireccion, etPaciente, etEdad, etPatologia, etPeso, etTratamiento;
    private Button btnEnviar;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Veterinaria2/recetas.pdf";
    public String path_clave = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Veterinaria2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_receta);

        findView();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Saco los datos de la lista //
                String dueno = etDueno.getText().toString();
                String direccion = etDireccion.getText().toString();
                String paciente = etPaciente.getText().toString();
                String edad = etEdad.getText().toString();
                String peso = etPeso.getText().toString();
                String patologia = etPatologia.getText().toString();
                String tratamiento = etTratamiento.getText().toString();

                // RUTA DE LA SECRET KEY//



                // validar que no esten vacios ni nulos //
                if(dueno!=null && !dueno.isEmpty() && direccion!=null && !direccion.isEmpty() &&
                        paciente!=null && !paciente.isEmpty() && edad!=null
                        && !edad.isEmpty() && patologia!=null && !patologia.isEmpty()
                        && peso!=null && !peso.isEmpty()){

                    Listado recetas = new Listado();
                    recetas.LeerFichero();
                    recetas.NuevaReceta(dueno, direccion, paciente, edad, peso, patologia, tratamiento, R.drawable.canino);
                    recetas.ListarDatos();
                    // receta a enviar //
                    Receta receta_enviar = new Receta(dueno,direccion,paciente,edad,peso, patologia, tratamiento, R.drawable.canino);
                    // Toast
                    File desFile2 = new File(path_clave+"/"+paciente+"_secretKey.txt");
                    File desFile3 = new File(path_clave+"/"+paciente+"_rec_encript.txt");

                    KeyGenerator kg = null;
                    String stringKey="";
                    SecretKey keyOriginal = null;

                    try {
                        kg = KeyGenerator.getInstance("DES");
                        SecretKey secretKey = kg.generateKey();

                        // convierto la key a String //
                        stringKey = Base64.encodeToString(secretKey.getEncoded(),Base64.DEFAULT);
                        FileWriter w = new FileWriter(desFile2);

                        BufferedWriter bw = new BufferedWriter(w);

                        PrintWriter wr = new PrintWriter(bw);
                        wr.write(stringKey);
                        wr.close();
                        bw.close();


                        // HAORA ENCRIPTAR EL ARCHIVO//
                        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);


                        FileOutputStream fos = new FileOutputStream(desFile3);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        CipherOutputStream cos = new CipherOutputStream(bos, desCipher);
                        ObjectOutputStream oos = new ObjectOutputStream(cos);
                        oos.writeObject(receta_enviar);
                        oos.close();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    }


                    // RECETA A ENVIAR //



                    Toast.makeText(CrearRecetaActivity.this, "Registro creado co exito", Toast.LENGTH_SHORT).show();



                    // ENVIAR DOCUMNETO POR GMAIL //
                    String[] mailto = {""};
                    Uri uri = Uri.fromFile(new File(path));
                    Uri uri2 = Uri.fromFile(new File(path_clave+"/"+paciente+"_secretKey.txt"));
                    Uri uri3 = Uri.fromFile(new File(path_clave+"/"+paciente+"_rec_encript.txt"));

                    ArrayList<Uri> listUris = new ArrayList<Uri>();
                    listUris.add(uri3);
                    Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RECETA");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Envió la receta encryptada y su clave ");
                    emailIntent.setType("application/pdf");
                    emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, listUris);

                    startActivity(Intent.createChooser(emailIntent, "Send email using:"));

                }else {
                    Toast.makeText(CrearRecetaActivity.this, "Tienes un error al ingresar los datos ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void findView(){
        etDueno = findViewById(R.id.editTextDueno);
        etDireccion = findViewById(R.id.editTextDireccion);
        etPaciente = findViewById(R.id.editTextPaciente);
        etEdad = findViewById(R.id.editTextEdad);
        etPeso = findViewById(R.id.editTextPeso);
        etPatologia = findViewById(R.id.editTextPatologia);
        etTratamiento = findViewById(R.id.editTextTratamiento);
        btnEnviar = findViewById(R.id.buttonEnviar);
    }
}
