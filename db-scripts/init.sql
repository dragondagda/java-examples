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

INSERT INTO authentifizierung VALUES("twitter");
INSERT INTO authentifizierung VALUES("google");

INSERT INTO mitglied VALUES(1, "user001", "12345", "user001@example.org", NULL);
INSERT INTO mitglied VALUES(2, "elli", NULL, "elli@example.org", "twitter");
INSERT INTO mitglied VALUES(3, "nick81", "67890", "nick@example.org", NULL);