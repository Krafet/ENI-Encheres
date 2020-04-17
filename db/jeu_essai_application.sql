DELETE ENCHERES;
DELETE RETRAITS;
DELETE ARTICLES_VENDUS;
DELETE CATEGORIES;
DELETE UTILISATEURS;

DBCC CHECKIDENT (ARTICLES_VENDUS, RESEED, 0);
DBCC CHECKIDENT (CATEGORIES, RESEED, 0);
DBCC CHECKIDENT (UTILISATEURS, RESEED, 0);

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('jeandupont', 'DUPONT', 'Jean','j.dupont@gmail.com', '0612547890', '26 rue des Magnolias', '75000', 'Paris', '63a9f0ea7bb98050796b649e85481845', '1000', 1 );
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('micheldupaul', 'DUPAUL', 'Michel','m.dupaul@gmail.com', '0682544790', '2 bd de Sévigné', '35000', 'Rennes', '63a9f0ea7bb98050796b649e85481845', '800', 0 );
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('pierredurand', 'DURAND', 'Pierre','p.duand@gmail.com', '0682514890', '1 place du parlement', '35000', 'Rennes', '63a9f0ea7bb98050796b649e85481845', '700', 0 );

INSERT INTO CATEGORIES (libelle) VALUES('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES('Ameublement');
INSERT INTO CATEGORIES (libelle) VALUES('Vêtement');
INSERT INTO CATEGORIES (libelle) VALUES('Sport&Loisirs');

INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie, picture) VALUES('Ordinateur', 'PC portable ACER', '2020-04-25', '2020-05-15', '300', '450', 1, 1, 'apple.jpg');
INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,picture) VALUES('Table basse', 'Quasi neuve', '2020-04-13', '2020-05-16', '10', '40', 1, 2, 'table.png');
INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,picture) VALUES('Skis', 'Occasion', '2020-05-12', '2020-05-19', '300', '500', 2, 4, 'ski.png');
INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,picture) VALUES('Etagère', 'Beaucoup de rangement', '2020-02-01', '2020-03-10', '50', '70', 3, 2, 'etagere.jpg');

INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(3,'26 rue des Magnolias', '35000', 'Rennes');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(2,'2 bd de Sévigné', '35000', 'Rennes');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(1,'2 bd de Sévigné', '35000', 'Rennes');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(4,'1 place du parlement', '35000', 'Rennes');

SET DateFormat YMD

INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(1, 3, '2020-05-19', 500);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(2, 1, '2020-05-15', 450);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(2, 2, '2020-05-16', 40);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(3, 4, '2020-03-10', 70);

