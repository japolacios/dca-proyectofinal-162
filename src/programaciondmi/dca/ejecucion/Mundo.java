package programaciondmi.dca.ejecucion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.reflections.Reflections;

import processing.core.PApplet;
import processing.core.PShape;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.LogoAbstracto;
import programaciondmi.dca.core.PlantaAbstracta;

public class Mundo implements Observer {
	private static Mundo ref;
	private PApplet app;
	private int camX;
	private int camY;
	private Set<Class<? extends EcosistemaAbstracto>> clasesEcosistemas;
	private Set<EcosistemaAbstracto> ecosistemas;
	private List<EspecieAbstracta> especies;
	private List<PlantaAbstracta> plantas;
	private PShape mapa;
	private PShape botones_base;
	private ArrayList<LogoAbstracto> botones;
	
	private Mundo(PApplet app) {
		this.app = app;
		this.camX = app.width / 2;
		this.camY = app.height / 2;
		this.ecosistemas = new HashSet<EcosistemaAbstracto>();
		this.especies = new LinkedList<EspecieAbstracta>();
		this.plantas = new LinkedList<PlantaAbstracta>();
		
		this.mapa = this.app.loadShape("global_data/mapa.svg");
		this.botones_base = this.app.loadShape("global_data/botones.svg");
		
		botones = new ArrayList<LogoAbstracto>();
	
	}

	/**
	 * Este método se encarga de buscar en todos los paquetes de la aplicaci�n
	 * todas las clases que hereden de EcosistemaAbstracto, generar una
	 * instancia de las mismas y almacenar dicha instancia en una colecci�n de
	 * tipo conjunto
	 */
	protected void cargarEcosistemas() {
		Reflections reflections = new Reflections("programaciondmi.dca");
		this.clasesEcosistemas = reflections.getSubTypesOf(EcosistemaAbstracto.class);

		for (Class<? extends EcosistemaAbstracto> clase : clasesEcosistemas) {
			try {
				Constructor<?> cons = clase.getConstructor();
				EcosistemaAbstracto ecosistema = (EcosistemaAbstracto) cons.newInstance();
				ecosistemas.add(ecosistema);
				ecosistema.addObserver(this);
				cargarEspecies(ecosistema);
				cargarPlantas(ecosistema);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	//holi

	/**
	 * Este método se encarga de agregar a la colección global de especies la
	 * población inicial de especies de un ecosistema dado por parámetro
	 * 
	 * @param ecosistema
	 *            al que se le sacará las especies
	 */
	private void cargarEspecies(EcosistemaAbstracto ecosistema) {
		if (ecosistema.getEspecies() != null) {
			especies.addAll(ecosistema.getEspecies());
		}
	}

	/**
	 * Este método se encarga de agregar a la colección global de plantas la
	 * población inicial de plantas de un ecosistema dado por parámetro
	 * 
	 * @param ecosistema
	 *            al que se le sacará las plantas
	 */
	private void cargarPlantas(EcosistemaAbstracto ecosistema) {
		if (ecosistema.getPlantas() != null) {
			plantas.addAll(ecosistema.getPlantas());
		}
	}

	/**
	 * <p>
	 * Este m�todo se encargar� de construir el �nico mundo que puede existir en
	 * la aplicaci�n.
	 * </p>
	 * <p>
	 * Si un Objeto de tipo Mundo ha sido creado previamente, se retorna una
	 * referencia a dicho mundo en lugar de construir uno nuevo
	 * </p>
	 * <p>
	 * Este m�todo es protegido para que s�lo pueda ser usado desde la clase
	 * ejecutable
	 * </p>
	 * 
	 * @param app
	 *            Un Objeto de tipo PApplet sobre el cual el mundo se dibujara.
	 * @return El objeto Mundo sobre el que se crearan nuevos ecosistemas
	 */
	protected static synchronized Mundo construirInstancia(PApplet app) {
		if (ref == null) {
			ref = new Mundo(app);
		}
		return ref;
	}

	/**
	 * <p>
	 * Este m�todo retorna una referencia al objeto Mundo sobre el que se
	 * crearan nuevos ecosistemas.
	 * </p>
	 * <p>
	 * No debe utilizarse sin antes hacer un llamado al m�todo
	 * construirInstancia(PApplet app)
	 * </p>
	 * 
	 * @return Una referencia al objeto Mundo sobre el que se crearan nuevos
	 *         ecosistemas.
	 */
	public static synchronized Mundo ObtenerInstancia() {
		return ref;
	}

	/**
	 * Este método se encarga de dibujar lo que ocurre en el mundo.
	 */
	protected void dibujar() {
		/**
		 * TODO Reemplzar esta línea por el background seleccionado por los
		 * estudiantes
		 */
		app.background(150);
		app.pushMatrix();
		// Moverse en el escenario
		moverCamara();
		
		// dibujar el mapa
		app.shapeMode(PApplet.CENTER);
		app.shape(mapa, 0, 0);
		app.shapeMode(PApplet.CORNER);
		
		// dibujar los ecosistemas
		synchronized (ecosistemas) {
			for (EcosistemaAbstracto ecosistema : ecosistemas) {
				ecosistema.dibujar();
			}
		}
		app.popMatrix();
		
		// dibujar los botones
		app.shapeMode(PApplet.CENTER);
		app.shape(botones_base, app.width/2,app.height-60);
		app.shapeMode(PApplet.CORNER);
		
		dibujarBotones();				

	}

	public synchronized PApplet getApp() {
		return app;
	}

	public synchronized List<EspecieAbstracta> getEspecies() {
		return especies;
	}

	public synchronized List<PlantaAbstracta> getPlantas() {
		return plantas;
	}
	
	public synchronized void agregarBoton(LogoAbstracto boton){
		botones.add(boton);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(" El ecosistema " + arg0 + " envia una notificación");
		if (arg1 instanceof EspecieAbstracta) {
			System.out.println("Se argrego una nueva especie " + arg1);
			EspecieAbstracta nuevaEspecie = (EspecieAbstracta) arg1;
			especies.add(nuevaEspecie);
		}

		if (arg1 instanceof PlantaAbstracta) {
			System.out.println("Se argrego una nueva especie " + arg1);
			PlantaAbstracta nuevaPlanta = (PlantaAbstracta) arg1;
			plantas.add(nuevaPlanta);
		}
		
	}

	private void moverCamara() {
		if (app.mouseX < app.width / 4) {
			camX++;
		}
		if (app.mouseX > 3 * app.width / 4) {
			camX--;
		}
		if (app.mouseY < app.height / 4) {
			camY++;
		}
		if (app.mouseY > 3 * app.height / 4 && app.mouseY < app.height-100) {
			camY--;
		}
		
		app.translate(camX, camY);
		// Indicador del origen del lienzo
		app.fill(255, 0, 0);
		app.ellipse(0, 0 / 2, 1, 1);
	}
	
	private void dibujarBotones(){
		int offSetX = 0;
		for(int i = 0; i < botones.size();i++){
			
			// Definir la posición del Logo
			int posicionX = (app.width/2) + (i%2==0?offSetX:-offSetX);
			int posicionY = app.height-70;
			
			LogoAbstracto logo = botones.get(i);
			logo.setX(posicionX);
			logo.setY(posicionY);
			logo.dibujar();
				
			if(i%2==0){
				offSetX+=60;
			}						
		}
	}

	public void click() {
		for(int i = 0; i < botones.size();i++){
			LogoAbstracto logo = botones.get(i);
			
			if(PApplet.dist(app.mouseX, app.mouseY, logo.getX(), logo.getY()) < 30){
				logo.click();
			}
		}
		
	}
	
}
