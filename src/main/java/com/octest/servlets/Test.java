package com.octest.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.octest.forms.ConnectionForm;

import com.octest.beans.Auteur;
import com.octest.bdd.Noms;
import com.octest.beans.Utilisateur;
import com.octest.dao.*;

@WebServlet("/Test")
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilisateurDao utilisateurDao;
    
    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/Users/phili/fichiers/"; // A changer

    public void init() throws ServletException {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String[] titres = { "Titre1", "Titre2", "Titre3" }; 
    	
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("prenom")) {
                    request.setAttribute("prenom", cookie.getValue());
                    System.out.println(cookie.getValue());
                }
            }
        }

        try {
        	request.setAttribute("utilisateurs", utilisateurDao.lister());
        }
        catch (Exception e) {
        	request.setAttribute("erreur",  e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
 /* formulaire pour connection  	ConnectionForm form = new ConnectionForm();
    	form.verifierIdentifiants(request);  	
    	request.setAttribute("form", form);
 */

/* Upload fichier
    	// On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
        }
        
    	
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        HttpSession session = request.getSession();

        session.setAttribute("nom", nom);
        session.setAttribute("prenom", prenom);
*/
    	String nom = request.getParameter("nom");
    	String prenom = request.getParameter("prenom");
        
    	Cookie cookie = new Cookie("prenom", prenom);
    	cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
        
        try {
        	Utilisateur utilisateur = new Utilisateur();
        	utilisateur.setNom(request.getParameter("nom"));
        	utilisateur.setPrenom(request.getParameter("prenom"));
        
        	utilisateurDao.ajouter(utilisateur);
        
        	request.setAttribute("utilisateurs", utilisateurDao.lister());
    	}
        catch (Exception e) {
        	request.setAttribute("erreur",  e.getMessage());
        }

    	this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }
    
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    } 
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   
    
    

}