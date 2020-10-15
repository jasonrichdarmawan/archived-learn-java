<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
    <div class="container my-5">
        <h1 class="mb-4">Users</h1>
        <a class="btn btn-primary" href="/tugas/insert">Tambah data</a>
        <div class="users mt-5">
            <div class="row">
                <%
                    ResultSet result = (ResultSet) request.getAttribute("users");
                %>

                <% while(result.next()) { %>
                      <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><%= result.getString("fullname") %></h5>
                                    <h6 class="card-subtitle mb-3 text-muted">(<%= result.getString("username") %>)</h6>
                                    <p class="card-text">1. Physics : <span class="ml-4"><%= result.getString("physics") %></span></p>
                                    <p class="card-text">2. Calculus : <span class="ml-4"><%= result.getString("calculus") %></span></p>
                                    <p class="card-text">3. Biologi : <span class="ml-4"><%= result.getString("biologi") %></span></p>
                                    <p class="card-text">4. Status : <span class="ml-4"><%= result.getString("status") %></span></p>
                                    <p class="card-text">5. Address : <span class="ml-4"><%= result.getString("address") %></span></p>
                                    <a href="/tugas/update?id=<%= result.getString("id") %>" class="card-link">Update</a>
                                    <form class="d-inline" method="post" action="delete">
                                        <input type="hidden" name="id" value="<%= result.getString("id") %>">
                                        <button type="submit" class="card-link text-danger" style="background-color: transparent; border: none; outline:none;" onclick="return confirm('Anda yakin ingin menghapus data ini ?')">Delete</button>
                                    </form>
                                </div>
                            </div>
                      </div>
                <% } %>
            </div>
        </div>
    </div>    
</body>
</html>