package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;

public class Logo extends LogoAbstracto {
	
	

	public Logo(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		// Transformar la instacia de mi ecosistema
		Ecosistema eco = (Ecosistema) ecosistema;
		System.out.println("Hizo click el ecosistema"+eco);
		
		/*	Cada grupo debe decidir que acción realizar cuando se click en su logo */
	}

}
