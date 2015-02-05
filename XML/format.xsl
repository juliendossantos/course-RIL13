<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<!-- 	<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
 -->	<html>
		<head>
			<title>Catalogue</title>
			<link rel="stylesheet" type="text/css" href="style.css"/>
		</head>
		<body>
			<h1>Catalogue de CD</h1>
			<table>
				<thead>
					<tr>
						<th>Titre</th>
						<th>Artiste</th>
						<th>Pays</th>
						<th>Labels</th>
						<th>Prix</th>
						<th>Annee</th>
						<th>Promo</th>
						<th>Flag</th>
					</tr>
				</thead>
				<tbody>
					<!-- Condition :
						= (égal) 
						!= (différent de) 
						&lt; (plus petit que <) 
						&gt; (plus grand que >) -->
					<!-- <xsl:for-each select="racine/cd[pays='Canada']" > Affiche les cd donc le pays est Canada -->
					<!-- <xsl:for-each select="racine/cd[prix&gt;8]" > Affiche les cd dont le prix est supérieur à 8 -->
					<xsl:for-each select="racine/cd" >
						<xsl:sort select="annee" />
						<tr>
							<td><xsl:value-of select="titre" /></td>
							<td><xsl:value-of select="artiste" /></td>
							<td><xsl:value-of select="pays" /></td>
							<td><xsl:value-of select="labels" /></td>
							<td><xsl:value-of select="prix" /><xsl:if test="prix!=''"> €</xsl:if></td>
							<td><xsl:value-of select="annee" /></td>
							<td>
								<xsl:if test="pays='Canada'">-10%</xsl:if> 							
							</td>
							<td>
								<xsl:choose>
									<xsl:when test="pays='Canada'"><img src="img/canada.png"/></xsl:when>
									<xsl:when test="pays='UK'"><img src="img/uk.png" /></xsl:when>
									<xsl:otherwise></xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
		</body>
	</html>
</xsl:template>

</xsl:stylesheet>