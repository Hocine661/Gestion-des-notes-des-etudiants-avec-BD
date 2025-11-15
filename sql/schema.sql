-- Script à écrire sur PHPMyadmin pour créer la base de données de ce projet
CREATE DATABASE gestion_etudiants;
USE gestion_etudiants;

-- table étudiants
CREATE TABLE etudiants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    moyenne FLOAT DEFAULT 0,
    avis VARCHAR(100) DEFAULT ''
);

-- Table matières
CREATE TABLE matieres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    coef INT NOT NULL
);

-- Table notations
CREATE TABLE notations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    note FLOAT NOT NULL,
    etudiant_id INT NOT NULL,
    matiere_id INT NOT NULL,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id) ON DELETE CASCADE,
    FOREIGN KEY (matiere_id) REFERENCES matieres(id) ON DELETE CASCADE
);