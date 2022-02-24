package dad.multiarcade.buscaminas.model;

import javafx.scene.control.Button;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *         <p>
 *         Clase que hereda de javafx.scene.control.Button
 *         </p>
 */
public class Mina extends Button {
// Atributos
	private int estado;
	private boolean esMina;
	private int contador;
	private boolean casillaVisible;

	/**
	 * Constructor de la clase Mina.
	 */
	// Constructor
	public Mina() {
		setEstado(0);
		setEsMina(false);
		setContador(0);
		setCasillaVisible(false);
	}

	/**
	 * Devuelve el valor de la variable estado, la cual representa si la casilla
	 * contiene una bandera, una interrogación o si no contiene nada.
	 * 
	 * @return número entero contenido en la variable estado.
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Devuelve el valor de la variable esMina, la cual representa si la casilla
	 * contiene una mina o no.
	 * 
	 * @return true si contiene una mina o false si no.
	 */
	public boolean isEsMina() {
		return esMina;
	}

	/**
	 * Asigna el valor de la variable estado, la cual representa si la casilla
	 * contiene una bandera, una interrogación o si no contiene nada.
	 * 
	 * @param estado número entero que sustituirá el contenido de la variable
	 *               estado.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Asigna el valor de la variable esMina, la cual representa si la casilla
	 * contiene una mina o no.
	 * 
	 * @param esMina boolean que sustituirá el contenido de la variable esMina.
	 */
	public void setEsMina(boolean esMina) {
		this.esMina = esMina;
	}

	/**
	 * Devuelve el valor de la variable contador, la cual representa el número de
	 * minas que se encuentran alrededor de la casilla.
	 * 
	 * @return numero entero contenido en la variable contador.
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * Asigna el valor de la variable contador, la cual representa el número de
	 * minas que se encuentran alrededor de la casilla.
	 * 
	 * @param contador numero entero que sustituirá el contenido de la variable
	 *                 contador.
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}

	/**
	 * Devuelve el valor de la variable casillaVisible, la cual representa si la
	 * casilla está "volteada" o no.
	 * 
	 * @return true si la casilla está "volteada", false si no.
	 */
	public boolean isCasillaVisible() {
		return casillaVisible;
	}

	/**
	 * Asigna el valor de la variable casillaVisible, la cual representa si la
	 * casilla está "volteada" o no.
	 * 
	 * @param minaVisible boolean que sustituirá el contenido de la variable
	 *                    casillaVisible.
	 */
	public void setCasillaVisible(boolean minaVisible) {
		this.casillaVisible = minaVisible;
	}

	/**
	 * Función que cambia al estado siguiente de una casilla (de nada a bandera, de
	 * bandera a interrogación y de interrogación a nada).
	 */
	public void cambiarEstado() {
		if (getEstado() == 0) {
			setEstado(1);
		} else if (getEstado() == 1) {
			setEstado(2);
		} else {
			setEstado(0);
		}
	}
}