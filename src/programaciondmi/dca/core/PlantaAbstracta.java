package programaciondmi.dca.core;

/**
 * 
 * Esta es la clase base de las plantas del ecosistema contempla los metodos necesarios para la construccion de cada una
 * 
 * @author Jamoncada
 *
 */

public abstract class PlantaAbstracta implements Runnable{
	
	protected int x;
	protected int y;	
	
	public PlantaAbstracta(){}
	
	public PlantaAbstracta(int x, int y){
		this.x = x ;
		this.y = y ;
	}
		
	public abstract void dibujar();
	
	/**
	 * Método encargadado de recibir el daño ocasionado por el ataque una especie abstracta
	 * el método deberá validar su posicion respecto a la especie para saber si se lastima o no.
	 * 
	 * @param lastimador quien hace daño a la planta actual
	 * @return true si pudo hacer daño y false si no puedo dañar a la especie actual 
	 */
	public abstract boolean recibirDano(EspecieAbstracta lastimador);

}
