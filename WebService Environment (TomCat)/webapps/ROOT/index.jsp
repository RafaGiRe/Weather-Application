<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="false" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
    <title>Laboratorio de Programación Avanzada de Aplicaciones - 2010-2011</title>
    <style type="text/css">
    /*<![CDATA[*/
	*{padding:0;border:0;	}
      body {
		color: #000000;
		background-color: #6E8C96;
		font-family: Arial, "Times New Roman", Times, serif;
		font-size:14px;
        margin: 10px 10px;
      }

    img {
       border: none;
	   vertical-align:middle;
    }
    
    a:link, a:visited {
        color: rgb(190,30,92);
    }
 
    a:hover {
		color:#ffffff;
        background-color: rgb(190,30,92);
    }
	
    #contenido {
		margin:15px;
		padding:20px;
		width:650px;
		margin: 0 auto;
		vertical-align:middle;
		border:  1px #FFFFFF;
    }
	#contenido div{
		color: rgb(190,30,92);
		background-color: #ffffff;
		margin:6px;
		padding: 5px;		
		font-size:1.5em;
		font-weigth:bold;
	}
	ul{
		margin-left:120px;
		list-style-type: square;
	}
     /*]]>*/
   </style>
</head>

<body>
<div id="contenido">
	<div>
	<img src="PAA.png" width="120"/><br>
	<ul>
	<%
	for(int i=1; i<6;i++){ %>
		<li><a href="/practica<%=i%>" title="Acceso a la Práctica <%=i%>">Práctica <%=i%></a></li>
	<%}
	%>
	</ul>
	<div>
</div>
</body>
</html>
