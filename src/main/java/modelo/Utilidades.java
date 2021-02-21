package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Utilidades {

    public static ArrayList<Alumno> getAlumnos(String fichero) throws FileNotFoundException, IOException {
        ArrayList<Alumno> alumnos = new ArrayList();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), "utf-8"));
            String linea;
            try {
                while ((linea = buffer.readLine()) != null) {
                    Alumno alumno = new Alumno();
                    String arr_datos[] = linea.split(";");

                    alumno.setId(Integer.parseInt(arr_datos[0]));
                    alumno.setNombre(arr_datos[1]);
                    alumno.setApellidos(arr_datos[2]);
                    alumno.setCorreo(arr_datos[3]);

                    alumnos.add(alumno);
                }
                buffer.close();
            } catch (IOException e) {

            }
        } catch (UnsupportedEncodingException e) {

        }
        return alumnos;
    }

    public static List<Alumnos> getAlumnosBD(String grupo) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_MensajeAlumnos_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM productos where grupo = '" + grupo + "'";
        Query q = manager.createNativeQuery(sql, Alumnos.class);
        List<Alumnos> alumnos = q.getResultList();

        return alumnos;
    }
}
