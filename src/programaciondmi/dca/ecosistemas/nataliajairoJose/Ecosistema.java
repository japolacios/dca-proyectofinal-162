package programaciondmi.dca.ecosistemas.nataliajairoJose;
import java.util.ArrayList;
import processing.core.PApplet;

public class Ecosistema {
	PApplet app;
	private Coconita coco;
	private int state, contador = 0;
	private boolean isClose = false;
	private ArrayList<PlantaA> misPlantas = new ArrayList<PlantaA>();

	public Ecosistema(PApplet app) {
		this.app = app;
		coco = (new Coconita(app, app.width / 2, app.height / 2));
		for (int i = 0; i < misPlantas.size(); i++) {
			misPlantas.add(new PlantaA(app, app.width / 2, app.height / 2));
		}
		//holi
	}

	public void pintar() {
		System.out.println("isClose: "+isClose+" "+"cont: "+contador);
		coco.run();
		if (isClose) {
			if (app.frameCount % 60 == 0) {
				contador++;
				if (contador > 3) {
					contador = 0;
				}
			}
		} else {
			contador = 0;
		}

		for (int i = 0; i < misPlantas.size(); i++) {
			PlantaA planta = misPlantas.get(i);
			planta.pintar();

			if (PApplet.dist(coco.getX(), coco.getY(), planta.getX(), planta.getY()) < 50) {
				isClose = true;
				if (contador > 3) {
					planta.setState(1);
				}
			} else {
				isClose = false;
			}

		}
	}

	public void key() {
//personajeee:3
	}

	public void click() {
		float mx = app.mouseX;
		float my = app.mouseY;
		if (mx < app.width && mx > 0 && my < app.height && my > 0) {
			misPlantas.add(new PlantaA(app, mx, my));
		}
	}
}
