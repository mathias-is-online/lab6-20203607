<%--
  Created by IntelliJ IDEA.
  User: Labtel
  Date: 01/06/2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Beans.Cancion" %>
<jsp:useBean type="java.util.ArrayList<Beans.Cancion>" scope="request" id="listaRecomendados"/>


<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Recomendados"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="recomendados"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Recomendados</h1>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>ID</th>
            <th>CANCION</th>
            <th>BANDA</th>
            <th>Ver</th>
            </thead>
            <%
                for (Cancion cancion : listaRecomendados) {
            %>
            <tr>
                <td><%=cancion.getIdcancion()%>
                </td>
                <td><%=cancion.getNombre_cancion()%>
                </td>
                <td><%=cancion.getBanda()%>
                </td>
                <td><a class="btn btn-success" href="<%=request.getContextPath()%>/listaCanciones?a=banda&idbanda=<%=cancion.getBanda()%>">Más de la banda</a>
                </td>

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
