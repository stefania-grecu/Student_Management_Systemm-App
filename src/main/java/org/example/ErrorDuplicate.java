package org.example;

import java.io.*;

public class ErrorDuplicate extends Exception {
    //clasa cu propria exceptie
    public ErrorDuplicate(String nume) {
        super("Student duplicat: " + nume);
    }
}
