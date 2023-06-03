<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 2/06/2023
  Time: 01:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Beans.Cancion" %>
<jsp:useBean type="java.util.ArrayList<Beans.Cancion>" scope="request" id="listaCanciones"/>
<jsp:useBean type="java.util.ArrayList<java.lang.String>" scope="request" id="listaListas"/>

<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Canciones"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="canciones"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Canciones</h1>
            <div class="col-20">
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/listaCanciones?a=crearlista">Crear
                    lista</a>
            </div>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>ID</th>
            <th>CANCION</th>
            <th>BANDA</th>
            <th>Rojo = favorito  /   Verde = no favorito</th>
            <th>Lista actual</th>
            <%
                for (String lista : listaListas) {
            %>
            <th></th>
            <%
                }
            %>
            </thead>


            <%
                for (Cancion cancion : listaCanciones) {
            %>
            <tr>
                <td><%=cancion.getIdcancion()%>
                </td>
                <td><%=cancion.getNombre_cancion()%>
                </td>
                <td><%=cancion.getBanda()%>
                </td>
                <td>
                <% if (cancion.getFavorito() == 1){ %>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/listaCanciones?a=favoritoquitar&id=<%=cancion.getIdcancion()%>">Quitar de favoritos?</a>
                <%}else{ %>
                <a class="btn btn-success" href="<%=request.getContextPath()%>/listaCanciones?a=favoritoagregar&id=<%=cancion.getIdcancion()%>">Agregar a favoritos</a>
                <%}%>
                </td>

                <td>
                <% if (cancion.getLista() == null){ %>
                    Sin Lista
                <%}else{ %>
                    <%=cancion.getLista()%>
                <%}%>
                </td>

                <%
                    for (String lista : listaListas) {
                %>

                <td>
                    <a class="btn btn-info" href="<%=request.getContextPath()%>/listaCanciones?a=agregarlista&id=<%=cancion.getIdcancion()%>&lista=<%=lista%>"><%=lista%></a>
                </td>

                <%
                    }
                %>

            </tr>
            <%
                }
            %>
        </table>
    </div>

</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>

