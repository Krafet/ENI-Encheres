DELETE ENCHERES;
DELETE RETRAITS;
DELETE ARTICLES_VENDUS;
DELETE CATEGORIES;
DELETE UTILISATEURS;

DBCC CHECKIDENT (ARTICLES_VENDUS, RESEED, 0);
DBCC CHECKIDENT (CATEGORIES, RESEED, 0);
DBCC CHECKIDENT (UTILISATEURS, RESEED, 0);

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('Utilisateur1', 'nom1', 'prenom1','user1@gmail.com', '0612547890', '45 rue test', '35000', 'Rennes', '63a9f0ea7bb98050796b649e85481845', '500', 1 );
INSERT INTO UTILISATEURS  (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('Utilisateur2', 'nom2', 'prenom2','user2@gmail.com', '0612544790', '60 rue test', '35000', 'Rennes', '63a9f0ea7bb98050796b649e85481845', '400', 0 );
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES('pierredurand', 'DURAND', 'Pierre','p.duand@gmail.com', '0682514890', '1 place du parlement', '35000', 'Rennes', '63a9f0ea7bb98050796b649e85481845', '300', 0 );

INSERT INTO CATEGORIES (libelle) VALUES('Categorie1');
INSERT INTO CATEGORIES (libelle) VALUES('Categorie2');
INSERT INTO CATEGORIES (libelle) VALUES('Categorie3');

INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES('Article1', 'Description1', '2020-04-12', '2020-05-15', '15', '50', 1, 3);
INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES('Article2', 'Description2', '2020-04-13', '2020-05-16', '5', '40', 1, 2);
INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES('Article3', 'Description3', '2020-05-12', '2020-05-19', '18', '60', 2, 1);

INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(3,'Rue1', '75000', 'Paris');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(2,'Rue2', '35000', 'Rennes');

INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(1, 2, '2020-04-12', 500);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(3, 2, '2018-04-10', 400);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(2, 1, '2018-04-10', 300);

