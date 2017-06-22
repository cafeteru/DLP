package ast.sentencias.util;

import ast.sentencias.Sentencia;
import ast.util.NodoPosicion;

/**
 * Implementación abstracta de la interfaz Sentencia para implementar las partes
 * comunes de todas las clases que heredan de esta interfaz.
 * 
 * @author Iván González Mahagamage
 */
public abstract class SentenciaAbstracta extends NodoPosicion
		implements Sentencia {

	public SentenciaAbstracta(int linea, int columna) {
		super(linea, columna);
	}
	
	@Override
	public boolean esAsignacion() {
		// TODO Auto-generated method stub
		return false;
	}

}
