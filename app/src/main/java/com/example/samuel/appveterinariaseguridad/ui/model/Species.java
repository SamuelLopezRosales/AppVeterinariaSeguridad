package com.example.samuel.appveterinariaseguridad.ui.model;

public class Species {
    private int id;
    private String name;
    private String photo="";
    private int imagen;

    public Species(int id, String name, String photo, int imagen) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen(){
        return imagen;
    }

    public void setImagen(int imagen){
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getImageUrl() {

       return "https://mui.today/__export/1563571267039/sites/mui/img/2019/07/19/vaca.jpg_691115875.jpg";
        //return this.photo;

    }
}
