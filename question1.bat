@ECHO OFF
set CLASSPATH=.
javac ./xml/assignment/transformer.java
java xml/assignment/transformer xml/assignment/site2website.xsl xml/assignment/site.xml xml/assignment/website.xml
pause