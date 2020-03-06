package com.example.samuel.appveterinariaseguridad.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.samuel.appveterinariaseguridad.R;
import com.example.samuel.appveterinariaseguridad.ui.adapter.RecetaAdapter;
import com.example.samuel.appveterinariaseguridad.ui.adapter.SpeciesAdapter;
import com.example.samuel.appveterinariaseguridad.ui.model.Listado;
import com.example.samuel.appveterinariaseguridad.ui.model.Receta;
import com.example.samuel.appveterinariaseguridad.ui.model.Species;

import java.util.ArrayList;

public class ListarRecetasActivity extends AppCompatActivity {
    private ListView listViewRecetas;
    private ArrayList<Receta> listaRecetas;
    private ArrayList<String> listaDuenos;
    private Receta receta;
    private ArrayAdapter<String> adapter;

    private RecetaAdapter mAdapter;
    //private ArrayList<Species> listaEspecies;
    //private static final int CODIGO_SOLICITUD_PERMISO=123;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_recetas);

        findView();
        listaRecetas = new ArrayList<Receta>();
        listaDuenos = new ArrayList<String>();




        // CREO OBJETO //
        Listado recetas = new Listado();
        recetas.LeerFichero();
        listaRecetas = recetas.ListarDatosArray();

        for(Receta Elemento:listaRecetas) {
            receta = new Receta();
            receta = Elemento;
            String dueno = receta.getDueno();
            listaDuenos.add(dueno);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_recetas);

        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
        //, listaDuenos);

        //listViewRecetas.setAdapter(adapter);

        mAdapter = new RecetaAdapter(this, listaRecetas);
        recyclerView.setAdapter(mAdapter);




    }

    public void findView(){

    }
}

