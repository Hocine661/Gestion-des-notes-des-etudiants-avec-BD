package com.s4m.poo.test;

import com.s4m.poo.basics.DBGestion;
import com.s4m.poo.basics.Etudiant;
import com.s4m.poo.basics.Matiere;
import com.s4m.poo.basics.Notation;

import java.util.Scanner;

public class TestEtudiant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de l'étudiant :");
        String nom = sc.nextLine();
        Etudiant e = new Etudiant(nom);

        e.save();

        System.out.println("Combien de matières voulez-vous ajouter?");
        int nbMatieres = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nbMatieres; i++) {
            System.out.println("Matière :");
            String matNom = sc.nextLine();

            //Vérifie que le coef donné est strictement positif
            int coef;
            do {
                System.out.println("Coefficient :");
                coef = sc.nextInt();
                if (coef <= 0) {
                    System.out.println("Erreur: le coefficient doit etre supérieur à 0.");

                }
            } while (coef <= 0);

            //Vérifie que la note est entre 0 et 20
            float note;
            do {
                System.out.println("Note :");
                note = sc.nextFloat();
                if (note < 0 || note > 20) {
                    System.out.println("Erreur : la note doit etre entre 0 et 20.");
                }
            } while (note < 0 || note > 20);
            sc.nextLine();

            Matiere m = new Matiere(matNom, coef);
            DBGestion.insertMatiere(m);

            Notation n = new Notation(note, m);
            e.ajouterNotation(n);
        }
        e.calculerMoyenne();
        e.genereAvis();
        e.update();
        e.afficher();

        sc.close();
    }
}