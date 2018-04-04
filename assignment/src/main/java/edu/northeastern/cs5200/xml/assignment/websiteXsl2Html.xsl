<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"></xsl:output>
	<xsl:template match="website">
		<style>
			table, th, td {
			border: 1px solid black;
			}
		</style>
		<h1>
			<xsl:value-of select="@name" />
		</h1>
		<h2>Pages</h2>
		<ol>
			<xsl:apply-templates select="page" />
		</ol>
		<h2>Widgets</h2>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>type</th>
					<th>text</th>
					<th>src</th>
					<th>url</th>
					<th>label</th>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates select="page/widget" />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="page">
		<li>
			<xsl:value-of select="@name" />
			,
			<xsl:value-of select="description" />
		</li>

	</xsl:template>
	<xsl:template match="widget">

		<tr>
			<td>
				<xsl:value-of select="@id" />
			</td>
			<td>
				<xsl:value-of select="@type" />
			</td>
			<td>
				<xsl:value-of select="text" />
			</td>
			<td>
				<xsl:value-of select="@src" />
			</td>
			<td>
				<xsl:value-of select="@url" />
			</td>
			<td>
				<xsl:value-of select="@label" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>