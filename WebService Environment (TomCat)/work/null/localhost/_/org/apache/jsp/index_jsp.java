package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
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
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\r\n");
      out.write("   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
      out.write("    <head>\r\n");
      out.write("    <title>Laboratorio de Programación Avanzada de Aplicaciones - 2010-2011</title>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("    /*<![CDATA[*/\r\n");
      out.write("\t*{padding:0;border:0;\t}\r\n");
      out.write("      body {\r\n");
      out.write("\t\tcolor: #000000;\r\n");
      out.write("\t\tbackground-color: #6E8C96;\r\n");
      out.write("\t\tfont-family: Arial, \"Times New Roman\", Times, serif;\r\n");
      out.write("\t\tfont-size:14px;\r\n");
      out.write("        margin: 10px 10px;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("    img {\r\n");
      out.write("       border: none;\r\n");
      out.write("\t   vertical-align:middle;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    a:link, a:visited {\r\n");
      out.write("        color: rgb(190,30,92);\r\n");
      out.write("    }\r\n");
      out.write(" \r\n");
      out.write("    a:hover {\r\n");
      out.write("\t\tcolor:#ffffff;\r\n");
      out.write("        background-color: rgb(190,30,92);\r\n");
      out.write("    }\r\n");
      out.write("\t\r\n");
      out.write("    #contenido {\r\n");
      out.write("\t\tmargin:15px;\r\n");
      out.write("\t\tpadding:20px;\r\n");
      out.write("\t\twidth:650px;\r\n");
      out.write("\t\tmargin: 0 auto;\r\n");
      out.write("\t\tvertical-align:middle;\r\n");
      out.write("\t\tborder:  1px #FFFFFF;\r\n");
      out.write("    }\r\n");
      out.write("\t#contenido div{\r\n");
      out.write("\t\tcolor: rgb(190,30,92);\r\n");
      out.write("\t\tbackground-color: #ffffff;\r\n");
      out.write("\t\tmargin:6px;\r\n");
      out.write("\t\tpadding: 5px;\t\t\r\n");
      out.write("\t\tfont-size:1.5em;\r\n");
      out.write("\t\tfont-weigth:bold;\r\n");
      out.write("\t}\r\n");
      out.write("\tul{\r\n");
      out.write("\t\tmargin-left:120px;\r\n");
      out.write("\t\tlist-style-type: square;\r\n");
      out.write("\t}\r\n");
      out.write("     /*]]>*/\r\n");
      out.write("   </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"contenido\">\r\n");
      out.write("\t<div>\r\n");
      out.write("\t<img src=\"PAA.png\" width=\"120\"/><br>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t");

	for(int i=1; i<6;i++){ 
      out.write("\r\n");
      out.write("\t\t<li><a href=\"/practica");
      out.print(i);
      out.write("\" title=\"Acceso a la Práctica ");
      out.print(i);
      out.write("\">Práctica ");
      out.print(i);
      out.write("</a></li>\r\n");
      out.write("\t");
}
	
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
