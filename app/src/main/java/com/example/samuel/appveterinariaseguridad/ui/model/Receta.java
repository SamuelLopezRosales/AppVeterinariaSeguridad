package com.example.samuel.appveterinariaseguridad.ui.model;

import java.io.Serializable;

public class Receta implements Serializable {

    //etDueno, etDireccion, etPaciente, etEdad, etEspecie, etPeso
    private String dueno;
    private String direccion;
    private String paciente;
    private String edad;
    private String peso;
    private String patologia;
    private String tratamiento;
    private int imagen;


    public Receta(String dueno, String direccion, String paciente, String edad, String peso, String patologia, String tratamiento, int imagen) {
        this.dueno = dueno;
        this.direccion = direccion;
        this.paciente = paciente;
        this.edad = edad;
        this.peso = peso;
        this.patologia = patologia;
        this.tratamiento = tratamiento;
        this.imagen =  imagen;
    }

    public Receta(){

    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }


    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getImagen(){
        return imagen;
    }

    public void setImagen(int imagen){
        this.imagen = imagen;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
