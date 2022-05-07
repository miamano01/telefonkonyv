package telefonkonyv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Kereses extends Talal {
	/**
	 * Végiglép a "kontakt.txt" sorjai között. Amennyiben nem "DELETED" rekortdot talál, létrehoz egy új Névjegy példányt a "-" mentén feltördelt sorból, amit egy ArrayList-be tesz. Meghívja a "vanBenne(sorok)" metódust.
	 * @throws IOException
	 */
	public void keres() throws IOException {
		try {
			ArrayList<Nevjegy> sorok = new ArrayList<Nevjegy>();
			FileReader fr = new FileReader("kontakt.txt");			
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String sor = br.readLine();
				if (sor == null) break;
				if (!sor.split("-")[0].equals("DELETED")) {
					String[] sorTorve = sor.split("-");
					sorok.add(new Nevjegy(sorTorve[0],sorTorve[1],sorTorve[2],sorTorve[3],sorTorve[4],sorTorve[5]));
				}
			}
			br.close();
			vaneBenne(sorok);
		}
		catch (NoTextFileException n) {
			System.err.println("Nincs ilyen névvel hozzáadott adatbázis!\n");
		} 
		catch (TooBigNumberException t) {
			System.err.println("Nincs ilyen számmal felsorolt kontakt!\n");
		}
	}
	
	/**
	 * Bekér egy kontakt nevet a felhasználótól, ügyelve a formátumra. Végig járja a létrehozott listát, majd a talált tagokat egy hashmapba rendezi hozzárendelt ID szerint, majd kilistázza õket. Sorszám megadásával választható a kontakt. Ha nem talált ilyet, megáll a metódus. A kiválasztott Névjegy-gyel meghívódik az "adatok(talat, valasz)" függvény.  
	 */
	@Override
	public void vaneBenne(ArrayList<Nevjegy> sorok) throws NoTextFileException, TooBigNumberException {
		Scanner sc = new Scanner(System.in);
		Map<Integer,Nevjegy> talalt = new HashMap<Integer,Nevjegy>();
		
		System.out.println("\n--Adja meg a kontakt nevét:--");
		String[] nev = sc.nextLine().split(" ");
		int counter = 0;
		for (Nevjegy s : sorok) {
			if (s.getVname().equals(nev[0]) && s.getKname().equals(nev[1])) {
					talalt.put(counter+1, s);
					counter++;
				}
			}
		
		Set set = talalt.entrySet();
		Iterator iter = set.iterator();
		
		if (counter == 0) {
			System.out.print("\nNincs ilyen nevû kontakt a listában!");
		}
		else if (counter == 1) {
			adatok(talalt,1);
		}
		else if (counter > 1) {
			System.out.println("\n--Több találat! Válasszon a felsoroltak közül!--");
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry)iter.next();
				System.out.println("["+entry.getKey()+"] "+entry.getValue()); 
			}
			
			System.out.print(">> ");
			int valasz = sc.nextInt();
			if (valasz > talalt.size() || valasz < 1) {
				throw new TooBigNumberException("");
			}
			adatok(talalt,valasz);
		}
		System.out.println("\n");
	}
	
	/**
	 * A paraméterül kapott HashMap-en lekéri a kiválasztott Névjegy-et, majd formázva kiírja minden tagját.
	 * @param talalt
	 * @param valasz
	 */
	public void adatok(Map<Integer, Nevjegy> talalt, int valasz) {
		Nevjegy jo = talalt.get(valasz);
		int szamlalo = 0;
		System.out.println("\n--"+jo.getVname()+" "+jo.getKname()+" adatai:--");
		System.out.println("[Vezetéknév] "+jo.getVname());
		System.out.println("[Keresztnév] "+jo.getKname());
		System.out.println("[Becenév] "+jo.getBname());
		System.out.println("[Mobil telefonszám] "+jo.getMobile());
		System.out.println("[Munkahelyi telefonszám] "+jo.getWork());
		System.out.println("[E-mail cím] "+jo.getEmail());
		System.out.println("\n");
	}	
}
