package Daos;
import Beans.Cancion;
import Beans.Tour;

import java.sql.*;
import java.util.ArrayList;

public class CancionesDao {

    private static String user = "root";
    private static String pass = "123456";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";


    public ArrayList<Cancion> obtenerListaRecomendados(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Cancion> listaRecomendados = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select r.cancion_idcancion, c.nombre_cancion, c.banda " +
                     "from reproduccion r " +
                     "inner join cancion c on (r.cancion_idcancion = c.idcancion) " +
                     "group by cancion_idcancion having\n" +
                     "count(*) >2 order by count(*) desc")) {

            while (rs.next()) {

                Cancion cancion = new Cancion();
                cancion.setIdcancion(rs.getInt(1));
                cancion.setNombre_cancion(rs.getString(2));
                cancion.setBanda(rs.getString(3));

                listaRecomendados.add(cancion);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaRecomendados;
    }

}
