package com.david.pokemon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Pokemon implements Serializable {
@PrimaryKey(autoGenerate = true)
    //POJO --> Guardar objetos JSON

    private int id;
    private String name;
    private int weight;
    private int height;
    private String image;
    private String detailsUrl;

    public Pokemon(){



    }

    public void setId(int id) {this.id = id;}
    public int getId() {
        return id;
    }


    //hacer constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }


    //crear get i set de detailsUrl

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", detailsUrl='" + detailsUrl + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

}
