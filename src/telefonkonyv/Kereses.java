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
	 * V�gigl�p a "kontakt.txt" sorjai k�z�tt. Amennyiben nem "DELETED" rekortdot tal�l, l�trehoz egy �j N�vjegy p�ld�nyt a "-" ment�n felt�rdelt sorb�l, amit egy ArrayList-be tesz. Megh�vja a "vanBenne(sorok)" met�dust.
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
			System.err.println("Nincs ilyen n�vvel hozz�adott adatb�zis!\n");
		} 
		catch (TooBigNumberException t) {
			System.err.println("Nincs ilyen sz�mmal felsorolt kontakt!\n");
		}
	}
	
	/**
	 * Bek�r egy kontakt nevet a felhaszn�l�t�l, �gyelve a form�tumra. V�gig j�rja a l�trehozott list�t, majd a tal�lt tagokat egy hashmapba rendezi hozz�rendelt ID szerint, majd kilist�zza �ket. Sorsz�m megad�s�val v�laszthat� a kontakt. Ha nem tal�lt ilyet, meg�ll a met�dus. A kiv�lasztott N�vjegy-gyel megh�v�dik az "adatok(talat, valasz)" f�ggv�ny.  
	 */
	@Override
	public void vaneBenne(ArrayList<Nevjegy> sorok) throws NoTextFileException, TooBigNumberException {
		Scanner sc = new Scanner(System.in);
		Map<Integer,Nevjegy> talalt = new HashMap<Integer,Nevjegy>();
		
		System.out.println("\n--Adja meg a kontakt nev�t:--");
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
			System.out.print("\nNincs ilyen nev� kontakt a list�ban!");
		}
		else if (counter == 1) {
			adatok(talalt,1);
		}
		else if (counter > 1) {
			System.out.println("\n--T�bb tal�lat! V�lasszon a felsoroltak k�z�l!--");
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
	 * A param�ter�l kapott HashMap-en lek�ri a kiv�lasztott N�vjegy-et, majd form�zva ki�rja minden tagj�t.
	 * @param talalt
	 * @param valasz
	 */
	public void adatok(Map<Integer, Nevjegy> talalt, int valasz) {
		Nevjegy jo = talalt.get(valasz);
		int szamlalo = 0;
		System.out.println("\n--"+jo.getVname()+" "+jo.getKname()+" adatai:--");
		System.out.println("[Vezet�kn�v] "+jo.getVname());
		System.out.println("[Keresztn�v] "+jo.getKname());
		System.out.println("[Becen�v] "+jo.getBname());
		System.out.println("[Mobil telefonsz�m] "+jo.getMobile());
		System.out.println("[Munkahelyi telefonsz�m] "+jo.getWork());
		System.out.println("[E-mail c�m] "+jo.getEmail());
		System.out.println("\n");
	}	
}
