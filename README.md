[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/yw5p-AaX)
# tema-2-poo-2023

README tema2 POO

M-am folosit de ArrayList-uri pentru ca e destul de usor sa adaugi studenti si cursuri in ele, parcurgerea lor e la fel de simpla (printr-un singur for) si in plus am mai lucrat cu ele.

Am tratat un caz care nu a fost specificat in cerinte: situatia in care ul student are ca preferinte un curs de la celalalt program de studii.


DESCRIEREA IMPLEMENTARII

Am creat clasa Student cu parametrii nume, medie, un array list in care sa mentin in ordine preferintele si constructorul aferent clasei; am mai facut si niste gettere si settere pentru nume si medie.
Clasele StudentLicenta si StudentMaster mostenesc clasa Student si suprascriu numele in constructor.
Clasa Curs are ca parametri o denumire, un program, o capacitate maxima si un array list de tip Student in care o sa fie inrolati studenti de la acel curs. Am creat un contructor pentru aceasta clasa si am creat ca metode 2 gettere pentru nume si capacitate.
Clasa ErrorDuplicate este o clasa pentru o exceptie proprie.
Clasa Secretariat are ca parametri 2 array listuri: unul de tip Studenti si unul de tip Curs, ca un fel de baza de date pentru studenti si pentru cursuri. Ca metode avem:
-adaugaStudent: cauta studentul sa vada daca a mai fost adaugat in baza de date si in caz afirmativ imi apare o eroare proprie, altfel verifel la ce program de studii trebuie sa fie, il creez conform programului si il adaug in baza de date
-cautaStudent: returneaza studenul cu nemele primit ca parametru sau null
-citesteMediile: primeste ca parametru un String pe care il face folder si de acolo in "l" o sa se retina un vector cu fisierele din folderul; parcurge toate fisierele si cele care incep cu "note_" le deschide, citeste datele din ele, extrage numele si media, daca studentul exista ii seteaza media.
-posteazaMediile: primeste ca parametru un String pe care il transforma in fisier, ordoneaza mediile, deschide fisierul, scrie "***" conform cerintei, iar apoi parcurge toti studentii si ii afiseaza.
-contestatie: se cauta studentul dupa nume si i se actualizeaza media.
-adaugaCurs: creaza un curs dupa program, nume si capacitate si il adauga in baza de date.
-adaugaPreferinte: cauta studentul dupa nume, pargurge materiile primite ca parametru si le adauga in ordine la preferinte.
-repartizare: parcurgem toti studentii si preferintele lor in ordine, cautam in arrayul de cursuri cursul preferat si verificam daca cursul si studentul fac parte din acelasi program, aflam cea mai mica medie, iar daca media studentului este egala cu aceasta il adaugam la curc chiar daca depaseste pacacitatea, altfel il adaugam doar daca mai este loc; pentru cazul in care cursurile preferante nu mai au locuri sau nu au complete, mai parcurgem odata toate cursurile si unde este loc il adaugam si pe el.
-posteazCurs: deschidem fisierul, cautam cursul, si scriem in fisier numele acestuia, capacitatea maxima, si lista studentilor inrolati conform cerintei.
-posteazaStudent: deschidem fisierul, cautam studentul dupa nume si daca acesta exista verificam ce la ce program este in functie de constructorul a carei clasa a fost declarat si afisam conform cerintei datele despre el.
