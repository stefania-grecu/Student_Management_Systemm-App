package org.example;

import javax.sql.rowset.serial.SerialStruct;
import java.util.ArrayList;

public class Curs {
    String denumire, program;
    int capacitateMaxima;
    //un array list in care sa se adauge studentii inrolati la acel curs
    ArrayList<Student> lista;

    //constructorul pentru clasa Curs
    public Curs(String program, String denumire, int capacitateMaxima) {
        this.program = program;
        this.denumire = denumire;
        this. capacitateMaxima = capacitateMaxima;
        lista = new ArrayList<>();
    }
    //gettere pentru denumire si capacitatea maxima
    public String getNume() {
        return denumire;
    }
    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }
    public String getProgram() {
        return program;
    }
}
