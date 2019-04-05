import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;

import java.util.*;

import dao.*;
import paa.aemet.AEMETPrediccionService;
import paa.aemet.IPrediccion;
import paa.aemet.IPrediccionService;
import paa.aemet.PrediccionServiceException;


public class ClaseServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException {
		
    	
    	String paramAccion=request.getParameter("accion");
    	String paramMuni=request.getParameter("provincia");
    	String paramFormato=request.getParameter("formato");	
    	
    	DBAccessProvinciaDAO dbap=new DBAccessProvinciaDAO("jdbc:ucanaccess://./webapps/practica4/WEB-INF/bdatos/PAA_municipios.mdb");
    	DBAccessMunicipioDAO dbam=new DBAccessMunicipioDAO("jdbc:ucanaccess://./webapps/practica4/WEB-INF/bdatos/PAA_municipios.mdb");
    	List<Provincia> coleccProv=null;
    	List<Municipio> coleccMun=null;
		
		if ((paramAccion==null)||((!paramAccion.equals("municipios"))&&(!paramAccion.equals("anadir"))&&(!paramAccion.equals("borrar"))&&(!paramAccion.equals("actualizar"))))
			paramAccion="provincias";
		if ((paramFormato==null)||(!paramFormato.equals("json")))
			paramFormato="html";
		
		switch(paramAccion) {
		case "provincias":{
			coleccProv=dbap.findAll();
			
			switch(paramFormato) {
			case "html":{
				// Paso como parámetro al JSP de la lista de provincias
				request.setAttribute("datos", coleccProv);
				// redirigir la ejecución a la pagina jsp y cederla control
				RequestDispatcher d = request.getRequestDispatcher("/provincias.jsp");
				d.forward(request, response);
				break;
			}
			
			case "json":{
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				
				JSONArray lista=new JSONArray();
				for(Provincia p: coleccProv) {
					JSONObject o=new JSONObject();
					o.put("Nombre",p.getNombre());
					o.put("Codigo", p.getCodigo());
					lista.add(o);
				}
				out.println(lista.toJSONString());
				break;
			}
			}
			break;
		}
		
		case "municipios":{
			
			switch(paramFormato) {
			case "html":{
				response.setContentType("text/html;charset=UTF-8");
		    	PrintWriter out = response.getWriter();
				
				out.println("<h1>Municipios</h1></br>");
				if(paramMuni!=null) {
					Provincia p=dbap.find(paramMuni);
					coleccMun=dbam.findByIdProvincia(paramMuni);
					if(p==null)
						out.println("<b>ERROR: El codigo "+paramMuni+" no se corresponde con ninguna provincia de la base de datos.</b>");
					else {
						if(coleccMun.isEmpty())
							out.println("No hay ningun municipio registrado para la provincia de codigo: "+paramMuni);
						
						for(Municipio m:coleccMun) {
							out.println("<p>Codigo: "+m.getCodigo()+"<br>");
							out.println("<p>Nombre: <b>"+m.getNombre()+"</b><br>");
							out.println("<p>Id Provincia: "+m.getIdProvincia()+"<br>");	
							out.println("<p>Altitud: "+m.getAltitud()+"<br>");	
							out.println("<p>Longitud: "+m.getLongitud()+"<br></p>");
							out.println("<hr>");
						}
					}
				}
				//Si no hemos introducido ningun valor para "provincia"
				else
					out.println("<b>ERROR: Debe introducir el parametro 'provincia=valor' en la url (valor debe corresponderse con un codigo de provincia).</b>");
			
				out.println("</body></html>");
				break;
			}
			
			case "json":{
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				
				Provincia p=dbap.find(paramMuni);
				coleccMun=dbam.findByIdProvincia(paramMuni);
				
				JSONArray lista=new JSONArray();
				for(Municipio m:coleccMun) {
					JSONObject o=new JSONObject();
					o.put("Nombre",m.getNombre());
					o.put("Codigo",m.getCodigo());
					o.put("Altitud",m.getAltitud());
					o.put("Latitud",m.getLatitud());
					o.put("Longitud",m.getLongitud());
					o.put("IdProvincia",m.getIdProvincia());
					lista.add(o);
				}
				out.println(lista.toJSONString());
				break;
			}
			}
			break;
		}
		
		case "anadir":{
			String tipo=request.getParameter("tipo");
			
			switch(tipo) {
			case "provincia":{
				String nombre=request.getParameter("nombre");
				String codigo=request.getParameter("codigo");
				
				Provincia p=new Provincia(codigo,nombre);
				dbap.add(p);
				
				break;
			}
			case "municipio":{
				String nombre=request.getParameter("nombre");
				String codigo=request.getParameter("codigo");
				String idProvincia=request.getParameter("idProvincia");
				int altitud=Integer.parseInt(request.getParameter("altitud"));
				double latitud=Float.parseFloat(request.getParameter("latitud"));
				double longitud=Float.parseFloat(request.getParameter("longitud"));
				
				Municipio m=new Municipio(codigo,nombre,altitud,latitud,longitud,idProvincia);
				dbam.add(m);
				break;
			}
			}
			break;
		}
		
		case "borrar":{
			String tipo=request.getParameter("tipo");
			
			switch(tipo) {
			case "provincia":{
				String codigo=request.getParameter("codigo");
				dbap.remove(codigo);
				break;
			}
			case "municipio":{
				String codigo=request.getParameter("codigo");
				dbam.remove(codigo);
				break;
			}
			}
			break;
		}
		
		case "actualizar":{
			String tipo=request.getParameter("tipo");
			
			switch(tipo) {
			case "provincia":{
				String nombre=request.getParameter("nombre");
				String codigo=request.getParameter("codigo");
				
				Provincia p=new Provincia(codigo,nombre);
				dbap.update(p);
				break;
			}
			case "municipio":{
				String nombre=request.getParameter("nombre");
				String codigo=request.getParameter("codigo");
				String idProvincia=request.getParameter("idProvincia");
				int altitud=Integer.parseInt(request.getParameter("altitud"));
				double latitud=Float.parseFloat(request.getParameter("latitud"));
				double longitud=Float.parseFloat(request.getParameter("longitud"));
				
				Municipio m=new Municipio(codigo,nombre,altitud,latitud,longitud,idProvincia);
				dbam.update(m);
				break;
			}
			}
			break;
		}
		}
	}
}