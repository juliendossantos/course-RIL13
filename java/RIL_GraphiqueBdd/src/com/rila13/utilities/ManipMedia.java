package com.rila13.utilities;

import java.net.URL;

import javax.swing.ImageIcon;

public class ManipMedia {
	public static ImageIcon vaChercher(String fic) {
		ImageIcon ret = null;
		
		URL imageUrl = ManipMedia.class.getResource("/"+fic);
		if(imageUrl !=null) {
			ret = new ImageIcon(imageUrl);
		}
		else {
			ret = new ImageIcon(fic);
		}
		
		return ret;
	}
}
