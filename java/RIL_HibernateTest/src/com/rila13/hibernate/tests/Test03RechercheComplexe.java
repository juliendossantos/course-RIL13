package com.rila13.hibernate.tests;

import java.util.List;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.rila13.hibernate.Acteurs;
import com.rila13.hibernate.Films;
import com.rila13.hibernate.HibernateUtil;
import com.rila13.hibernate.Joue;
import com.rila13.hibernate.JouePK;

public class Test03RechercheComplexe {

	public static void main(String[] args) throws HibernateException {
		Session session = HibernateUtil.currentSession();
		List<Films> listFilms = session.find("from Films");
		for(Films f:listFilms) {
			System.out.println(f.getTitreFilm() + " (" + f.getAnneeFilm() + ")");
			Set<Joue> setJ=f.getJoueSet();
			for(Joue j:setJ)
			{
				JouePK jpk = j.getId();
				System.out.println("\t" + j.getCasting() + " (" + jpk.getCodeA().getPrenomActeur() + " " + jpk.getCodeA().getNomActeur() + ")");
			}

		}

	}

}
