### Setup

- [ ] Create a MySQL `user=root`, `password=password`
- [ ] Create a schema `day18`
- [ ] Create a table `staff`
    ```
    CREATE TABLE `staff` (
      `IDKaryawan` int NOT NULL,
      `Nama` varchar(45) DEFAULT NULL,
      `TunjanganPulsa` decimal(15,3) DEFAULT NULL,
      `GajiPokok` decimal(15,3) DEFAULT NULL,
      `AbsensiHari` int DEFAULT NULL,
      `Email` json DEFAULT NULL,
      PRIMARY KEY (`IDKaryawan`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
    ```