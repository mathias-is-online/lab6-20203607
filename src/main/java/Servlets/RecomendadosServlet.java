package Servlets;

import Beans.Tour;
import Daos.TourDao;
import Beans.*;
import Daos.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RecomendadosServlet",urlPatterns = {"/listaRecomendados"})
public class RecomendadosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         CancionesDao cancionesDao = new CancionesDao();
        ArrayList<Cancion> listaRecomendados = cancionesDao.obtenerListaRecomendados();
        System.out.println(listaRecomendados.size());

        request.setAttribute("listaRecomendados",listaRecomendados);

        RequestDispatcher view =request.getRequestDispatcher("listaRecomendados.jsp");
        view.forward(request,response);
    }
}

