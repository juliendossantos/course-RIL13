package com.rila13.hibernate;

import com.rila13.hibernate.base.BaseJouePK;

public class JouePK extends BaseJouePK {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public JouePK () {}
	
	public JouePK (
		com.rila13.hibernate.Films _codeF,
		com.rila13.hibernate.Acteurs _codeA) {

		super (
		_codeF,
		_codeA);
	}
/*[CONSTRUCTOR MARKER END]*/
}