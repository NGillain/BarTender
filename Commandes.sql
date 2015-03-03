--Commandes pour la table BarTender:

CREATE Table Utilisateur ( Login char(32) not null, MotDePasse char(32) not null, Statut char(16) not null DEFAULT 'Client',Sexe char(1),Religion char(32),Age INT(3),Nom Char(64),Primary key (Login));
INSERT INTO Utilisateur (Login,MotDePasse,Statut,Sexe,Religion,Age,Nom) VALUES ('AMaalouf','AllahWakbar','Serveur','M','Musulman',42,'Ahmed Maalouf');
INSERT INTO Utilisateur (Login,MotDePasse,Statut,Sexe,Religion,Age,Nom) VALUES ('Abra','Kadabra','Client','M','Juif',24,'Abraham Iosov');
INSERT INTO Utilisateur (Login,MotDePasse,Statut,Sexe,Religion,Age,Nom) VALUES ('Mik','Jackson','Patron','M','Bouddhiste',24,'Kim Bling');

CREATE Table Allergies(Login char(32) not null REFERENCES Utilisateur(login),Allergene char(32) not null);
INSERT INTO Allergies (Login,Allergene) VALUES ('Abra','Soja');

CREATE Table Addition(AddNum int(9) not null,Date DATETIME not null, ServeurLogin char(32) not null REFERENCES Utilisateur(login), ClientLogin char(32) not null REFERENCES Utilisateur(Login),NumTable int(2) not null, primary key (AddNum));
INSERT INTO Addition (AddNum,Date,ServeurLogin,ClientLogin,NumTable) VALUES (1,'2015-02-26','Mik','Abra',4);
INSERT INTO Addition (AddNum,Date,ServeurLogin,ClientLogin,NumTable) VALUES (2,'2015-02-28','AMaalouf','Abra',6);

CREATE Table Consommation(AddNum int(9) not null, Boisson char(32) not null REFERENCES Boisson(nom), Qté int(2) not null, CONSTRAINT unq UNIQUE (Boisson, AddNum));
INSERT INTO Consommation (AddNum,Boisson,Qté) VALUES (1,'Coca-Cola',2);
INSERT INTO Consommation (AddNum,Boisson,Qté) VALUES (1,'Bière',2);
INSERT INTO Consommation (AddNum,Boisson,Qté) VALUES (2,'Bière',2);
INSERT INTO Consommation (AddNum,Boisson,Qté) VALUES (2,'Martini Blanc',1);

CREATE TABLE Boisson (nom char(32) not null, prix_achat int not null, prix_vente int not null, description char(256), photo char(32), type int not null, stock int not null, seuil int not null, max int not null, unique(photo), Primary Key(nom));


INSERT INTO Boisson VALUES('Sprite', 1, 2, "Le sprite est une limonade crée par Coca-Cola", 'sprite.jpg', 1, 25, 50, 100);
INSERT INTO Boisson VALUES('Orangina', 1.5, 2.5, 'Orangina est une boisson gazeuse à base d oranges', 'orangina.jpg', 1, 25, 50, 100);
INSERT INTO Boisson VALUES('Petit Café', 0.80, 1.5, "", 'cafe.jpg', 2, 18, 30, 50);
INSERT INTO Boisson VALUES('Grand Café', 1, 2.3, "", 'cafe.jpg', 2, 18, 30, 50);
INSERT INTO Boisson VALUES('Cappucino', 1.2, 2.5, "", 'cafe.jpg', 2, 18, 30, 50);
INSERT INTO Boisson VALUES('Jupiler 25cl', 0.3, 2.3, "Bière belge blonde de fermentation basse type pils", 'jupiler.jpg', 3, 60, 120, 240);
INSERT INTO Boisson VALUES('Jupiler 50cl', 0.5, 4.2, "Bière belge blonde de fermentation basse type pils", 'jupiler.jpg', 3, 60, 120, 240);
INSERT INTO Boisson VALUES('Leffe Blonde 33cl', 0.8, 3.6, 'Bière blonde d abbaye', 'leffeblonde.jpg', 3, 35, 35, 120);
INSERT INTO Boisson VALUES('Ricard', 1.3, 4.5, 'Le Ricard est une marque de pastis, une boisson alcoolisée à l anis', 'pastis.jpg', 4, 18, 25, 50);
INSERT INTO Boisson VALUES('Vodka', 1.8, 4.7, "Boisson alcolisée à 40°", 'vodka.jpg', 4, 48, 25, 50);
INSERT INTO Boisson VALUES('Limoncello', 2.4, 5.3, "Le limoncello est une liqueur de citron", 'limoncello.jpg', 4, 37, 25, 50);
INSERT INTO Boisson VALUES('Bouteille de vin blanc', 13, 20, "", "", 5, 17, 10, 30);
INSERT INTO Boisson VALUES('Bouteille de vin rouge', 13, 20, "", "", 5, 17, 10, 30);
INSERT INTO Boisson VALUES('Bouteille de vin rosé', 13, 20, "", "", 5, 17, 10, 30);

--TYPES :    1) Soft
  --          2) Boisson chaudes
  --          3) Bières
  --          4) Spiritueux
  --          5) Vins

CREATE TABLE Image(type int not null REFERENCES Boissons(type), logo char(32) not null);
INSERT INTO Images VALUES(1, 'soft.jpg');
INSERT INTO Images VALUES(2, 'boissonChaude.jpg');
INSERT INTO Images VALUES(3, 'biere.jpg');
INSERT INTO Images VALUES(4, 'spiritueux.jpg');
INSERT INTO Images VALUES(5, 'vin.jpg');
            
CREATE TABLE Allergies_boisson(nom char(32) not null REFERENCES Boisson(nom), allergène char(32) not null);
INSERT INTO Allergies_boisson VALUES('Cappucino', "lactose");
INSERT INTO Allergies_boisson VALUES('Ricard', "lactose");
