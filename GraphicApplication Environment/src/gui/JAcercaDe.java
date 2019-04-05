package gui;

import javax.swing.*;

public class JAcercaDe extends JDialog{
	
	String titulo="Acerca de...";
	boolean modal=false;
	
	JDialog dAcerca=null;
	
	JLabel infor=null;
	
	public JAcercaDe (JFrame padre, String estado) {
		dAcerca=new JDialog(padre, titulo, modal);
		
		infor=new JLabel(estado);
		dAcerca.add(infor);
		dAcerca.setSize(200,100);
		dAcerca.setVisible(true);
	}
}
