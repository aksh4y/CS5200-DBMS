<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="site">
		<website>
			<xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
			<description><xsl:value-of select="@description"/></description>
			<xsl:apply-templates select="views" />
		</website>
	</xsl:template>
	<xsl:template match="site/views">
		<xsl:for-each select="view">
			<page>
				<xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
				<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
				<description>
					<xsl:value-of select="description" />
				</description>
				<xsl:for-each select="component">
					<xsl:variable name="refID" select="@ref" />
					<xsl:for-each select="//component[@id=$refID]">
						<xsl:element name="widget">
							<xsl:copy-of select="@*" />

							<xsl:copy-of select="*" />
						</xsl:element>
					</xsl:for-each>
				</xsl:for-each>
			</page>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>