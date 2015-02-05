package com.rila13.hibernate.tests;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.rila13.hibernate.Films;
import com.rila13.hibernate.HibernateUtil;

public class test02Creation {

	public static void main(String[] args) throws HibernateException {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		Films zeFilm = new Films();
		zeFilm.setTitreFilm("Le 5ème élément");
		zeFilm.setAnneeFilm(1997);
		session.save(zeFilm);
		tx.commit();
		
	}

}
