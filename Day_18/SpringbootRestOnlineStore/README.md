### Setup

- [ ] Create a MySQL `user=root`, `password=password`
- [ ] Create a schema `day18`
- [ ] Create a table `product`
    ```
    CREATE TABLE `product` (
      `id` int NOT NULL AUTO_INCREMENT,
      `name` varchar(45) NOT NULL,
      `categoryId` int NOT NULL,
      `price` decimal(13,3) NOT NULL,
      PRIMARY KEY (`id`,`categoryId`)
    ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
    ```