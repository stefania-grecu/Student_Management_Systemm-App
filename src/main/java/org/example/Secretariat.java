package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Secretariat {
    //array listul studenti contine baza de date in care sunt toti studentii
    //si cursuri contine baza de date in care sunt toate cursurile
    ArrayList<Student> studenti;
    ArrayList<Curs> cursuri;

    //constructor pentru Secretariat
    public Secretariat() {
        this.studenti = new ArrayList<>();
        this.cursuri = new ArrayList<>();
    }

    public void adaugaStudent(String nume, String program) throws ErrorDuplicate {
        //cauta studentul cunumele primit ca parametru si daca il gaseste intoarce eroare
        for (Student s : studenti) {
            if (s.getNume().equals(nume)) {
                throw new ErrorDuplicate(nume);
            }
        }

        // altfel il creeaza conform programului de studii si il adauga in array
        Student student = null;
        if (program.equals("licenta")) {
            student = new StudentLicenta(nume);
        } else if (program.equals("master")) {
            student = new StudentMaster(nume);
        }

        studenti.add(student);
    }

    //cauta studentul si il returneaza
    public Student cautaStudent(String nume) {
        for (Student s : studenti)
            if (s.getNume().equals(nume))
                return s;
        return null;
    }

    public void citesteMediile (String file) {
        File f = new File(file);
        //in l o sa retin un vector de stringuri care o sa contina toate fisierele din folderul file
        File[] l = f.listFiles();

        //parcurg vectorul si cand gasesc un fisier care incepe cu "note_" il deschidem si citim din el
        for (File i : l) {
            if (i.getName().startsWith("note_")) {
                try (BufferedReader br = new BufferedReader(new FileReader(i))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        //extragem numele studentului si media
                        String[] x = line.split(" ");

                        //si atribuim studentului media respectiva
                        Student stud = cautaStudent(x[0]);
                        if (stud != null)
                            stud.setMedie(Double.parseDouble(x[2]));
                    }
                } catch (IOException ignored) {
                }
            }
        }
    }

    //ordoneaza descrescatod dupa medie si in caz de egalitate crescator dupa nume
    public void ordoneazaMedii() {
        studenti.sort(Comparator.comparing(Student::getMedie).reversed().thenComparing(Student::getNume));
    }

    public void posteazaMediile(String file) throws IOException {
        File f = new File(file);
        //ordonarea dupa medii
        ordoneazaMedii();

        //deschiderea fisierului si scrierea in el conform cerintei
        try (FileWriter fw = new FileWriter(f, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("***");
            for (Student s : studenti) {
                out.println(s.getNume() + " - " + s.getMedie());
            }
        } catch (IOException ignored) {
        }

    }

    //in caz de contestatii actualizarea mediei
    public void contestatie(String nume, double medie) {
        Student s = cautaStudent(nume);
        s.setMedie(medie);
    }

    //adaugarea cursului in array
    public void adaugaCurs(String program, String denumire, int capacitate) {
        Curs curs = new Curs(program, denumire, capacitate);
        cursuri.add(curs);
    }

    //adaugarea in array-ul de preferinte a studentului materiile primite ca parametru
    public void adaugaPreferinte(String nume, String[] arg) {
        Student s = cautaStudent(nume);
        for (int i = 4; i < arg.length; i = i + 2) {
            s.preferinte.add(arg[i]);
        }
    }

    public void repartizare() {
        //luam fiecare student
        for (Student s : studenti) {
            int ok = 0;
            //luam in ordinea preferintelor
            for (int i = 0; i < s.preferinte.size() && ok == 0; i++) {
                String cursPref = s.preferinte.get(i);
                for (Curs c : cursuri) {
                    //caut cursul preferat in array-ul de cursuri
                    if (c.getNume().equals(cursPref)) {
                        // aflu cea mai mica medie a studentilor de la acest curs
                        double m = 10.0;
                        for (Student x : c.lista) {
                            if (x.getMedie() < m)
                                m = x.getMedie();
                        }
                        //daca media studentului este egala cu cea mai mica medie il adaugam la curs
                        if (s.getMedie() == m && c.lista.size() >= c.capacitateMaxima) {
                            c.lista.add(s);
                            ok = 1;
                        } else if (c.lista.size() < c.capacitateMaxima) {
                            //altfel il adaugam doar daca capacitatea nu a atins maximul
                            c.lista.add(s);
                            ok = 1;
                        }
                    }
                }
            }
        }

    }

    //ordoneaza studentii alfabetic dupa nume
    public void ordoneazaStudenti(Curs c) {
        c.lista.sort(Comparator.comparing(Student::getNume));
    }

    //postez cursul si studentii de la eacest curs conform cerintei
    public void posteazaCurs(String nume, String file) throws IOException {
        File f = new File(file);

        try (FileWriter fw = new FileWriter(f, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("***");
            for (Curs c : cursuri) {
                if (c.getNume().equals(nume)) {
                    ordoneazaStudenti(c);
                    out.println(nume + " (" + c.capacitateMaxima + ")");
                    for (Student s : c.lista)
                        out.println(s.getNume() + " - " + s.getMedie());
                }
            }
        } catch (IOException e) {
        }

    }

    public void posteazaStudent(String nume, String file) throws IOException {
        File f = new File(file);

        try (FileWriter fw = new FileWriter(f, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("***");

            Student s = cautaStudent(nume);

            if(s != null) {
                out.print("Student ");

                //verific daca studentul afartine clasei studentilor de la licenta sau master
                if (s instanceof StudentLicenta)
                    out.print("Licenta: ");
                else if (s instanceof StudentMaster)
                    out.print("Master: ");

                //afisez conform cerintei numele si media
                out.print(s.getNume() + " - " + s.getMedie() + " - ");

                //caut cursul la care a fost repartizat
                for (Curs c : cursuri) {
                    for (Student x : c.lista) {
                        if (x.getNume().equals(s.getNume())) {
                            out.println(c.getNume());
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
        }
    }
}
