package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import dao.*;
import paa.aemet.AEMETPrediccionService;
import paa.aemet.IPrediccion;
import paa.aemet.IPrediccionService;
import paa.aemet.PrediccionServiceException;

public class JFramePrincipal extends JFrame {
	//componentes
	DBRemoteAccessProvinciaDAO dbap=new DBRemoteAccessProvinciaDAO();
	DBRemoteAccessMunicipioDAO dbam=new DBRemoteAccessMunicipioDAO();
	
	List<Provincia> coleccProv=null;
	List<Municipio> coleccMun=null;
	
	IPrediccionService ps = new AEMETPrediccionService();
	List<IPrediccion> lista=null;
	String guiones="------------------------------------------------------------------------------------------------";
	
	String estado="Cliente AEMET v1.0 para PAA";
	ImageIcon iAddMun=new ImageIcon("AddMun.PNG");
	ImageIcon iDelMun=new ImageIcon("DeleteMun.PNG");
	
	JMenuBar bMenu=null;
	JMenu archivo=null;
	JMenu ayuda=null;
	JMenuItem nuevoMuni=null;
	JMenuItem borrarMuni=null;
	JMenuItem salir=null;
	JMenuItem acerca=null;
	
	JLabel bEstado=null;
	JPanel zProvincia=null;
	JPanel zMunicipio=null;
	JPanel zPrediccion=null;
	GridBagConstraints c=null;
	
	JButton addMun=null;
	JButton delMun=null;
	
	JComboBox<Provincia> comProvincias=null;
	JList<Municipio> lisMunicipio=null;
	DefaultListModel<Municipio> modeloLista=null;
	JScrollPane scroller=null;
	JLabel lPred=null;
	JTable tPred=null;
	TablaModelo tModelo=new TablaModelo();
	JScrollPane scrollPred=null;
	
	JToolBar bBotones=null;
	JSplitPane zCentral=null;
	JPanel zOeste=null;
	
	String[] colPred= {"Fecha", "Temp. minima", "Temp. maxima", "Icono", "Estado del cielo"};
	Object[] [] datosPred={{"","","","",""},{"","","","",""},{"","","","",""},{"","","","",""},{"","","","",""},{"","","","",""},{"","","","",""}};
	
	
	public JFramePrincipal (String title){
		super(title);
		 
		coleccProv=dbap.findAll();
		
		
		//Barra de menu con las distintas opciones
		bMenu= new JMenuBar();
		
		archivo = new JMenu("Archivo");
		nuevoMuni = new JMenuItem("Nuevo municipio");
		borrarMuni = new JMenuItem("Borrar municipio");
		salir=new JMenuItem("Salir");
		bMenu.add(archivo);
		archivo.add(nuevoMuni);
		archivo.add(borrarMuni);
		archivo.add(salir);
		nuevoMuni.addActionListener(new ClickAddMun());
		borrarMuni.addActionListener(new ClickDelMun());
		salir.addActionListener(new ClickSalir());
		
		ayuda=new JMenu("Ayuda");
		acerca=new JMenuItem("Acerca de...");
		ayuda.add(acerca);
		bMenu.add(ayuda);
		acerca.addActionListener(new ClickAcercaDe());
		
		setJMenuBar(bMenu);
		
		//Empezamos a dividir nuestro frame
		setLayout(new BorderLayout());
		
		//Parte abajo
		bEstado=new JLabel(estado);
		bEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(bEstado, BorderLayout.SOUTH);
		
		//Parte arriba
		addMun=new JButton(iAddMun);
		delMun=new JButton(iDelMun);
		addMun.addActionListener(new ClickAddMun());
		delMun.addActionListener(new ClickDelMun());
		
		bBotones=new JToolBar();
		bBotones.add(addMun);
		bBotones.add(delMun);
		bBotones.setBackground(Color.LIGHT_GRAY);
		add(bBotones, BorderLayout.NORTH);
		
		//Parte central: 4 columnas 5 filas por ejemplo
		
		//Creamos el comboBox de Provincias y lo añadimos en la primera posicion de la cuadricula
		comProvincias=new JComboBox(coleccProv.toArray());
		comProvincias.addActionListener(new ProvinciaSeleccionada());
		comProvincias.setBackground(Color.WHITE);
		zProvincia=new JPanel();
		zProvincia.setLayout(new CardLayout());
		zProvincia.setBorder(BorderFactory.createTitledBorder("Provincias"));
		zProvincia.add(comProvincias);
		
		
		//Creamos el JList de Municipios vacío y lo añadimos debajo del comboBox y que llegue hasta abajo
		zMunicipio=new JPanel();
		
		modeloLista=new DefaultListModel<Municipio>();
		lisMunicipio=new JList(modeloLista);
		
		lisMunicipio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lisMunicipio.setVisibleRowCount(10);
		lisMunicipio.addListSelectionListener(new MunicipioSeleccionado());
		scroller= new JScrollPane(lisMunicipio);
		
		zMunicipio.setLayout(new CardLayout());
		zMunicipio.setBorder(BorderFactory.createTitledBorder("Municipios"));
		zMunicipio.add(scroller);
		
		
		//Creamos el Panel para las predicciones y lo añadimos a la derecha de la primera columna
		zPrediccion=new JPanel();
		
		lPred=new JLabel("Municipio: ");
		
		tPred=new JTable(tModelo);
		scrollPred=new JScrollPane(tPred);
		scrollPred.setMinimumSize(new Dimension(600,100));
		
		
		zPrediccion.setBorder(BorderFactory.createTitledBorder("Predicciones"));
		zPrediccion.setLayout(new BorderLayout());
		zPrediccion.add(lPred, BorderLayout.NORTH);
		zPrediccion.add(scrollPred, BorderLayout.CENTER);
		
		
		//Añadimos la zCentral al centro de nuestro BorderLayout
		zOeste=new JPanel();
		zOeste.setLayout(new BorderLayout());
		zOeste.add(zProvincia, BorderLayout.NORTH);
		zOeste.add(zMunicipio, BorderLayout.CENTER);
		zOeste.setMinimumSize(new Dimension(200,100));
		
		zCentral=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, zOeste, zPrediccion);
		zCentral.setOneTouchExpandable(true);
		zCentral.setDividerLocation(200);
		
		add(zCentral, BorderLayout.CENTER);
		
		//Añadimos tamaño a nuestra ventana y la mostramos
		setSize(1200,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void listarMunicipios() {
		Provincia p=(Provincia)comProvincias.getSelectedItem();
		coleccMun=dbam.findByIdProvincia(p.getCodigo());

		modeloLista.clear();
		for (Municipio m : coleccMun.toArray(new Municipio[0]))
			modeloLista.addElement(m);
	}
	
	
	
	public void prediccion() {
		if(lisMunicipio.getSelectedValue()!=null) {
			
			try {
				lista=ps.getPrediccionesByIdMunicipio(lisMunicipio.getSelectedValue().getCodigo());
				
				tModelo.getPrediccion(lista);
				lPred.setText("Municipio: "+lisMunicipio.getSelectedValue().getNombre());
				
			} catch (PrediccionServiceException e){
				JDialog dErrorPred=new JDialog(this,"Error",true); //Que sea modal para que el usuario se fije
				JPanel panelError=new JPanel();
				panelError.setLayout(new GridLayout(2,1,5,10));
				JLabel lErrorPred=new JLabel("Parece ser que la base de datos utilizada no es correcta...",JLabel.CENTER);
				JLabel lErrorPred2=new JLabel("No se puede obtener la prediccion de este municipio.", JLabel.CENTER);
				panelError.add(lErrorPred);panelError.add(lErrorPred2);
				
				dErrorPred.setLayout(new CardLayout());
				dErrorPred.add(panelError);
				dErrorPred.setSize(500,100);
				dErrorPred.setVisible(true);
			}
		}
	}
	
	

	//Clases internas
	
	public class ClickSalir implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFramePrincipal.this.dispose();
		}
	}
	
	
	public class ClickAddMun implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JAddMun ventanaAddMun=new JAddMun(JFramePrincipal.this);
		}	
	}
	
	public class ClickDelMun implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			JDelMun ventanaDelMun=new JDelMun(JFramePrincipal.this);
		}
	}
	
	public class ClickAcercaDe implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			JAcercaDe ventanaAcerca=new JAcercaDe(JFramePrincipal.this, estado);
		}
	}
	
	public class ProvinciaSeleccionada implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFramePrincipal.this.listarMunicipios();
		}
	}
	
	public class MunicipioSeleccionado implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			JFramePrincipal.this.prediccion();
		}
	}

}
