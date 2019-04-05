package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import dao.*;

public class JAddMun extends JDialog{
	
	String titulo="Nuevo municipio";
	boolean modal=true;
	JFramePrincipal JPadre=null;
	
	Provincia pEnComboAnterior=null;
	
	JDialog dAdd=null;
	JPanel datos=null;
	JPanel botones=null;
	
	JLabel lCod=new JLabel("Codigo: ",JLabel.RIGHT); 
	JLabel lNom=new JLabel("Nombre: ",JLabel.RIGHT);
	JLabel lProv=new JLabel("Provincia: ",JLabel.RIGHT);
	JLabel lAlt=new JLabel("Altitud: ",JLabel.RIGHT);
	JLabel lLat=new JLabel("Latitud: ",JLabel.RIGHT);
	JLabel lLon=new JLabel("Longitud: ",JLabel.RIGHT);
	
	JTextField hCod=new JTextField(20);
	JTextField hNom=new JTextField(20);
	JComboBox<Provincia> comProv=null;
	JTextField hAlt=new JTextField(20);
	JTextField hLat=new JTextField(20);
	JTextField hLon=new JTextField(20);
	SpinnerNumberModel modAlt=null;
	JSpinner spiAlt=null;
	SpinnerNumberModel modLat=null;
	JSpinner spiLat=null;
	SpinnerNumberModel modLong=null;
	JSpinner spiLong=null;
	
	JButton bOk=null;
	JButton bCancelar=null;
	
	public JAddMun(JFramePrincipal padre) {
		
		this.JPadre=padre;
		dAdd=new JDialog(padre, titulo, modal);
		
		//Usamos BorderLayout para poner en el centro los huecos a rellenar y abajo los botones.
		dAdd.setLayout(new BorderLayout());
		
		//Creamos los diferentes huecos para rellenar y los añadimos desde un JPanel a la ventana
		datos=new JPanel();
		datos.setLayout(new GridLayout(6,2,5,10));

		pEnComboAnterior=(Provincia)padre.comProvincias.getSelectedItem();
		
		comProv=new JComboBox(padre.coleccProv.toArray());
		comProv.setSelectedItem(pEnComboAnterior);
		
		
		modAlt=new SpinnerNumberModel(0,0,10000,10);
		spiAlt=new JSpinner(modAlt);
		modLat=new SpinnerNumberModel(0,-90,90,0.1);
		spiLat=new JSpinner(modLat);
		modLong=new SpinnerNumberModel(0,-180,180,0.1);
		spiLong=new JSpinner(modLong);
		
		datos.add(lCod);datos.add(hCod);
		datos.add(lNom);datos.add(hNom);
		datos.add(lProv);datos.add(comProv);
		datos.add(lAlt);/*datos.add(hAlt);*/datos.add(spiAlt);
		datos.add(lLat);/*datos.add(hLat);*/datos.add(spiLat);
		datos.add(lLon);/*datos.add(hLon);*/datos.add(spiLong);
		
		dAdd.add(datos, BorderLayout.CENTER);
		
		//Creamos los botones y los añadimos en un JPanel a la parte de abajo de la ventana
		botones=new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.CENTER));
		bOk=new JButton("OK");
		bCancelar=new JButton("Cancelar");
		bOk.addActionListener(new ClickOK());
		bCancelar.addActionListener(new ClickCancelar());
		botones.add(bOk);
		botones.add(bCancelar);
		
		dAdd.add(botones, BorderLayout.SOUTH);
		
		
		dAdd.pack();
		dAdd.setVisible(true);
	}
	
	public class ClickOK implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JDialog dErrorFormato=null;
			
			Provincia provincia=(Provincia)comProv.getSelectedItem();
			String codigo=hCod.getText();
			String nombre=hNom.getText();
			String codigoProvincia=provincia.getCodigo();
			int altitud=0;
			float latitud=0;
			float longitud=0;
			
			try{
				//altitud=Integer.parseInt(hAlt.getText());
				altitud=(int)spiAlt.getValue();
				//latitud=Float.valueOf(hLat.getText());
				latitud=(float)(double)spiLat.getValue();
				//longitud=Float.valueOf(hLon.getText());
				longitud=(float)(double)spiLong.getValue();
			}catch(NumberFormatException e) {
				dErrorFormato=new JDialog(JAddMun.this,"Error",true);
				JLabel lErrorFormato=new JLabel("Debe introducir valores numericos para la altitud, latitud y longitud.",JLabel.CENTER);
				dErrorFormato.setLayout(new CardLayout());
				dErrorFormato.add(lErrorFormato);
				dErrorFormato.setSize(500,100);
				dErrorFormato.setVisible(true);
			}
			
			if (dErrorFormato!=null){
				
			//}else if(hCod.getText().equals("")||hNom.getText().equals("")||hAlt.getText().equals("")|hLat.getText().equals("")||hLon.getText().equals("")){
			}else if(hCod.getText().equals("")||hNom.getText().equals("")){	
				JDialog dCampoIncom=new JDialog(JAddMun.this,"Error",true);
				JLabel lCampoIncom=new JLabel("Debe completar todos los campos.",JLabel.CENTER);
				dCampoIncom.setLayout(new CardLayout());
				dCampoIncom.add(lCampoIncom);
				dCampoIncom.setSize(300,100);
				dCampoIncom.setVisible(true);
			}else {
				Municipio m=new Municipio(codigo, nombre, altitud, latitud, longitud, codigoProvincia);
				JPadre.dbam.add(m);
				JDialog dOK=new JDialog(JAddMun.this,"OK",true);
				JLabel lOK=new JLabel("Municipio añadido correctamente.",JLabel.CENTER);
				dOK.setLayout(new CardLayout());
				dOK.add(lOK);
				dOK.setSize(300,100);
				dAdd.dispose();
				
				//El problema de hacer esto es que la coleccion de Municipios
				//de la ventana principal no se actualiza. Se hace desde aquí,
				//pero es que es más complicado eso que directamente hacer:
				//JPadre.listarMunicipios();
				//Pero entonces no serviría para nada utilizar el modelo de JList.
				JPadre.coleccMun=JPadre.dbam.findByIdProvincia(pEnComboAnterior.getCodigo());
				JPadre.modeloLista.addElement(m); 
				
				dOK.setVisible(true);
			}
			
		}
	}
		
	public class ClickCancelar implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dAdd.dispose();
		}
	}
}
