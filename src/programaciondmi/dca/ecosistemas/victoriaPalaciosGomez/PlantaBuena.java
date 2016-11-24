package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta{
	
	protected int x, y, index;
	protected PApplet app;
	protected PImage[] plantaB = new PImage [4];
	protected ArrayList<PlantaBuena>buenas;
	
	
	public PlantaBuena(PApplet ecosistema, float x, float y){
		super(ecosistema);
		this.app = Mundo.ObtenerInstancia().getApp();
		this.x= (int)app.random(2000);
		this.y=(int)app.random(2000);
		images();
		
	}
	
	public void dibujar(){
		app.image(plantaB[index], x, y);	
	}
	
	private void images(){
		plantaB[0] = app.loadImage("../data/pBuena-01.png");
		plantaB[1] = app.loadImage("../data/pBuena-02.png");
		plantaB[2] = app.loadImage("../data/pBuena-03.png");
		plantaB[3] = app.loadImage("../data/pBuena-04.png");
	}

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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}
  
	
}
