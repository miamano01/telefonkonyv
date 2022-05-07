package telefonkonyv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Listaz {
	/**
	 * V�gigl�p a "kontakt.txt" sorjai k�z�tt. Amennyiben nem "DELETED" rekortdot tal�l, l�trehoz egy �j N�vjegy p�ld�nyt a "-" ment�n felt�rdelt sorb�l, amit egy ArrayList-be tesz. A list�t felhaszn�lva megvizsg�lja minden soron k�vetke�, Comparatorral rendezett listatagot, hogy az els� bet�je elt�r-e az el�tte l�v�t�l. Minden �j bet�vel kezdett N�vjegy vezet�kneve el�tt form�zva ki�rja a bet�t, eszt�tikailag rendszerezve a kilist�z�st.
	 * @throws IOException
	 */
	public void listazas() throws IOException {
		try {
			ArrayList<Nevjegy> nevek = new ArrayList<Nevjegy>();
			FileReader fr = new FileReader("kontakt.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String sor = br.readLine();
				if (sor == null) break;
				if (!sor.split("-")[0].equals("DELETED")) {
					String[] sorTorve = sor.split("-");
					nevek.add(new Nevjegy(sorTorve[0],sorTorve[1],sorTorve[2],sorTorve[3],sorTorve[4],sorTorve[5]));
				}
			}
			br.close();
			Collections.sort(nevek, new NameComparator());

			System.out.println();
			
			if (nevek.size() == 0) {
				System.out.println("�res kontaktlista, adjon hozz� �j n�vjegyet az [1]-es gombbal!\n");
				return;
			}
			
			boolean hasznalt = false;
			char elso = 'A';
			for (Nevjegy n : nevek) {
				if (elso != n.getVname().toUpperCase().charAt(0)) { //itt
					hasznalt = false;
				}
				if (hasznalt == false) {
					System.out.println("------------------------------------------["+n.getVname().toUpperCase().charAt(0)+"]------------------------------------------");
					hasznalt = true;
					elso = n.getVname().toUpperCase().charAt(0);
				}
				System.out.println(n);
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println("Nincs ilyen n�vvel hozz�adott adatb�zis!\n");
		}
	}
}
