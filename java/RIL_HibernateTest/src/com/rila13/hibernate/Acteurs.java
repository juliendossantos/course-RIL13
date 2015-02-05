package com.rila13.hibernate;

import com.rila13.hibernate.base.BaseActeurs;

/**
 * This is the object class that relates to the acteurs table.
 * Any customizations belong here.
 */
public class Acteurs extends BaseActeurs {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Acteurs () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Acteurs (java.lang.Integer _codeActeur) {
		super(_codeActeur);
	}

	/**
	 * Constructor for required fields
	 */
	public Acteurs (
		java.lang.Integer _codeActeur,
		java.lang.String _nomActeur,
		java.lang.String _prenomActeur,
		java.lang.String _image,
		java.lang.Integer _anneeNaissanceActeur,
		java.lang.Integer _sexeActeur) {

		super (
			_codeActeur,
			_nomActeur,
			_prenomActeur,
			_image,
			_anneeNaissanceActeur,
			_sexeActeur);
	}

/*[CONSTRUCTOR MARKER END]*/
}