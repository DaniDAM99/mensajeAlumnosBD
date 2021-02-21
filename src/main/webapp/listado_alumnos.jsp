<%-- 
    Document   : listado_alumnos
    Created on : 05-feb-2021, 15:24:40
    Author     : Cristian
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <%
            ArrayList<Alumno> alumnos = (ArrayList) request.getAttribute("alumnos");
            ArrayList<String> grupos = (ArrayList) request.getAttribute("grupos");
            String grupoSel = (String) request.getAttribute("grupo_activo");
        %>

        <div class="container">
            <h1>Listado alumnos</h1>
            <div class="m-3">
                <form action="servletAlumnos" method="GET">
                    <select name="grupo">
                        <% for (String g : grupos) {
                                String seleccion = "";
                                if (g.equals(grupoSel)) {
                                    seleccion = "selected";
                                }
                        %>
                        <option <%=seleccion%> value="<%=g%>"><%=g%></option>
                        <%}%>
                    </select>
                    <input type="submit" value="Seleccionar">
                </form>
            </div>
                    
            <div class="m-4">
                <form action="servletAlumnos" method="POST">
                    Grupo seleccionado: <input name="grupo" type="text" readonly value="<%=grupoSel%>">
                    <table class="table">
                        <% for (Alumno a : alumnos) {%>
                        <tr>
                            <td><%=a.getNombre()%></td>
                            <td><%=a.getApellidos()%></td>
                            <td><%=a.getCorreo()%></td>
                            <td><input type="checkbox" name="<%=a.getId()%>" value="<%=a.getId()%>"></td>
                        </tr>
                        <%}%>
                    </table>
                    <input type="submit" value="Enviar">
                </form>
            </div>
        </div>
    </body>
</html>
