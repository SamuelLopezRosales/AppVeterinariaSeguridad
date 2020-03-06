package com.example.samuel.appveterinariaseguridad.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.samuel.appveterinariaseguridad.R;

import java.io.File;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Veterinaria2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearReceta);
        linearLayout.setOnClickListener(this);

        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.linearConsultar);
        linearLayout2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // case R.id.ivBackButton:
            // finish();
            // break;

            case R.id.linearReceta:
                Intent i1 = new Intent(OptionsActivity.this, CrearRecetaActivity.class);
                startActivity(i1);
                break;

            case R.id.linearConsultar:
               // Intent il2 = new Intent(OptionsActivity.this, ListarRecetasActivity.class);
               // startActivity(il2);
                Intent i = new Intent(OptionsActivity.this, ListarRecetasActivity.class);
                startActivity(i);

                break;

            case R.id.LinearcosultarRecetas:

                //Intent i = new Intent(OptionsActivity.this, ListarRecetasActivity.class);
                //startActivity(i);
                break;
        }
    }
}
