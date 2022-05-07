package telefonkonyv;

import java.io.IOException;
import java.util.Scanner;

public class UjNevjegy {
	/**
	 * Létrehozza a Névjegy változóit. A felhasználótól bekéri az adatokat megkötött formátumban. A csilalggal megjelölt értékekre vár bemenetet, a többi tagnál " " stringgel helyettesíti, ha nem adtunk rá semmit. Miután minden változó értéket kapott, az új Névjeggyel meghívódik a "textolvas.beleir()" függvény.
	 * @throws Exception
	 */
	public void hozzaad() throws Exception {
		Textolvas textolvas = new Textolvas();
		Scanner sc = new Scanner(System.in);
		String Vname = " ",Kname = " ",mobile = " ",email = " ",Bname = " ",work = " ";
		
		System.out.println("\n--Adja meg a kért adatokat az új névjegyhez: (*-al jelzettek kötelezõ adatok)-- ");
		System.out.println("Teljes név (vezetéknév* keresztnév* [benecév]): ");
		
		String bemenet = sc.nextLine();
		try {
			String[] nev = bemenet.split(" ");
			Vname = nev[0];
			Kname = nev[1];
			if (nev.length == 3) {
				Bname = nev[2];
			}
		} catch (ArrayIndexOutOfBoundsException n) {
			System.err.println("Csillaggal jelölt elemek megadása kötelezõ!\n");
			hozzaad();
			return;		//Függvény újra hívása, ne kelljen kilépni a menübe.
		}
		
		System.out.println("\nTelefonszámok (mobil* [munkahelyi]): ");
		String[] telszamok = sc.nextLine().split(" ");
		mobile = telszamok[0];
		if (mobile == "") {
			System.err.println("Nemüres telefonszám megadása kötelezõ!\n");
			hozzaad();
			return;		//Függvény újra hívása, ne kelljen kilépni a menübe.
		}
		if (mobile.length() != 11 || vanBetu(mobile)) {
			System.err.println("Nem megfelelõ formátumú mobil telefonszám! (Pl.: 01234567891)\n");
			hozzaad();
			return;
		}
		try {
			work = telszamok[1];
			if (work.length() != 11 || vanBetu(work)) {
				System.err.println("Nem megfelelõ formátumú munkahelyi telefonszám! (Pl.: 01234567891)\n");
				hozzaad();
				return;
			}
		} catch (Exception e) {
			work = " ";
		}
		
		System.out.println("\nEmail cím*: ");
		email = sc.nextLine();
		if (!email.contains("@")) {
			System.err.println("Nem megfelelõ formátumú e-mail cím!\n");
			hozzaad();
			return;
		}
		
		textolvas.beleir(new Nevjegy(Vname,Kname,Bname,mobile,work,email));
		System.out.println("\nSikeresen felvetted "+Kname+"-t a névjegyekbe!\n");
	}
	/**
	 * Meghívásakor a kapott stringet átvizsgálja, hogy minden tagja integer-e.
	 * @param telszam
	 * @return
	 */
	public boolean vanBetu(String telszam) {
	    for (char c : telszam.toCharArray()) {
	        if (!Character.isDigit(c)) {
	        	return true;		//true-t ad vissza, ha talál benne nem számot
	        }
	    }
	    return false;
	}
}
