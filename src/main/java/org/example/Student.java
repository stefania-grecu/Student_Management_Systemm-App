package org.example;

import java.util.ArrayList;

public class Student {
    String nume;
    double medie;
    //un array list in care sa se puna in ordine preferintele pentru optionale
    //primul string reprezinta prima optiune, al doilea a doua optiune si tot asa
    ArrayList<String> preferinte;

    //constructorul pentru clasa Student
    public Student(String nume) {
        this.nume = nume;
        preferinte = new ArrayList<>();
    }
    //getters si settere pentru nume si medie
    void setNume(String nume) {
        this.nume = nume;
    }
    void setMedie(double medie) {
        this.medie = medie;
    }
    String getNume() {
        return nume;
    }
    double getMedie() {
        return medie;
    }
}
