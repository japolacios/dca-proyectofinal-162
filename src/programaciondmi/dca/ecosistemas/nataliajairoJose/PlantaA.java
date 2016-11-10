package programaciondmi.dca.ecosistemas.nataliajairoJose;

import processing.core.PApplet;
import processing.core.PImage;

public class PlantaA {

	private PApplet app;
	private float x, y;
	private int state;
	private PImage planta;

	public PlantaA(PApplet app, float x, float y) {
		this.app = app;
		this.x = x;
		this.y = y;
		planta = app.loadImage("PlantaA-01.png");
	}

	public void pintar() {
		app.imageMode(app.CENTER);
		switch (state) {
		case 0:
			app.image(planta, x,y, 54, 85);
			break;
		case 1:
			app.fill(0,0,255);
			app.ellipse(x,y,30,30);
			break;
		case 2:
			app.fill(0,255,0);
			app.ellipse(x,y,30,30);
			break;
		case 3:
			app.fill(255,0,0);
			app.ellipse(x,y,30,30);
			break;
		default:
			break;
		}
		app.imageMode(app.CORNER);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public int getState(){
		return state;
	}

	public void setState(int i) {
		state += i;
	}
	
}
