package com.example.samuel.appveterinariaseguridad.ui.model;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Listado {

    private Receta receta;
    private ArrayList<Receta> ListaRecetas;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Veterinaria2";

    // constructor //
    public Listado(){
        receta = new Receta();
        ListaRecetas = new ArrayList<Receta>();
    }

    public void GuardarLista(){
        File fichero = new File(path+"/recetas.pdf");
        FileOutputStream fos;
        ObjectOutputStream cos;

        try {
            fos = new FileOutputStream(fichero);
            cos = new ObjectOutputStream(fos);
            cos.writeObject(ListaRecetas);
            cos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PedirDatos(String dueno, String direccion, String paciente, String edad, String peso, String patologia, String tratamiento, int imagen){
        receta = new Receta();
        receta.setDueno(dueno);
        receta.setDireccion(direccion);
        receta.setPaciente(paciente);
        receta.setEdad(edad);
        receta.setPeso(peso);
        receta.setPatologia(patologia);
        receta.setTratamiento(tratamiento);
        receta.setImagen(imagen);

    }

    // ESTE ES EL METODO QUE PRIMERO SE LLAMA //
    public void NuevaReceta(String dueno, String direccion, String paciente, String edad, String peso, String patologia, String tratamiento, int imagen){
        PedirDatos(dueno,direccion, paciente, edad, peso, patologia, tratamiento, imagen);
        ListaRecetas.add(receta);
        GuardarLista();
    }

    public void LeerFichero(){
        FileInputStream fis;
        ObjectInputStream ois;
        ListaRecetas = new ArrayList<>();

        try {
            fis = new FileInputStream(path+"/recetas.pdf");
            ois = new ObjectInputStream(fis);
            ListaRecetas = (ArrayList<Receta>) ois.readObject(); // LLENO EL ARRAY D EOBJETOS//
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ListarDatos(){
        Log.i("**************", "*******************");
        for(Receta Elemento:ListaRecetas){
            receta = new Receta();
            receta = Elemento;
            MostrarDatos();
        }
    }

    public void MostrarDatos(){
        Log.i("Dueno:  ", receta.getDueno());
    }

    public ArrayList<Receta> ListarDatosArray(){
        return ListaRecetas;
    }
}
