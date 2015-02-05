package com.cesi.ril.API;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesi.ril.engine.TypeReponse;
import com.cesi.ril.engine.Devine;


public class DevineAPI extends HttpServlet {
	
	private static Devine devine;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		
		if(devine == null)
			devine = new Devine();		
		
		PrintWriter out = rep.getWriter();
	
		if(req.getParameter("nouveauJeu") != null) {
			devine.nouveauJeu();
			out.println("Trouver un nombre entre 1 et 100");
		} else {
			int nbProposer;
			try{
				nbProposer = Integer.parseInt(req.getParameter("nbProposer"));
			} catch (Exception e){
				nbProposer = 0;
			}
			TypeReponse proposition = devine.proposerNombre(nbProposer);
			
			if (proposition.equals(TypeReponse.TROP_PETIT))
				out.println("Trop petit !");
			else if (proposition.equals(TypeReponse.TROP_GRAND))
				out.println("Trop grand !");
			else
				out.println("Trouver en " + devine.getNbCoups() + " coups");
		}
	}
	
	
}
