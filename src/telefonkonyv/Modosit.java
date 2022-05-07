package telefonkonyv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Modosit extends Talal {
	static String sorokEgybe = "";
	/**
	 * Végiglép a "kontakt.txt" sorjai között. Amennyiben nem "DELETED" rekortdot talál, létrehoz egy új Névjegy példányt a "-" mentén feltördelt sorból, amit egy ArrayList-be tesz. A kész listával meghívásra kerül a modositMenu() metódus.
	 * @throws IOException
	 * @throws BadFormatException
	 */
	public void atir() throws IOException, BadFormatException {
		sorokEgybe = "";
		try {
			ArrayList<Nevjegy> sorok = new ArrayList<Nevjegy>();
			FileReader fr = new FileReader("kontakt.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String sor = br.readLine();
				if (sor == null) break;
				if (!sor.split("-")[0].equals("DELETED")) {
					sorokEgybe += (sor+"\n");
					String[] sorTorve = sor.split("-");
					sorok.add(new Nevjegy(sorTorve[0],sorTorve[1],sorTorve[2],sorTorve[3],sorTorve[4],sorTorve[5]));
				}
			}
			br.close();
			modositMenu(sorok);
		} 
		catch (NoTextFileException n) {
			System.err.println("Nincs ilyen névvel hozzáadott adatbázis!\n");
		} 
		catch (TooBigNumberException t) {
			System.err.println(t.getMessage());
		}
	}
	/**
	 * Bekér egy nevet, majd végigjárja a listát egyezések után kutatva. Ha talál, elhelyezi egy HashMap-be a talált Névjegy-eket, hozzá rendelt sorszámmal. Ha többet talált, sorszám megadásával választható ki a kívánt Névjegy, ami paraméterül szolgál a modositas() metódusban.
	 */
	@Override
	public void modositMenu(ArrayList<Nevjegy> sorok) throws NoTextFileException, TooBigNumberException, IOException {
		Scanner sc = new Scanner(System.in);
		Map<Integer,Nevjegy> talalt = new HashMap<Integer,Nevjegy>();
		System.out.println("--Adja meg a módosítani kívánt kontakt nevét:--");
		String[] nev = sc.nextLine().split(" ");
		int counter = 0;
		for (Nevjegy s : sorok) {
			if (s.getVname().equals(nev[0]) && s.getKname().equals(nev[1])) {
					talalt.put(counter, s);
					counter++;
				}
			}
		
		Set set = talalt.entrySet();
		Iterator iter = set.iterator();
		
		if (counter == 0) {
			System.out.print("\nNincs ilyen nevû kontakt a listában!");
		}
		else if (counter == 1) {
			try {
				modositas(talalt,1);
			} catch (BadFormatException b) {
				System.err.println(b.getMessage());
			}
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
				throw new TooBigNumberException("Nincs ilyen számmal ellátott névjegy!\n");
			}
			try {
				modositas(talalt,valasz);
			} catch (BadFormatException b) {
				System.err.println(b.getMessage());
			}
		}
		System.out.println("\n");
	}
	/**
	 * Kiiratja a "talalt" ArrayList-bõl a kiválasztott Névjegy adatait, mindegyikhez egy számot rendel. Inputban bekéri a módosítandó adat számát, majd bekéri az új adattagot. Ha megfelel a kikötéseknek az adat, felülírja a sorokEgybe stringben azt a sort a kijavított, megformázott adatsorral. A .txt file tartalma törlõdik, helyére az új sorokEgybe string kerül.
	 * @param talalt
	 * @param valasz
	 * @throws IOException
	 * @throws TooBigNumberException
	 * @throws BadFormatException
	 */
	public void modositas(Map<Integer, Nevjegy> talalt, int valasz) throws IOException, TooBigNumberException, BadFormatException {
		Scanner sc = new Scanner(System.in);
		UjNevjegy hiba = new UjNevjegy();
		Nevjegy jo = talalt.get(valasz-1);
		int szamlalo = 0;
		System.out.println("\n--Válassza ki a módosítani kívánt tagot!--");
		System.out.println("[1] "+jo.getVname());
		System.out.println("[2] "+jo.getKname());
		System.out.println("[3] "+jo.getBname());
		System.out.println("[4] "+jo.getMobile());
		System.out.println("[5] "+jo.getWork());
		System.out.println("[6] "+jo.getEmail());
		System.out.print(">> ");
		int szam = sc.nextInt();
		if (szam > 6 || szam < 1) {
			throw new TooBigNumberException("Nincs ilyen számmal ellátott adat!\n");
		}
		
		System.out.println("\n--Adja meg az új adatot:--");
		System.out.print(">> ");
		String uj = sc.next();
		
		String regiSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
		String ujSor;
		
		if (szam == 1) {
			ujSor = (uj+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 2) {
			ujSor = (jo.getVname()+"-"+uj+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 3) {
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+uj+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 4) {
			if (uj.length() != 11 || hiba.vanBetu(uj)) {
				throw new BadFormatException("Nem megfelelõ formátum!");
			}
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+uj+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 5) {
			if (uj.length() != 11 || hiba.vanBetu(uj)) {
				throw new BadFormatException("Nem megfelelõ formátum!");
			}
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+uj+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 6) {
			if (!uj.contains("@")) {
				throw new BadFormatException("Nem megfelelõ formátum!");
			}
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+jo.getWork()+"-"+uj+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		
		FileWriter fw = new FileWriter("kontakt.txt", false);
		PrintWriter pw = new PrintWriter(fw);
		pw.flush();
		pw.print(sorokEgybe);
		pw.close();
	}
}
