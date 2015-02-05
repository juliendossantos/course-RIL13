package com.rila13.hibernate;

import com.rila13.hibernate.base.BaseJoue;

/**
 * This is the object class that relates to the joue table.
 * Any customizations belong here.
 */
public class Joue extends BaseJoue {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Joue () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Joue (com.rila13.hibernate.JouePK _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Joue (
		com.rila13.hibernate.JouePK _id,
		java.lang.String _casting) {

		super (
			_id,
			_casting);
	}

/*[CONSTRUCTOR MARKER END]*/
}