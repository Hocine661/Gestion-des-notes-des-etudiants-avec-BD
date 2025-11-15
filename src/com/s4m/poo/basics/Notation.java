package com.s4m.poo.basics;

public class Notation {

    private float note;
    private Matiere matiere;

    //Constructeur
    public Notation(float note, Matiere matiere) {
        this.note = note;
        this.matiere = matiere;
    }

    //Getters and Setters
    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
    public Matiere getMatiere() {
        return matiere;
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }


    //Methode
    public void afficher() {
        System.out.println("Matière : " + matiere.getNom() + " || Coefficient : " + matiere.getCoef() + " || Note : " + note);
    }
}
