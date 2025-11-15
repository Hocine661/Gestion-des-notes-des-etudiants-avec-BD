package com.s4m.poo.basics;

import java.sql.*;
import java.util.ArrayList;


public class DBGestion {

    public static Connection getConnection() {
        try {
            var url = "jdbc:mysql://localhost:3306/gestion_etudiants";
            var username = "root";
            var password = "";
            var conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Insère un nouvel étudiant et donne son ID
    public static int insertEtudiant(Etudiant e) {
        var sql = "INSERT INTO etudiants(nom, moyenne, avis) VALUES (?, ?, ?)";
        try (var conn = getConnection();
             var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNom());
            ps.setFloat(2, e.getMoyenne());
            ps.setString(3, e.getAvis());
            ps.executeUpdate();
            var  rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Met à jour la moyenne et l'avis d'un étudiant
    public static void updateEtudiant(Etudiant e) {
        var sql = "UPDATE etudiants SET moyenne=?, avis=? WHERE id=?";
        try (var conn = getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, e.getMoyenne());
            ps.setString(2, e.getAvis());
            ps.setInt(3, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Insere une nouvelle matière et donne son ID
    public static int insertMatiere(Matiere m) {
        var sql = "INSERT INTO matieres(nom, coef) VALUES (?, ?)";
        try (var conn = getConnection();
             var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNom());
            ps.setInt(2, m.getCoef());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
                return m.getId();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Récupere une matière à partir de son ID
    public static Matiere getMatiereById(int id) {
        var sql = "SELECT * FROM matieres WHERE id=?";
        try (var conn = getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var m = new Matiere(rs.getString("nom"), rs.getInt("coef"));
                m.setId(rs.getInt("id"));
                return m;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Insère une notation pour un étudiant
    public static void insertNotation(int etudiantId, Notation n) {
        var sql = "INSERT INTO notations(note, etudiant_id, matiere_id) VALUES (?, ?, ?)";
        try (var conn = getConnection();
        var ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, n.getNote());
            ps.setInt(2, etudiantId);
            ps.setInt(3, n.getMatiere().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Récupère les notations d'un étudiants avec toutes les infos des matières
    public static ArrayList<Notation> getNotations(int etudiantId) {
        var list = new ArrayList<Notation>();
        var sql = "SELECT n.note, m.id AS matiere_id, m.nom, m.coef "
                +
                "From notations n JOIN matieres m ON n.matiere_id = m.id"
                +
                "WHERE n.etudiant_id=?";
        try (var conn = getConnection();
        var ps = conn.prepareStatement(sql)) {
            ps.setInt(1, etudiantId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var matiere = new
                        Matiere(rs.getString("nom"), rs.getInt("coef"));
                matiere.setId(rs.getInt("matiere_id"));
                var n = new Notation(rs.getFloat("note"), matiere);
                list.add(n);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  list;
    }
}



