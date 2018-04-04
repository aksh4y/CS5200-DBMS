@ECHO OFF
set CLASSPATH=.
javac ./xml/assignment/transformer.java
java xml/assignment/transformer xml/assignment/websiteXml2Html.xsl xml/assignment/website.xml xml/assignment/website.html
pause