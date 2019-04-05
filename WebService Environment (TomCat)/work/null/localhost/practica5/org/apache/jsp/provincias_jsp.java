package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.*;
import java.util.*;

public final class provincias_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
 

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
				

      out.write("\r\n");
      out.write("\t\t\t\t");
      if (true) {
        _jspx_page_context.forward("/Provincias" + (("/Provincias").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("accion", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramAccion), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("formato", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramFormato), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("provincia", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramMuni), request.getCharacterEncoding()));
        return;
      }
      out.write('\r');
      out.write('\n');


				break;
			}
			}
			break;
		}

		case 2:{

      out.write("\r\n");
      out.write("\t\t\t");
      if (true) {
        _jspx_page_context.forward("/Provincias" + (("/Provincias").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("accion", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramAccion), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("formato", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramFormato), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("provincia", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(paramMuni), request.getCharacterEncoding()));
        return;
      }
      out.write('\r');
      out.write('\n');

		break;
	}
}

      out.write(" \r\n");
      out.write("</body> \r\n");
      out.write("</html> ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
