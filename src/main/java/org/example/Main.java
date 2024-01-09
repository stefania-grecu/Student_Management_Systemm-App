package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
	//System.out.println("code");

        Secretariat secretariat = new Secretariat();

        //creez stringurile pentru fisiere
        String file = "src/main/resources/" + args[0] + "/"; //contine folderul
        String fileT = file + args[0] + ".in"; //fisierul de intrare
        String fileP = file +args[0] + ".out"; //fisierul de iesire

        try (BufferedReader br = new BufferedReader(new FileReader(fileT))) {
            String line;

            //parcurg tot fisierul
            while ((line = br.readLine()) != null) {
                String[] i = line.split(" ");

                //in i[0] este numle comenzii
                //apelem metodele din Secretariat pentru fiecare comanda

                if (i[0].equals("adauga_student")) {
                    try {
                        secretariat.adaugaStudent(i[4], i[2]);
                    } catch (ErrorDuplicate e) {
                        File f = new File(fileP);

                        try (FileWriter fw = new FileWriter(f, true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println("***");
                            out.println("Student duplicat: " + i[4]);
                        } catch (IOException ignored) {
                        }
                    }
                }

                if (i[0].equals("citeste_mediile")) {
                    secretariat.citesteMediile(file);
                }

                if (i[0].equals("posteaza_mediile"))
                    secretariat.posteazaMediile(fileP);

                if (i[0].equals("contestatie"))
                    secretariat.contestatie(i[2], Double.parseDouble(i[4]));

                if (i[0].equals("adauga_curs"))
                    secretariat.adaugaCurs(i[2], i[4], Integer.parseInt(i[6]));

                if (i[0].equals("adauga_preferinte"))
                    secretariat.adaugaPreferinte(i[2], i);

                if (i[0].equals("repartizeaza"))
                    secretariat.repartizare();

                if (i[0].equals("posteaza_curs"))
                    secretariat.posteazaCurs(i[2], fileP);

                if (i[0].equals("posteaza_student"))
                    secretariat.posteazaStudent(i[2], fileP);

            }
        } catch (IOException ignored) {
        }
    }
}
