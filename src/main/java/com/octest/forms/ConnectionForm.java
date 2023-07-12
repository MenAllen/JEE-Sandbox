package com.octest.forms;

import javax.servlet.http.HttpServletRequest;

public class ConnectionForm {
	
	private String resultat;
	
	public void verifierIdentifiants ( HttpServletRequest request) {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		if (pass != null) {
		if (pass.equals(login + "123")) {
			resultat = "Vous êtes bien connecté !";
		}
		else {
			resultat = "Identifiants incorrects";
		}
		}
		else {
			resultat = "remplissez le mot de passe";
		}
		
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

}
