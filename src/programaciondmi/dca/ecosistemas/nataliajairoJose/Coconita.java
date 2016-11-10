package programaciondmi.dca.ecosistemas.nataliajairoJose;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Coconita extends Thread {
	private PApplet app;
	private PVector location, aceleration, velocity;
	private float topspeed;
	private PImage[] coconitaF = new PImage[7];
	private int index = 0;

	public Coconita(PApplet app, float x, float y) {
		this.app = app;
		location = new PVector(x, y);
		velocity = new PVector(0, 0);
		aceleration = new PVector(0, 0);
		topspeed = 2;
		imagenes();
	}

	@Override
	public void run() {
		update();
		display();
	}

	public void update() {
		PVector zona = new PVector(app.random(app.width), app.random(app.height));
		PVector acceleration = PVector.sub(zona, location);
		acceleration.setMag(0.2f);
		velocity.add(acceleration);
		velocity.limit(topspeed);
		location.add(velocity);
		if (app.frameCount % 6 == 0) {
			index++;
			if (index > 6) {
				index = 0;
			}
		}
	}

	public void display() {
		app.pushMatrix();
		app.translate(location.x, location.y);
		app.image(coconitaF[index],0,0,68,100);
		app.popMatrix();
	}

	public void imagenes() {
		coconitaF[0] = app.loadImage("presetacion personajes-08.png");
		coconitaF[1] = app.loadImage("presetacion personajes-09.png");
		coconitaF[2] = app.loadImage("presetacion personajes-10.png");
		coconitaF[3] = app.loadImage("presetacion personajes-11.png");
		coconitaF[4] = app.loadImage("presetacion personajes-12.png");
		coconitaF[5] = app.loadImage("presetacion personajes-13.png");
		coconitaF[6] = app.loadImage("presetacion personajes-14.png");
	}

	public float getX() {
		return location.x;
	}

	public float getY() {
		return location.y;
	}

}
