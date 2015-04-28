-- Table: users
CREATE TABLE [users] ( [u_id] INTEGER PRIMARY KEY NOT NULL , [u_name] TEXT NOT NULL DEFAULT ( NULL ) , [u_password] TEXT NOT NULL DEFAULT ( NULL ) );

INSERT INTO [users] ([u_id], [u_name], [u_password]) VALUES (1, 'Utilisateur 1', 'password');
INSERT INTO [users] ([u_id], [u_name], [u_password]) VALUES (2, 'Kim Mens', 'lsinf1225');


-- Table: collected_items
CREATE TABLE [collected_items] ( [ci_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , [ci_name] TEXT NOT NULL , [ci_description] TEXT , [ci_picture] TEXT );

INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (1, 'Service Boston', 'Service de 60 pièces pour 12 personnes', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (2, 'Service Ambiante', 'Service pour 12 personnes', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (3, 'Service en porcelaine', 'Service comprenant 12 assiettes plates, 12 assiettes creuses et 12 assiettes à dessert.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (4, 'Présentoire cure-dents', 'Porte cure-dents Magic Bunny.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (5, 'Cuillère à sauce', 'Cuillère pour servir la sauce.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (6, 'Cuillère à glace', 'Cuillère pour faire des boules de glace.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (7, 'Couteau à pain', 'Couteau pour couper le pain.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (8, 'Couteau à viande', 'Couteau pour couper la viande.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (9, 'Porte-bouteilles', 'Porte-bouteilles métallique.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (10, 'Tire-bouchon', 'Tire-bouchon métallique.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (11, 'Chateau Cheval Blanc', 'Une bouteille de Cheval Blanc 1981.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (12, 'Château Margaux', 'Une bouteille de Château Margaux 2003.', null);
INSERT INTO [collected_items] ([ci_id], [ci_name], [ci_description], [ci_picture]) VALUES (13, 'Château Brane-Cantenac', 'Une bouteille de Château Brane-Cantenac 2009.', null);

-- Table: owns
CREATE TABLE [owns] ( [u_id] INTEGER NOT NULL , [ci_id] INTEGER NOT NULL , [ci_rating] float , FOREIGN KEY ( [u_id] ) REFERENCES [users] ( [u_id] ) , FOREIGN KEY ( [ci_id] ) REFERENCES [collecteditems] ( [ci_id] ) );

INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 1, 5.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 2, 2.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 3, 5.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 4, 1.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 5, null);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 6, 4.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 7, 2.5);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 8, 2.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 9, 3.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (1, 10, null);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (2, 9, 4.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (2, 10, 4.5);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (2, 11, 5.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (2, 12, 5.0);
INSERT INTO [owns] ([u_id], [ci_id], [ci_rating]) VALUES (2, 13, 3.0);