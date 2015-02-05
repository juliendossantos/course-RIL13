package com.cesi.ril.calcule;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ZeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		// récupération des paramètres
		String commande = req.getParameter("commande");
		int nombre = Integer.parseInt(req.getParameter("nb"));
		
		// création du flux d'écriture
		PrintWriter out = rep.getWriter();
		out.println("<h1>Coucou !</h1>");
		out.println("Tu veux en " + commande + " " + nombre);
	}
	
}
