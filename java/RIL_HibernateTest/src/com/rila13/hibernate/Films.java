package com.rila13.hibernate;

import com.rila13.hibernate.base.BaseFilms;

/**
 * This is the object class that relates to the films table.
 * Any customizations belong here.
 */
public class Films extends BaseFilms {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Films () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Films (java.lang.Integer _codeFilm) {
		super(_codeFilm);
	}

	/**
	 * Constructor for required fields
	 */
	public Films (
		java.lang.Integer _codeFilm,
		java.lang.String _titreFilm,
		java.lang.Integer _anneeFilm) {

		super (
			_codeFilm,
			_titreFilm,
			_anneeFilm);
	}

/*[CONSTRUCTOR MARKER END]*/
}