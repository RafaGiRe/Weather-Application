<%@page import="dao.*" %> 
<%@page import="java.util.*" %>

<% 

List<Provincia> coleccProv = (List<Provincia>) request.getAttribute("datos"); 

if(coleccProv==null){			//Fase 1
	DBAccessProvinciaDAO dbap=new DBAccessProvinciaDAO("jdbc:ucanaccess://./webapps/practica5/WEB-INF/bdatos/PAA_municipios.mdb");
	coleccProv=dbap.findAll();
}


String paramAccion=request.getParameter("accion");
String paramMuni=request.getParameter("provincia");
String paramFormato=request.getParameter("formato");	
int pmAccion=0;
int pmFormato=0;
		
	if ((paramAccion==null)||((!paramAccion.equals("municipios"))&&(!paramAccion.equals("anadir"))&&(!paramAccion.equals("borrar"))&&(!paramAccion.equals("actualizar")))){
		paramAccion="provincias";
		pmAccion=1;
	}
		if ((paramFormato==null)||(!paramFormato.equals("json"))){
			paramFormato="html";
		}	pmFormato=1;

if(paramAccion.equals("provincias"))
	pmAccion=1;
else if(paramAccion.equals("municipios"))
	pmAccion=2;
if(paramFormato.equals("html"))
	pmFormato=1;
else if(paramFormato.equals("json"))
	pmFormato=2;

		switch(pmAccion) {
		case 1:{
			
			switch(pmFormato) {
			case 1:{
				response.setContentType("text/html;charset=UTF-8");
		    		
		    	out.println("<html><head><title>Practica 5</title></head><body>");
				out.println("<h1>Provincias</h1>");
				out.println("<ul>");
				for (Provincia p:coleccProv) {
									out.println("<li><a href=\"http://localhost:8080/practica5/Provincias?accion=municipios&provincia="+p.getCodigo()+"\">"+p.toString()+"</a></li>");
				}
				out.println("</ul>");
				break;
			}
			
			case 2:{
				
%>
				<jsp:forward page="/Provincias">

				<jsp:param name="accion" value="<%=paramAccion%>"/>
				<jsp:param name="formato" value="<%=paramFormato%>"/>
				<jsp:param name="provincia" value="<%=paramMuni%>"/>
				</jsp:forward>
<%

				break;
			}
			}
			break;
		}

		case 2:{
%>
			<jsp:forward page="/Provincias">

			<jsp:param name="accion" value="<%=paramAccion%>"/>
			<jsp:param name="formato" value="<%=paramFormato%>"/>
			<jsp:param name="provincia" value="<%=paramMuni%>"/>

			</jsp:forward>
<%
		break;
	}
}
%> 
</body> 
</html> 