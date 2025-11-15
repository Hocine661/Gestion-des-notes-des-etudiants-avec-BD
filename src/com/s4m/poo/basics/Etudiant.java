package com.s4m.poo.basics;

import java.util.ArrayList;

public class Etudiant {
    private int id;
    private String nom, avis;
    private float moyenne;
    private ArrayList<Notation> notations;

    //Constructeur
    public Etudiant(String nom) {
        this.nom = nom;
        this.notations = new ArrayList<>();
        this.moyenne = 0;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }
    //Pas de setter pour id car il est générer automatiquement par la base de donnés, si on le mit on risque de le modidier manuellemnt
    public String getNom() {

        return nom;
    }
    public void setNom(String nom) {

        this.nom = nom;
    }
    public String getAvis() {

        return avis;
    }
    public void setAvis(String avis) {

        this.avis = avis;
    }
    public float getMoyenne() {
        return moyenne;
    }
    public void setMoyenne(float moyenne) {

        this.moyenne = moyenne;
    }
    public ArrayList<Notation> getNotations() {

        return notations;
    }
    public void setNotations(ArrayList<Notation> notations) {
        this.notations = notations;
    }

    //Methodes
    public void save() {
        this.id = DBGestion.insertEtudiant(this);
    }
    public void update() {
        DBGestion.updateEtudiant(this);
    }
    public void loadNotations() {
        this.notations = DBGestion.getNotations(this.id);
    }
    public void calculerMoyenne() {
        if (notations.isEmpty()) {
            this.moyenne = 0;
            return;
        }
        int sumCoef = 0;
        float sumCoefxNote = 0;
        for (Notation n : this.notations) {
            sumCoef += n.getMatiere().getCoef();
            sumCoefxNote += n.getMatiere().getCoef() * n.getNote();
        }
        this.moyenne = sumCoefxNote/ sumCoef ;
    }


    public void genereAvis() {
        this.avis = this.moyenne >= 10 ? "Passe en classe supérieur" : "Autorisé à redoubler";
    }


    public void ajouterNotation(Notation n) {
        DBGestion.insertNotation(this.id, n);
        notations.add(n);
    }


    public void afficher() {
        System.out.println("\n Information de l'étudiant :");
        System.out.println("Id :" + this.id);
        System.out.println("Nom :" + this.nom);
        for (Notation n : this.notations) {
            n.afficher();
        }
        System.out.println("Moyenne :" + this.moyenne);
        System.out.println("Avis :" + this.avis);
    }
}
