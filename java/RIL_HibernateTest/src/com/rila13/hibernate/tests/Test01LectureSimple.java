package com.rila13.hibernate.tests;

import java.util.Iterator;
import java.util.List;

import com.rila13.hibernate.Films;
import com.rila13.hibernate.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

public class Test01LectureSimple {

	public static void main(String[] args) throws HibernateException {
		Session session = HibernateUtil.currentSession();
		List<Films> listFilms = session.find("from Films");
		for(Films f:listFilms)
			System.out.println(f.getTitreFilm() + " (" + f.getAnneeFilm() + ")");
	}

}
