package gui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.DAOException;
import dao.IMunicipioDAO;
import dao.Municipio;
import dao.Provincia;

public class DBRemoteAccessMunicipioDAO implements IMunicipioDAO{

	public void add(Municipio m) throws DAOException {
		URL url=null;
		
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=anadir&tipo=municipio&nombre="+m.getNombre()+"&codigo="+m.getCodigo()+"&idProvincia="+m.getIdProvincia()+"&altitud="+m.getAltitud()+"&latitud="+m.getLatitud()+"&longitud="+m.getLongitud());
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

	public Municipio find(String cod) throws DAOException {
		Municipio m=null;
		Integer codProv=Integer.parseInt(cod)/1000;
		try {
			List<Municipio> listaMun=this.findByIdProvincia(codProv.toString());
			
			for(Municipio aux:listaMun)
				if(aux.getCodigo().equals(cod))
					m=aux;
		}catch(DAOException e) {
			throw e;
		}
		return m;
	}

	public List<Municipio> findAll() throws DAOException {
		DBRemoteAccessProvinciaDAO dbap=new DBRemoteAccessProvinciaDAO();
		List<Municipio> listaMun=new ArrayList<Municipio>();
		
		try {
			List<Provincia> listaProv=dbap.findAll();
			for(Provincia p:listaProv) {
				List<Municipio> listaAuxMun=this.findByIdProvincia(p.getCodigo());
				for(Municipio m:listaAuxMun)
					listaMun.add(m);
			}
		}catch(DAOException e) {
			throw e;
		}
		return listaMun;
	}

	
	public List<Municipio> findByIdProvincia(String cod) throws DAOException {
		URL url=null;
		Municipio m=null;
		List<Municipio> municipios=null;
		try {
			url = new URL("http://localhost:8080/practica4/Provincias?formato=json&accion=municipios&provincia="+cod);
			JSONParser parser=new JSONParser();
			Object o=parser.parse(new InputStreamReader(url.openStream()));

			JSONArray lista=(JSONArray)o;
			municipios=new ArrayList<Municipio>();
			for(Object c:lista) {
				JSONObject municipioJSON=(JSONObject)c;
				m=new Municipio();
				m.setCodigo((String)municipioJSON.get("Codigo"));
				m.setNombre((String)municipioJSON.get("Nombre"));
				m.setAltitud((int)((long)municipioJSON.get("Altitud")));
				m.setIdProvincia((String)municipioJSON.get("IdProvincia"));
				m.setLatitud((double)municipioJSON.get("Latitud"));
				m.setLongitud((double)municipioJSON.get("Longitud"));
				municipios.add(m);
			}
		} catch (IOException | ParseException e) {
			throw new DAOException("Error en la capa de datos.");
		}
		return municipios;
	}

	public void remove(String cod) throws DAOException {
		URL url=null;
		
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=borrar&tipo=municipio&codigo="+cod);
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

	public void update(Municipio m) throws DAOException {
		URL url=null;
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=actualizar&tipo=municipio&nombre="+m.getNombre()+"&codigo="+m.getCodigo()+"&idProvincia="+m.getIdProvincia()+"&altitud="+m.getAltitud()+"&latitud="+m.getLatitud()+"&longitud="+m.getLongitud());
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

}
