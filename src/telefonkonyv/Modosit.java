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
	 * V�gigl�p a "kontakt.txt" sorjai k�z�tt. Amennyiben nem "DELETED" rekortdot tal�l, l�trehoz egy �j N�vjegy p�ld�nyt a "-" ment�n felt�rdelt sorb�l, amit egy ArrayList-be tesz. A k�sz list�val megh�v�sra ker�l a modositMenu() met�dus.
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
			System.err.println("Nincs ilyen n�vvel hozz�adott adatb�zis!\n");
		} 
		catch (TooBigNumberException t) {
			System.err.println(t.getMessage());
		}
	}
	/**
	 * Bek�r egy nevet, majd v�gigj�rja a list�t egyez�sek ut�n kutatva. Ha tal�l, elhelyezi egy HashMap-be a tal�lt N�vjegy-eket, hozz� rendelt sorsz�mmal. Ha t�bbet tal�lt, sorsz�m megad�s�val v�laszthat� ki a k�v�nt N�vjegy, ami param�ter�l szolg�l a modositas() met�dusban.
	 */
	@Override
	public void modositMenu(ArrayList<Nevjegy> sorok) throws NoTextFileException, TooBigNumberException, IOException {
		Scanner sc = new Scanner(System.in);
		Map<Integer,Nevjegy> talalt = new HashMap<Integer,Nevjegy>();
		System.out.println("--Adja meg a m�dos�tani k�v�nt kontakt nev�t:--");
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
			System.out.print("\nNincs ilyen nev� kontakt a list�ban!");
		}
		else if (counter == 1) {
			try {
				modositas(talalt,1);
			} catch (BadFormatException b) {
				System.err.println(b.getMessage());
			}
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
				throw new TooBigNumberException("Nincs ilyen sz�mmal ell�tott n�vjegy!\n");
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
	 * Kiiratja a "talalt" ArrayList-b�l a kiv�lasztott N�vjegy adatait, mindegyikhez egy sz�mot rendel. Inputban bek�ri a m�dos�tand� adat sz�m�t, majd bek�ri az �j adattagot. Ha megfelel a kik�t�seknek az adat, fel�l�rja a sorokEgybe stringben azt a sort a kijav�tott, megform�zott adatsorral. A .txt file tartalma t�rl�dik, hely�re az �j sorokEgybe string ker�l.
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
		System.out.println("\n--V�lassza ki a m�dos�tani k�v�nt tagot!--");
		System.out.println("[1] "+jo.getVname());
		System.out.println("[2] "+jo.getKname());
		System.out.println("[3] "+jo.getBname());
		System.out.println("[4] "+jo.getMobile());
		System.out.println("[5] "+jo.getWork());
		System.out.println("[6] "+jo.getEmail());
		System.out.print(">> ");
		int szam = sc.nextInt();
		if (szam > 6 || szam < 1) {
			throw new TooBigNumberException("Nincs ilyen sz�mmal ell�tott adat!\n");
		}
		
		System.out.println("\n--Adja meg az �j adatot:--");
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
				throw new BadFormatException("Nem megfelel� form�tum!");
			}
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+uj+"-"+jo.getWork()+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 5) {
			if (uj.length() != 11 || hiba.vanBetu(uj)) {
				throw new BadFormatException("Nem megfelel� form�tum!");
			}
			ujSor = (jo.getVname()+"-"+jo.getKname()+"-"+jo.getBname()+"-"+jo.getMobile()+"-"+uj+"-"+jo.getEmail()+"-");
			sorokEgybe = sorokEgybe.replaceAll(regiSor, ujSor);
		}
		else if (szam == 6) {
			if (!uj.contains("@")) {
				throw new BadFormatException("Nem megfelel� form�tum!");
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
