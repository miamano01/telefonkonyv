package telefonkonyv;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	/**
	 * Példányosítja az osztályokat, végtelen ciklust kezd. A meghívott menusor() függvénybõl kapott integer válaszja ki a menüpontok közül azt a metódust, amelyik hívásával lesz végrehajtva a menüpontban leírt parancs.
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
					System.err.println("Nincs ezzel a számmal menüpont!\n");
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
					System.out.println("Telefonkönyv - HL51UB");
					System.exit(0);
				}
			}
		}
		catch (Exception e) {
			System.err.println("Hibás bemenet! Használjon a menüpontokban elhelyezett számok közül!\n");
			main(null);
		}
	}
}
