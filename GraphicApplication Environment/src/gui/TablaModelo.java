package gui;

import javax.swing.table.AbstractTableModel;

import paa.aemet.IPrediccion;

public class TablaModelo extends AbstractTableModel{
	
	String[] colPred= {"Fecha", "Temp. minima", "Temp. maxima", "Icono", "Estado del cielo"};
	Object[] [] datosPred;
 
	public TablaModelo() {
		datosPred=new Object[7][5];
		for(int i=0;i<7;i++){
			for(int j=0;j<5;j++){
				datosPred[i][j]=new String("'");
			}
		}
	}
 
	@Override
	public int getColumnCount() {
		return colPred.length;
	}
 
	@Override
	public int getRowCount() {
		return datosPred.length;
	}
 
	@Override
	public Object getValueAt(int f, int c) {
 
		return datosPred[f][c];
	}
 
	@Override
	public String getColumnName(int c){
		return colPred[c];
	}
 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int c){
		return getValueAt(0,c).getClass();
	}
 
	@Override
	public void setValueAt(Object value, int f, int c){
		datosPred[f][c]=value;
		fireTableCellUpdated(f,c);
	}
 
	
	
	public void getPrediccion(java.util.List<IPrediccion> prediccion){
		int i=0;
		for ( IPrediccion p : prediccion ) {
			setValueAt(p.getFecha(),i,0);
			setValueAt(p.getTemperaturaMinima(),i,1);
			setValueAt(p.getTemperaturaMaxima(),i,2);
			setValueAt(p.getIconoEstadoCielo(),i,3);
			setValueAt(p.getEstadoCielo(),i,4);
			i++;
		}	
	}
 


}
