<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%--    <p><c:out value="${ variable }">Salut !</c:out></p>
    
    <c:set var="pseudo" value="Mateo21" scope="page" />
    <c:out value="Bonjour ${ pseudo } !" />
    <c:remove var="pseudo" scope="page" />
    

    <c:if test="${ 50 > 10 }" var="variable">
    	C'est vrai !
	</c:if>
	
	<c:forEach var="i" begin="0" end="10" step="2">
    	<p>Un message n°<c:out value="${ i }" /> !</p>
	</c:forEach>
	
	<c:forEach items="${ titres }" var="titre" varStatus="status">
    	<p>N°<c:out value="${ status.count }" /> : <c:out value="${ titre }" /> !</p>
	</c:forEach>
	
	<c:forTokens var="morceau" items="Un élément/Encore un autre élément/Un dernier pour la route" delims="/ ">
    	<p>${ morceau }</p>
	</c:forTokens>
--%>
<%-- formulaire pour connection	<c:if test="${ !empty form }"><p><c:out value="${ form.resultat }" /></p></c:if> 
        
    <form method="post" action="bonjour">
        
       <p>
	       <label for="login">Login : </label>
    	   <input type="text" name="login" id="login" />       
       </p>
       <p>
	       <label for="pass">Mot de passe : </label>
    	   <input type="password" name="pass" id="pass" />       
       </p>
       
       <input type="submit" />

    </form>
--%>

<%-- formulaire pour upoad fichier 
     <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>

    	<form method="post" action="bonjour" enctype="multipart/form-data">
        	<p>
            	<label for="description">Description du fichier : </label>
            	<input type="text" name="description" id="description" />
        	</p>
        	<p>
            	<label for="fichier">Fichier à envoyer : </label>
            	<input type="file" name="fichier" id="fichier" />
        	</p>
        
        	<input type="submit" />
    	</form>
    	 --%> 

<%-- formulaire pour data session
    <c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
        <p>Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom } !</p>
    </c:if>
--%>
    <c:out value="cookie trouvé: ${ prenom }"/>
    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
        
    <form method="post" action="bonjour">
        <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
        
        <input type="submit" />
    </form>
    
    	<p>Voici la liste des éléments de la table noms dans la BDD :</p>
       <ul>
        <c:forEach var="utilisateur" items="${ utilisateurs }">
            <li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
        </c:forEach>
    </ul> 
                
    </body>
</html>