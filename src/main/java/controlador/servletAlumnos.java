/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumnos;
import modelo.Utilidades;

/**
 *
 * @author Cristian
 */
public class servletAlumnos extends HttpServlet {

    private ArrayList<Alumnos> alumnos;
    private ArrayList<String> grupos;
    private String grupo;
    private String rutaFicheros;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        
        grupos = new ArrayList();
        grupos.add("2daw_a");
        grupos.add("2daw_b");
        
        //rutaFicheros = config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros");
        
        grupo = "2daw_a";
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String grupo = request.getParameter("grupo");
        if(grupo == null) {
            grupo = "2daw_a";
        }
        
        alumnos = (ArrayList) Utilidades.getAlumnosBD(grupo);
        
        request.setAttribute("grupo_activo", grupo);
        request.setAttribute("grupos", grupos);
        request.setAttribute("alumnos", alumnos);
        request.getRequestDispatcher("listado_alumnos.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        int ultimoId = alumnos.get(alumnos.size() - 1).getId();
        
        ArrayList<Alumnos> alumnosSelec = new ArrayList();
        
        String grupo = request.getParameter("grupo");
        for (int i = 0; i <= ultimoId; i++) {
            if(request.getParameter(String.valueOf(i)) != null) {
                for (Alumnos alum : alumnos) {
                    if (alum.getId() == i) {
                        Alumnos a = new Alumnos(alum.getId(), grupo, alum.getNombre(), alum.getApellidos(), alum.getCorreo());
                        /*Integer id, String grupo, String nombre, String apellidos, String correo*/
                        alumnosSelec.add(a);
                    }
                }
            }
        }
        request.setAttribute("grupo", grupo);
        request.setAttribute("alumnos", alumnosSelec);
        request.getRequestDispatcher("mensajeAlumnos.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
