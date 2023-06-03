<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 3/06/2023
  Time: 03:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Nuevo trabajo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
        crossorigin="anonymous">
</head>
<body>
<div class="container">
  <h1 class='mb-3'>Nuevo empleado</h1>
  <form method="POST" action="<%=request.getContextPath()%>/listaCanciones?p=crearlista">
    <div class="mb-3">
      <label for="lista">Nombre Lista</label>
      <input type="text" class="form-control" name="lista" id="lista">
    </div>
    <a class="btn btn-danger" href="<%=request.getContextPath()%>/listaCanciones">Cancelar</a>
    <button type="submit" class="btn btn-primary">Enviar</button>

  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>
