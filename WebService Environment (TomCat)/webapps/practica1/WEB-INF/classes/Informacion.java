import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Demo que retorna una página con la información
 */

public class Informacion extends HttpServlet {


	public void init(ServletConfig servletConfig) throws ServletException {
	    super.init(servletConfig);
		// No hay inicialización propia, solo como ejemplo.
	}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>"); 
		out.println("<title>Servlet Info </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Informaciones del contexto obtenidas del objeto ServletContext</h2>");
		ServletContext sc = this.getServletContext();
		out.println("<ul>");
		out.println("<li> Máxima versión de servlet soportada por el contenedor: "+ sc.getMajorVersion()+"</li>");
		out.println("<li> Mínina versión de servlet soportada por el contenedor: "+ sc.getMinorVersion()+"</li>");		     
		out.println("<li> Path real del sitio web  en el servidor     : "+ sc.getRealPath("/")+"</li>");		     
		out.println("<li> Nombre y versión del contenedor                      : "+ sc.getServerInfo()+"</li>");		     
		out.println("<li> Estructura de directorios de la raiz del sitio web   : "+ sc.getResourcePaths("/")+"</li>");		     
		out.println("<li> Tipo MIME del fichero index.html                     : "+ sc.getMimeType("index.html")+"</li>");		     
		out.println("</ul>");	        
	    out.println("<h3>Información de los parámetros de configuracion (formato nombreparametro=valor)</h3>");
		Enumeration<String> parametrosConfiguracion = sc.getInitParameterNames();
		if( parametrosConfiguracion.hasMoreElements() )
		{
			out.println("<ul>");
			do {
				String parametro = parametrosConfiguracion.nextElement();
				String valor= sc.getInitParameter(parametro);
				out.println("<li>"+parametro+"=" + valor );

			} while( parametrosConfiguracion.hasMoreElements() );
			out.println("</ul>");                
		}
		else
			out.println("<p>No se han recibido parámetros</p>"); 
	
		out.println("<h2>Información de la petición obtenida de HttpServletRequest</h2>");
		 out.println("<ul>");
         out.println("<li>protocolo:" + request.getProtocol() );
         out.println("<li>método:" + request.getMethod() );
         out.println("<li>uri:" + request.getRequestURI());
         out.println("<li>path:" +request.getPathInfo());
         out.println("<li>dir remota:" +  request.getRemoteAddr());
		 out.println("<li>host remota:" +  request.getRemoteHost());
         out.println("<li>servidor:" +  request.getServerName());
         out.println("<li>puerto:" +  request.getServerPort());
         out.println("<li>contexto:" +  request.getContextPath());
		 out.println("</ul>");	        
	 
		out.println("<h3>Información de los parámetros recibidos (formato nombreparametro=valor)</h3>");
		Enumeration<String> nombres = request.getParameterNames();
		if( nombres.hasMoreElements() )
		{
			out.println("<ul>");
			do {
				String parametro = nombres.nextElement();
				String valor= request.getParameter(parametro);
				out.println("<li>"+parametro+"=" + valor );

			} while( nombres.hasMoreElements() );
			out.println("</ul>");                
		}
		else
			out.println("<p>No se han recibido parámetros</p>"); 

		 out.println("<h3>Información de las cabeceras recibidas</h3>");
         out.println("<ul>");
         Enumeration e = request.getHeaderNames();
         while (e.hasMoreElements()) {
            String headerName = (String)e.nextElement();
            String headerValue = request.getHeader(headerName);
            out.println("<li>Nombre:" + headerName + ",Valor="+headerValue);
         }
         out.println("</ul>");
		out.println("</div>"); 
		out.println("</body>");
		out.println("</html>");            
	
    }

//-------------------

    	    @Override
    	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        processRequest(request, response);
    	    } 
    	    @Override
    	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        processRequest(request, response);
    	    }
 
}