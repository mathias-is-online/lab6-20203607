package Daos;
import Beans.Cancion;
import Beans.Tour;

import java.sql.*;
import java.util.ArrayList;

public class CancionesDao {

    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";



    public ArrayList<Cancion> listar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from cancion")) {

            while (rs.next()) {

                Cancion cancion = new Cancion();
                cancion.setIdcancion(rs.getInt(1));
                cancion.setNombre_cancion(rs.getString(2));
                cancion.setBanda(rs.getString(3));
                cancion.setFavorito(rs.getInt(4));
                cancion.setLista(rs.getString(5));
                listaCanciones.add(cancion);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaCanciones;
    }

    public ArrayList<Cancion> listar(String idbanda) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Cancion> listaCancionesBanda = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedStatement = conn.prepareStatement("select * from cancion where banda = ?")) {

            //uso preparedstatement xq necesito buscar algo en específico
            preparedStatement.setString(1, idbanda);
            //el prepared statement indexa según el orden del ?, al ser job_id el primer ? toncs coincidirá
            //el id ingresada con el id del querie

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Cancion cancion = new Cancion();
                    cancion.setIdcancion(rs.getInt(1));
                    cancion.setNombre_cancion(rs.getString(2));
                    cancion.setBanda(rs.getString(3));

                    listaCancionesBanda.add(cancion);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCancionesBanda;
    }

    public void quitarfavorito(String id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "UPDATE cancion set favorito = 0 where idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            //como necesito coincidir parametros especificos usare prepared statement
            pstmt.setString(1, id);
            //dsps de un delete, insert o update se debe hacer execute update para actualizar la base de datos
            pstmt.executeUpdate();
            System.out.println("se quito pe");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarfavorito(String id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        String sql = "UPDATE cancion SET favorito = 1 WHERE idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> listarListas() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> listaListas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select lista from cancion group by lista")) {

            while (rs.next()) {

                if (rs.getString(1)!=null){
                    String lista = rs.getString(1);
                    listaListas.add(lista);
                }
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaListas;
    }





    public ArrayList<Cancion> obtenerlistaFavoritos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Cancion> listaCanciones = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from cancion where favorito = 1")) {

            while (rs.next()) {

                Cancion cancion = new Cancion();
                cancion.setIdcancion(rs.getInt(1));
                cancion.setNombre_cancion(rs.getString(2));
                cancion.setBanda(rs.getString(3));
                cancion.setFavorito(rs.getInt(4));

                listaCanciones.add(cancion);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaCanciones;
    }


    public Cancion obtenerCancion(String id){
        Cancion cancion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion where idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //uso preparedstatement xq necesito buscar algo en específico
            preparedStatement.setString(1, id);
            //el prepared statement indexa según el orden del ?, al ser job_id el primer ? toncs coincidirá
            //el id ingresada con el id del querie

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    cancion = new Cancion();
                    cancion.setIdcancion(rs.getInt(1));
                    cancion.setNombre_cancion(rs.getString(2));
                    cancion.setBanda(rs.getString(3));
                    cancion.setFavorito(rs.getInt(4));
                    cancion.setLista(rs.getString(5));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cancion;
    }











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


    public void agregarlista(String id, String listaescogida){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "UPDATE cancion SET lista = ? WHERE idcancion = ?";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            //como necesito coincidir parametros especificos usare prepared statement
            pstmt.setString(1, listaescogida);
            pstmt.setString(2, id);
            //dsps de un delete, insert o update se debe hacer execute update para actualizar la base de datos
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }









    }





}
