CREATE SEQUENCE Mst_Kary;

CREATE TABLE Mst_Kary (
  Id_Kary INTEGER IDENTITY NOT NULL,
  Nama_Kary VARCHAR(255) NOT NULL,
  Alamat_Kary VARCHAR(255) NULL,
  RT VARCHAR(255) NULL,
  RW VARCHAR(255) NULL,
  Kecamatan VARCHAR(255) NULL,
  Keluharan VARCHAR(255) NULL,
  No_Telp VARCHAR(255) NULL,
  Input_Date DATE NOT NULL,
  Input_By VARCHAR(255) NOT NULL,
  Approve_Date DATE NULL,
  Approve_By VARCHAR(255) NULL,
  PRIMARY KEY (Id_Kary)
);