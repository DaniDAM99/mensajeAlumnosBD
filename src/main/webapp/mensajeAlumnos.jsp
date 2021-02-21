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
        <h1>Mesanje a Alumnos</h1>
        <%
            String grupo = (String) request.getAttribute("grupo");
            ArrayList<Alumno> alumnos = (ArrayList) request.getAttribute("alumnos");
        %>
        <div>
            <form action="servletAlumnos" method="POST">
                Grupo seleccionado: <%=grupo%>
                <table class="table">
                    <% for (Alumno a : alumnos) {%>
                    <tr>
                        <td><%=a.getNombre()%></td>
                        <td><%=a.getApellidos()%></td>
                        <td><%=a.getCorreo()%></td>
                    </tr>
                    <%}%>
                </table>
                Mensaje: <textarea name="mensaje"></textarea>
                <input type="submit" value="Enviar">
            </form>
        </div>
    </body>
</html>
