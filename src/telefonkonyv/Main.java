package telefonkonyv;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	/**
	 * P�ld�nyos�tja az oszt�lyokat, v�gtelen ciklust kezd. A megh�vott menusor() f�ggv�nyb�l kapott integer v�laszja ki a men�pontok k�z�l azt a met�dust, amelyik h�v�s�val lesz v�grehajtva a men�pontban le�rt parancs.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		UjNevjegy ujNevjegy = new UjNevjegy();
		Modosit modosit = new Modosit();
		Torol torol = new Torol();
		Listaz listaz = new Listaz();
		Kereses kereses = new Kereses();
		Menu menu = new Menu();
		
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				int menupont = menu.menusor();
				if (menupont > 5 || menupont < 0) {
					System.err.println("Nincs ezzel a sz�mmal men�pont!\n");
				}
				else if (menupont == 1) {
					ujNevjegy.hozzaad();
				}
				else if (menupont == 2) {
					modosit.atir();
				}
				else if (menupont == 3) {
					torol.olvas();
				}
				else if (menupont == 4) {
					listaz.listazas();
				}
				else if (menupont == 5) {
					kereses.keres();
				}
				else if (menupont == 0) {
					System.out.println("Telefonk�nyv - HL51UB");
					System.exit(0);
				}
			}
		}
		catch (Exception e) {
			System.err.println("Hib�s bemenet! Haszn�ljon a men�pontokban elhelyezett sz�mok k�z�l!\n");
			main(null);
		}
	}
}
