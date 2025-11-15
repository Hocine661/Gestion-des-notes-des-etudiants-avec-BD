package com.s4m.poo.basics;

public class Matiere
{
    private int id;
    private String nom;
    private int coef;

    //Constructeur
    public Matiere(String nom, int coef) {
        this.nom = nom;
        this.coef = coef;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getCoef() {
        return coef;
    }
    public void setCoef(int coef) {
        this.coef = coef;
    }

   // Méthode
    public void afficher() {
        System.out.println("Matière : " + nom + " || Coefficient : " + coef);
    }
}

