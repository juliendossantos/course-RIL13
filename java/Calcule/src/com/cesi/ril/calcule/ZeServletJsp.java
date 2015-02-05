package com.cesi.ril.calcule;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ZeServletJsp extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		
		// récupération des paramètres
		String commande = req.getParameter("commande");
		int nombre;
		try{
			nombre = Integer.parseInt(req.getParameter("nb"));
		} catch (Exception e){
			nombre=1;
		}
		
		// injecter les paramètres du fichier JSP
		req.setAttribute("commande", commande);
		req.setAttribute("nombre", nombre);
		
		// pontage sur le fichier JSP
		getServletContext().getRequestDispatcher("/WEB-INF/views/affiche.jsp").forward(req, rep);
	}
	
}
