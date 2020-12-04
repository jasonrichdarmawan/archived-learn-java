Set up:
1.	docker build -t be-bankaccount .

        ```
        Catatan: tidak perlu JDK, hanya perlu Docker Engine. Instalasi akan memakan waktu sekitar 5 menit (jika ada JDK, silahkan edit Dockerfile)
        ```
2.	docker-compose up

Endpoint:
1.	hit GET localhost:8080/account/555001
2.	hit POST localhost:8080/account/555001/transfer
    
        a.	Request Body
        ```
        {
          “to_account_number”: “555002”,
          “amount”: 15000
        }
        ```

Depedency yang digunakan:
1.	Client Java Application dengan Spring Framework dan Spring Boot
        ```
        a.	Spring Web
        b.	MyBatis Framework
        c.	MySQL Driver
        d.	Hibernate Validator (hanya untuk validasi request, contoh “to_account_number” tidak boleh kosong, dan seterusnya)
        ```
2. MySQL