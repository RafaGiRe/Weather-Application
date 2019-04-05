package gui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.DAOException;
import dao.IProvinciaDAO;
import dao.Provincia;

public class DBRemoteAccessProvinciaDAO implements IProvinciaDAO {

	public void add(Provincia p) throws DAOException {
		URL url=null;
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=anadir&tipo=provincia&nombre="+p.getNombre()+"&codigo="+p.getCodigo());
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

	public Provincia find(String cod) throws DAOException {
		URL url=null;
		Provincia p=null;
		try {
			url = new URL("http://localhost:8080/practica4/Provincias?formato=json");
			JSONParser parser=new JSONParser();
			Object o=parser.parse(new InputStreamReader(url.openStream()));

			JSONArray lista=(JSONArray)o;
			for(Object c:lista) {
				JSONObject provinciaJSON=(JSONObject)c;
				if(provinciaJSON.get("Codigo").equals(cod)) {
					p=new Provincia();
					p.setCodigo((String)provinciaJSON.get("Codigo"));
					p.setNombre((String)provinciaJSON.get("Nombre"));
				}
			}
		} catch (IOException | ParseException e) {
			throw new DAOException("Error en la capa de datos.");
		}
		return p;
	}

	public List<Provincia> findAll() throws DAOException {
		URL url=null;
		Provincia p=null;
		List<Provincia> provincias=null;
		try {
			url = new URL("http://localhost:8080/practica4/Provincias?formato=json");
			JSONParser parser=new JSONParser();
			Object o=parser.parse(new InputStreamReader(url.openStream()));

			JSONArray lista=(JSONArray)o;
			provincias=new ArrayList<Provincia>();
			for(Object c:lista) {
				JSONObject provinciaJSON=(JSONObject)c;
				p=new Provincia();
				p.setCodigo((String)provinciaJSON.get("Codigo"));
				p.setNombre((String)provinciaJSON.get("Nombre"));
				provincias.add(p);
			}
		} catch (IOException | ParseException e) {
			throw new DAOException("Error en la capa de datos.");
		}
		return provincias;
	}

	public void remove(String cod) throws DAOException {
		URL url=null;
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=borrar&tipo=provincia&codigo="+cod);
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

	public void update(Provincia p) throws DAOException {
		URL url=null;
		try {
			url=new URL("http://localhost:8080/practica4/Provincias?accion=actualizar&tipo=provincia&nombre="+p.getNombre()+"&codigo="+p.getCodigo());
			url.openStream();
		}catch (IOException e) {
			throw new DAOException("Error en la cada de datos.");
		}
	}

}
