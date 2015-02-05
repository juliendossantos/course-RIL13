<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<head>
				<title>Catalogue</title>
				<link rel="stylesheet" type="text/css" href="style.css"/>
			</head>
			<body>
				<h1>Catalogue de CD</h1>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="cd">
		<div>
			<xsl:apply-templates select="titre"/>
			<xsl:if test="prix!=''">
				<xsl:apply-templates select="prix"/>
			</xsl:if>
		</div>
	</xsl:template>
	
	<xsl:template match="titre">
		<h2><xsl:value-of select="."/></h2>
	</xsl:template>

	<xsl:template match="prix">
		<p><strong>Prix : </strong> <xsl:value-of select="."/> €</p>
	</xsl:template>

</xsl:stylesheet>