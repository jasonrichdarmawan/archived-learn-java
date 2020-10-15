###

Disclaimer: This folder is not my work. The instructor request us to troubleshoot this maven project.

What I did:
1. Add library `mysql-connector-java-8.0.21`
2. Re-configure the properties used by the `Connection Class` in the `src` folder.
3. Create a schema `day14`
4. Create a table `users` with column `int id, varchar(20) username, varchar(20) fullname, int physics, int calculus, int biologi, int status, varchar(45) address`
5. send GET request to localhost:8080/tugas/Login.jsp
6. send POST request to localhost:8080/tugas/Login.jsp with body username=abcde and a random password that match the regex for example `AbcdEfgh1#`

What I am not sure:
1. How things works:
    1. After successfully login `login.jsp` using form action `LoginController.java` with a POST request, the UI redirects to `success.jsp`, the user can redirect to `menu.jsp`. There is no indication of `menu.jsp` to call the `doGet` method from the `ReadController` class. Everything works by creating an RequestDispatcher Object.
    2. Why I need to send the GET request to `/login.jsp` while I can send a GET request to `/menu`.
    3. Why the `LoginController` class do not have the `doGet` method `note: other Controller has the doGet method` and the `requestDispatcher` object. But I still can send the GET request.
2. What I am afraid:
    1. This creates the endpoints open to unauthenticated user.