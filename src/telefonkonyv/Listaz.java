package telefonkonyv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Listaz {
	/**
	 * Végiglép a "kontakt.txt" sorjai között. Amennyiben nem "DELETED" rekortdot talál, létrehoz egy új Névjegy példányt a "-" mentén feltördelt sorból, amit egy ArrayList-be tesz. A listát felhasználva megvizsgálja minden soron követkeõ, Comparatorral rendezett listatagot, hogy az elsõ betûje eltér-e az elõtte lévõtõl. Minden új betûvel kezdett Névjegy vezetékneve elõtt formázva kiírja a betût, esztétikailag rendszerezve a kilistázást.
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
				System.out.println("Üres kontaktlista, adjon hozzá új névjegyet az [1]-es gombbal!\n");
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
			System.err.println("Nincs ilyen névvel hozzáadott adatbázis!\n");
		}
	}
}
