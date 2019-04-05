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

import dao.DBAccessMunicipioDAO;
import dao.DBAccessProvinciaDAO;
import dao.Municipio;
import dao.Provincia;

public class JDelMun extends JDialog{
	
	String titulo="Borrar municipio";
	boolean modal=true;
	JFramePrincipal JPadre=null;
	
	Provincia pEnComboAnterior=null;
	Municipio mEnListAnterior=null;
	List<Municipio> listaMun=null;
	
	JDialog dDel=null;
	JPanel datos=null;
	JPanel botones=null;
	
	JLabel lProv=new JLabel("Provincia: ",JLabel.RIGHT);
	JLabel lMun=new JLabel("Municipio: ",JLabel.RIGHT);

	
	JComboBox<Provincia> comProv=null;
	JComboBox<Municipio> comMun=null;
	
	JButton bOk=null;
	JButton bCancelar=null;
	
	public JDelMun(JFramePrincipal padre) {
		
		JPadre=padre;
		this.listaMun=padre.coleccMun;

		pEnComboAnterior=(Provincia)padre.comProvincias.getSelectedItem();
		mEnListAnterior=padre.lisMunicipio.getSelectedValue();
		
		dDel=new JDialog(padre, titulo, modal);
		
		//Usamos BorderLayout para poner en el centro los huecos a rellenar y abajo los botones.
		dDel.setLayout(new BorderLayout());
		
		//Creamos los diferentes huecos para rellenar y los añadimos desde un JPanel a la ventana
		datos=new JPanel();
		datos.setLayout(new GridLayout(2,2,5,10));

		comProv=new JComboBox(padre.coleccProv.toArray());
		comProv.setSelectedItem(pEnComboAnterior);
		comProv.addActionListener(new ClickProvincia());
		
		if(listaMun==null)
			comMun=new JComboBox();
		else
			comMun=new JComboBox(JPadre.modeloLista.toArray());
		
		comMun.setSelectedItem(mEnListAnterior);

		datos.add(lProv);datos.add(comProv);
		datos.add(lMun);datos.add(comMun);

		dDel.add(datos, BorderLayout.CENTER);
		
		//Creamos los botones y los añadimos en un JPanel a la parte de abajo de la ventana
		botones=new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.CENTER));
		bOk=new JButton("OK");
		bOk.addActionListener(new ClickOK());
		bCancelar=new JButton("Cancelar");
		bCancelar.addActionListener(new ClickCancelar());
		botones.add(bOk);
		botones.add(bCancelar);
		
		dDel.add(botones, BorderLayout.SOUTH);
		
		dDel.pack();
		dDel.setVisible(true);
	}
	
	
	//Clases internas
	public class ClickProvincia implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			comMun.removeAllItems();
			
			Provincia p=(Provincia)comProv.getSelectedItem();
			listaMun=JPadre.dbam.findByIdProvincia(p.getCodigo());
			
			for (Municipio m:listaMun) {
				comMun.addItem(m);
			}
		}
	}
	
	public class ClickOK implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Municipio m=(Municipio)comMun.getSelectedItem();
			
			if(m!=null) {
				JPadre.dbam.remove(m.getCodigo());
				JDialog dOK=new JDialog(JDelMun.this,"OK",true);
				JLabel lOK=new JLabel("Municipio borrado correctamente.",JLabel.CENTER);
				dOK.setLayout(new CardLayout());
				dOK.add(lOK);
				dOK.setSize(300,100);
				dDel.dispose();
				
				//El problema de hacer esto es que la coleccion de Municipios
				//de la ventana principal no se actualiza. Se hace desde aquí,
				//pero es que es más complicado eso que directamente hacer:
				//JPadre.listarMunicipios();
				//Pero entonces no serviría para nada utilizar el modelo de JList.
				JPadre.coleccMun=JPadre.dbam.findByIdProvincia(pEnComboAnterior.getCodigo());
				JPadre.modeloLista.removeElement(m);
				
				dOK.setVisible(true);
			}else {
				JDialog dCampoIncom=new JDialog(JDelMun.this,"Error",true);
				JLabel lCampoIncom=new JLabel("Debe seleccionar un municipio a borrar.",JLabel.CENTER);
				dCampoIncom.setLayout(new CardLayout());
				dCampoIncom.add(lCampoIncom);
				dCampoIncom.setSize(300,100);
				dCampoIncom.setVisible(true);
			}
		}
	}
	
	public class ClickCancelar implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dDel.dispose();
		}
	}
}
