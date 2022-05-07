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
	 * V�gigl�p a "kontakt.txt" sorjai k�z�tt. Amennyiben nem "DELETED" rekortdot tal�l, l�trehoz egy �j N�vjegy p�ld�nyt a "-" ment�n felt�rdelt sorb�l, amit egy ArrayList-be tesz. Megh�vja a "keresMenu(sorok)" met�dust.
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
			System.err.println("Nincs ilyen n�vvel hozz�adott adatb�zis!\n");
		}
	}
	
	/**
	 * Bek�r egy kontakt nevet a felhaszn�l�t�l, �gyelve a form�tumra. V�gig j�rja a l�trehozott list�t, majd a tal�lt tagokat egy arraylistbe rendezi, majd kilist�zza �ket. Sorsz�m megad�s�val v�laszthat� a kontakt. Ha nem tal�lt ilyet, meg�ll a met�dus. A kiv�lasztott N�vjegy-gyel megh�v�dik az "torol(talat, valasz)" f�ggv�ny.  
	 */
	public void keresMenu(ArrayList<Nevjegy> sorok) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Nevjegy> talalt = new ArrayList<Nevjegy>();
		System.out.println("--Adja meg a t�r�lni k�v�nt kontakt nev�t:--");
		String[] nev = sc.nextLine().split(" ");
		int counter = 0;
		for (Nevjegy s : sorok) {
			if (s.getVname().equals(nev[0]) && s.getKname().equals(nev[1])) {
					talalt.add(s);
					counter++;
				}
			}
		if (counter == 0) {
			System.out.print("\nNincs ilyen nev� kontakt a list�ban!");
		}
		else if (counter == 1) {
			torol(talalt,1);
		}
		else if (counter > 1) {
			int i = 1;
			System.out.println("\n--T�bb tal�lat! V�lasszon a felsoroltak k�z�l!--");
			for (Nevjegy s : talalt) {
				System.out.println("["+i+"]"+s);
				i++;
			}
			System.out.print(">> ");
			int valasz = sc.nextInt();
			torol(talalt,valasz);
		}
		System.out.println("\nSikeres t�rl�s!\n");
	}
	
	/**
	 * L�trehoz a kiv�lasztott N�vjegy alapj�n a .txt fileban l�v� form�tum szerint egy Stringet. Az eg�sz sort lecser�li az �sszes adatot tartalmaz� sorokEgyben-ben "DELETED-"-re. A .txt file tartalma t�rl�dik, fel�l�rja a sorokEgyben string.
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
