<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: sans-serif;
            flex-direction: column;
        }

        h1 {
            margin-bottom: 20px;
            font-weight: 300;
        }
    </style>
</head>
<body>
    <h1>Welcome ${user.getString("fullname")}</h1>
        <a href="/tugas/menu">
        <button>Go to menu</button>
    </a>
</body>
</html>