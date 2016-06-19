DROP TABLE IF EXISTS gruppen_netzwerk;
DROP TABLE IF EXISTS soziales_netzwerk;

DROP TABLE IF EXISTS teilnehmerliste;
DROP TABLE IF EXISTS treffen;

DROP TABLE IF EXISTS mitgliedschaft;
DROP TABLE IF EXISTS organisator;
DROP TABLE IF EXISTS gruppe;

DROP TABLE IF EXISTS erfahrung;
DROP TABLE IF EXISTS manager;

DROP TABLE IF EXISTS interesse;
DROP TABLE IF EXISTS mitglied;
DROP TABLE IF EXISTS authentifizierung;

CREATE TABLE authentifizierung(
	typ VARCHAR(20) PRIMARY KEY
);

CREATE TABLE mitglied(
	mitglieds_nr INT  AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL UNIQUE,
	passwort VARCHAR(100) NULL,
	email VARCHAR(255) NOT NULL,
	authentifizierung VARCHAR(20),
	CONSTRAINT mitglied_authentifizierung_1
		FOREIGN KEY(authentifizierung)
			REFERENCES authentifizierung(typ)
				ON DELETE SET NULL
				ON UPDATE CASCADE
);

CREATE TABLE interesse(
	mitglied INT,
	interesse VARCHAR(100),
	PRIMARY KEY(mitglied, interesse),
	CONSTRAINT interesse_mitglied_1
		FOREIGN KEY(mitglied)
			REFERENCES mitglied(mitglieds_nr)
			ON DELETE CASCADE
			ON UPDATE CASCADE
);

CREATE TABLE manager(
	mitglied INT PRIMARY KEY,
	adresse VARCHAR(100) NOT NULL,
	CONSTRAINT manager_mitglied_1
		FOREIGN KEY(mitglied)
			REFERENCES mitglied(mitglieds_nr)
			ON DELETE CASCADE
			ON UPDATE CASCADE
);

CREATE TABLE erfahrung(
	manager INT,
	erfahrung VARCHAR(100),
	PRIMARY KEY(manager, erfahrung),
	CONSTRAINT erfahrung_manager_1
		FOREIGN KEY(manager)
			REFERENCES manager(mitglied)
			ON DELETE CASCADE
			ON UPDATE CASCADE
);

CREATE TABLE gruppe(
	bezeichnung VARCHAR(10) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	aktiv BOOLEAN NOT NULL DEFAULT 0,
	beschreibung VARCHAR(250),
	gruendungsdatum DATE
);

CREATE TABLE organisator(
	manager INT,
	gruppe VARCHAR(10),
	von DATE NOT NULL,
	bis DATE,
	PRIMARY KEY(manager, gruppe),
	CONSTRAINT organisator_manager_1
		FOREIGN KEY(manager)
			REFERENCES manager(mitglied)
				ON DELETE RESTRICT
				ON UPDATE CASCADE,
	CONSTRAINT organisator_gruppe_1
		FOREIGN KEY(gruppe)
			REFERENCES gruppe(bezeichnung)
				ON DELETE CASCADE
				ON UPDATE CASCADE
);

CREATE TABLE mitgliedschaft(
	mitglied INT,
	gruppe VARCHAR(10),
	PRIMARY KEY(mitglied,gruppe),
	CONSTRAINT mitgliedschaft_mitglied_1
		FOREIGN KEY(mitglied)
			REFERENCES mitglied(mitglieds_nr)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	CONSTRAINT mitgliedschaft_gruppe_1
		FOREIGN KEY(gruppe)
			REFERENCES gruppe(bezeichnung)
				ON DELETE CASCADE
				ON UPDATE CASCADE
);

CREATE TABLE treffen(
	treffen_nr INT UNSIGNED,
	thema VARCHAR(50) NOT NULL,
	ort VARCHAR(50),
	datum TIMESTAMP,
	max_teilnehmer INT UNSIGNED,
	teilnehmer_anzahl INT UNSIGNED,
	gruppe VARCHAR(10),
	PRIMARY KEY(treffen_nr, gruppe),
	CONSTRAINT treffen_gruppe_1
		FOREIGN KEY(gruppe)
			REFERENCES gruppe(bezeichnung)
				ON DELETE CASCADE
				ON UPDATE CASCADE
);

CREATE TABLE teilnehmerliste(
	mitglied INT,
	gruppe VARCHAR(10),
	treffen INT UNSIGNED,
	PRIMARY KEY(mitglied, gruppe, treffen),
	CONSTRAINT teilnehmerliste_mitglied_1
		FOREIGN KEY(mitglied)
			REFERENCES mitglied(mitglieds_nr)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	CONSTRAINT teilnehmerliste_treffen_1
		FOREIGN KEY(gruppe,treffen)
			REFERENCES treffen(gruppe, treffen_nr)
				ON DELETE CASCADE
				ON UPDATE CASCADE
);

CREATE TABLE soziales_netzwerk(
	bezeichnung VARCHAR(15) PRIMARY KEY,
	base_url VARCHAR(50) NOT NULL,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE gruppen_netzwerk(
	gruppe VARCHAR(10),
	netzwerk VARCHAR(15),
	gruppen_seite VARCHAR(75) NOT NULL,
	PRIMARY KEY(gruppe,netzwerk),
	CONSTRAINT gruppen_netzwerk_gruppe_1
		FOREIGN KEY(gruppe)
			REFERENCES gruppe(bezeichnung)
				ON DELETE CASCADE
				ON UPDATE CASCADE,
	CONSTRAINT gruppen_netzwerk_soziales_netzwerk_1
		FOREIGN KEY(netzwerk)
			REFERENCES soziales_netzwerk(bezeichnung)
				ON DELETE RESTRICT
				ON UPDATE CASCADE
);