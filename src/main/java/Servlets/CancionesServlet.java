package Servlets;

import Beans.Tour;
import Daos.CancionesDao;
import Daos.TourDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CancionesServlet",urlPatterns = {"/listaCanciones"})
public class CancionesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("p") == null ? "crearlista" : request.getParameter("p");
        CancionesDao cancionesDao = new CancionesDao();
        //NO IMPLEMENTADO


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CancionesDao cancionesDao = new CancionesDao();


        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");

        switch (action) {
            case "listar":
                request.setAttribute("listaCanciones",cancionesDao.listar());
                request.setAttribute("listaListas",cancionesDao.listarListas()); //esto me bota las listas que hay
                RequestDispatcher view =request.getRequestDispatcher("listaCanciones.jsp");
                view.forward(request,response);
                break;

            case "banda":
                String idbanda = request.getParameter("idbanda");
                request.setAttribute("listaCancionesBanda",cancionesDao.listar(idbanda));
                request.getRequestDispatcher("listaCancionesBanda.jsp").forward(request, response);
                break;

            case "favoritoquitar":

                String id2 = request.getParameter("id");
                cancionesDao.quitarfavorito(id2);
                response.sendRedirect(request.getContextPath() + "/listaCanciones");
                break;

            case "favoritoagregar":

                String id3 = request.getParameter("id");
                cancionesDao.agregarfavorito(id3);
                response.sendRedirect(request.getContextPath() + "/listaCanciones");
                break;
            case "agregarlista":

                String id4 = request.getParameter("id");
                String lista = request.getParameter("lista");
                cancionesDao.agregarlista(id4,lista);

                response.sendRedirect(request.getContextPath() + "/listaCanciones");
                break;
            case "crearlista":

                //solo te mandará a la página de crear
                request.getRequestDispatcher("nuevalista.jsp").forward(request, response);
                break;

        }
    }
}
