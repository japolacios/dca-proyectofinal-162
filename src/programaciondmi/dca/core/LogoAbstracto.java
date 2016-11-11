package programaciondmi.dca.core;

import processing.core.PApplet;
import processing.core.PShape;
import programaciondmi.dca.ejecucion.Mundo;

/**
 * @author Damanzano
 * 
 * Esta es la clase base para crear los logo de cada ecosistema que iran en la botonera.
 * 
 */
public abstract class LogoAbstracto {
	/**
	 * Posición x en la que se dibujara el logo
	 */
	private int x;
	
	/**
	 * Posición y en la que se dibujara el logo
	 */
	private int y;
	
	/**
	 * Figura que representa la imagen del logo en si misma. 
	 * Se recomienda el uso de imagenes svg
	 */
	protected PShape logo;
	
	/**
	 * Ecosistema al que representa este logo.
	 */
	protected EcosistemaAbstracto ecosistema;
	
	/**
	 * Crea un nuevo LogoAbstracto.
	 * @param rutaLogo Ruta de la imagen del logo
	 * @param ecosistema Ecosistema al que representa este logo.
	 */
	public LogoAbstracto(String rutaLogo, EcosistemaAbstracto ecosistema){
		logo = Mundo.ObtenerInstancia().getApp().loadShape(rutaLogo);
		this.ecosistema = ecosistema;
	}
	
	/**
	 * Dibuja el logo en pantalla
	 */
	public void dibujar(){
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.shapeMode(PApplet.CENTER);			
		app.shape(logo, x , y, 60, 60);			
		app.shapeMode(PApplet.CORNER);	
	}
	
	/**
	 * Método plantilla en el que se defienen las acciones a realizar
	 * cuando se da clic en este logo
	 */
	public abstract void click();
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PShape getLogo() {
		return logo;
	}

	public void setLogo(PShape logo) {
		this.logo = logo;
	}

	public EcosistemaAbstracto getEcosistema() {
		return ecosistema;
	}

	public void setEcosistema(EcosistemaAbstracto ecosistema) {
		this.ecosistema = ecosistema;
	}

}
