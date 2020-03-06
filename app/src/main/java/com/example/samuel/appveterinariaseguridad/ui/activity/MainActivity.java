package com.example.samuel.appveterinariaseguridad.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.samuel.appveterinariaseguridad.R;
import com.example.samuel.appveterinariaseguridad.ui.adapter.SpeciesAdapter;
import com.example.samuel.appveterinariaseguridad.ui.model.Species;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {
    private SpeciesAdapter mAdapter;
    private ArrayList<Species> listaEspecies;
    private static final int CODIGO_SOLICITUD_PERMISO=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CREAR LA CARPETA //
        if(checkearPermiso()){
            //Nuestra app tiene permiso
            crearCarpeta();

        }else{
            //Nuestra app no tiene permiso, entonces debo solicitar el mismo
            solicitarPermiso();
        }





        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_species);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        listaEspecies = new ArrayList<Species>();
        listaEspecies.add(new Species(1, "Bobino", "https://mui.today/__export/1563571267039/sites/mui/img/2019/07/19/vaca.jpg_691115875.jpg", R.drawable.vaca));
        listaEspecies.add(new Species(2, "Porcino", "", R.drawable.porcino));
        listaEspecies.add(new Species(3, "Canino", "", R.drawable.canino));
        listaEspecies.add(new Species(4, "Felino", "",R.drawable.felino));

        mAdapter = new SpeciesAdapter(this, listaEspecies);
        recyclerView.setAdapter(mAdapter);

        //Call<ArrayList<Species>> call = DiagnosticVetApiAdapter.getApiService().getSpecies();
       // call.enqueue(this);
    }

    private boolean checkearPermiso(){


        //Array de permisos
        String[] permisos={Manifest.permission.WRITE_EXTERNAL_STORAGE};

        for(String perms:permisos){
            int res=checkCallingOrSelfPermission(perms);
            if(!(res== PackageManager.PERMISSION_GRANTED)){
                return false;
            }
        }
        return true;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        boolean autorizado= true;   //Si el permiso fue autorizado

        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                for(int res:grantResults){

                    //si el usuario concedió todos los permisos
                    autorizado= autorizado && (res == PackageManager.PERMISSION_GRANTED);
                }
                break;

            default:
                //Si el usuario autorizó los permisos
                autorizado= false;
                break;
        }

        if(autorizado){
            //Si el usuario autorizó todos los permisos podemos ejecutar nuestra tarea
            crearCarpeta();
        }else {
            //Se debe alertar al usuario que los permisos no han sido concedidos
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Toast.makeText(MainActivity.this,"Los permisos de almacenamiento externo fueron denegados",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void solicitarPermiso(){

        String[] permisos={Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){         //Verificamos si la version de android del dispositivo es mayor
            requestPermissions(permisos,CODIGO_SOLICITUD_PERMISO);  //o igual a MarshMallow
        }

    }

    private void crearCarpeta(){

        File file= new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Veterinaria2"); // Creamos un archivo llamado PruebaPermisosMM


        //Verificamos si el archivo fue creado exitosamente

        if(!file.exists()) {
            boolean ff = file.mkdir();
            if (ff){
                Toast.makeText(MainActivity.this,"La carpeta fue creada exitosamente",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"La carpeta no pudo ser creada",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(MainActivity.this,"La carperta ya existe",Toast.LENGTH_SHORT).show();
        }

    }
}
