package telefonkonyv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Torol {
	static String sorokEgyben = "";
	/**
	 * Végiglép a "kontakt.txt" sorjai között. Amennyiben nem "DELETED" rekortdot talál, létrehoz egy új Névjegy példányt a "-" mentén feltördelt sorból, amit egy ArrayList-be tesz. Meghívja a "keresMenu(sorok)" metódust.
	 * @throws IOException
	 */
	public void olvas() throws IOException {
		sorokEgyben = "";
		try {
			ArrayList<Nevjegy> sorok = new ArrayList<Nevjegy>();
			FileReader fr = new FileReader("kontakt.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String sor = br.readLine();
				if (sor == null) break;
				if (!sor.split("-")[0].equals("DELETED")) {
					sorokEgyben += (sor+"\n");
					String[] sorTorve = sor.split("-");
					sorok.add(new Nevjegy(sorTorve[0],sorTorve[1],sorTorve[2],sorTorve[3],sorTorve[4],sorTorve[5]));
				}
			}
			keresMenu(sorok);
		} catch (Exception e) {
			System.err.println("Nincs ilyen névvel hozzáadott adatbázis!\n");
		}
	}
	
	/**
	 * Bekér egy kontakt nevet a felhasználótól, ügyelve a formátumra. Végig járja a létrehozott listát, majd a talált tagokat egy arraylistbe rendezi, majd kilistázza õket. Sorszám megadásával választható a kontakt. Ha nem talált ilyet, megáll a metódus. A kiválasztott Névjegy-gyel meghívódik az "torol(talat, valasz)" függvény.  
	 */
	public void keresMenu(ArrayList<Nevjegy> sorok) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Nevjegy> talalt = new ArrayList<Nevjegy>();
		System.out.println("--Adja meg a törölni kívánt kontakt nevét:--");
		String[] nev = sc.nextLine().split(" ");
		int counter = 0;
		for (Nevjegy s : sorok) {
			if (s.getVname().equals(nev[0]) && s.getKname().equals(nev[1])) {
					talalt.add(s);
					counter++;
				}
			}
		if (counter == 0) {
			System.out.print("\nNincs ilyen nevû kontakt a listában!");
		}
		else if (counter == 1) {
			torol(talalt,1);
		}
		else if (counter > 1) {
			int i = 1;
			System.out.println("\n--Több találat! Válasszon a felsoroltak közül!--");
			for (Nevjegy s : talalt) {
				System.out.println("["+i+"]"+s);
				i++;
			}
			System.out.print(">> ");
			int valasz = sc.nextInt();
			torol(talalt,valasz);
		}
		System.out.println("\nSikeres törlés!\n");
	}
	
	/**
	 * Létrehoz a kiválasztott Névjegy alapján a .txt fileban lévõ formátum szerint egy Stringet. Az egész sort lecseréli az összes adatot tartalmazó sorokEgyben-ben "DELETED-"-re. A .txt file tartalma törlõdik, felülírja a sorokEgyben string.
	 * @param talalt
	 * @param valasz
	 * @throws IOException
	 */
	public void torol(ArrayList<Nevjegy> talalt, int valasz) throws IOException {
		Scanner sc = new Scanner(System.in);
		Nevjegy jo = talalt.get(valasz-1);
		String regiSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
		String ujSor = "DELETED-";
		sorokEgyben = sorokEgyben.replaceAll(regiSor, ujSor);
		FileWriter fw = new FileWriter("kontakt.txt", false);
		PrintWriter pw = new PrintWriter(fw);
		pw.flush();
		pw.print(sorokEgyben);
		pw.close();		
	}
}
