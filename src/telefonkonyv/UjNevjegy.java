package telefonkonyv;

import java.io.IOException;
import java.util.Scanner;

public class UjNevjegy {
	/**
	 * L�trehozza a N�vjegy v�ltoz�it. A felhaszn�l�t�l bek�ri az adatokat megk�t�tt form�tumban. A csilalggal megjel�lt �rt�kekre v�r bemenetet, a t�bbi tagn�l " " stringgel helyettes�ti, ha nem adtunk r� semmit. Miut�n minden v�ltoz� �rt�ket kapott, az �j N�vjeggyel megh�v�dik a "textolvas.beleir()" f�ggv�ny.
	 * @throws Exception
	 */
	public void hozzaad() throws Exception {
		Textolvas textolvas = new Textolvas();
		Scanner sc = new Scanner(System.in);
		String Vname = " ",Kname = " ",mobile = " ",email = " ",Bname = " ",work = " ";
		
		System.out.println("\n--Adja meg a k�rt adatokat az �j n�vjegyhez: (*-al jelzettek k�telez� adatok)-- ");
		System.out.println("Teljes n�v (vezet�kn�v* keresztn�v* [benec�v]): ");
		
		String bemenet = sc.nextLine();
		try {
			String[] nev = bemenet.split(" ");
			Vname = nev[0];
			Kname = nev[1];
			if (nev.length == 3) {
				Bname = nev[2];
			}
		} catch (ArrayIndexOutOfBoundsException n) {
			System.err.println("Csillaggal jel�lt elemek megad�sa k�telez�!\n");
			hozzaad();
			return;		//F�ggv�ny �jra h�v�sa, ne kelljen kil�pni a men�be.
		}
		
		System.out.println("\nTelefonsz�mok (mobil* [munkahelyi]): ");
		String[] telszamok = sc.nextLine().split(" ");
		mobile = telszamok[0];
		if (mobile == "") {
			System.err.println("Nem�res telefonsz�m megad�sa k�telez�!\n");
			hozzaad();
			return;		//F�ggv�ny �jra h�v�sa, ne kelljen kil�pni a men�be.
		}
		if (mobile.length() != 11 || vanBetu(mobile)) {
			System.err.println("Nem megfelel� form�tum� mobil telefonsz�m! (Pl.: 01234567891)\n");
			hozzaad();
			return;
		}
		try {
			work = telszamok[1];
			if (work.length() != 11 || vanBetu(work)) {
				System.err.println("Nem megfelel� form�tum� munkahelyi telefonsz�m! (Pl.: 01234567891)\n");
				hozzaad();
				return;
			}
		} catch (Exception e) {
			work = " ";
		}
		
		System.out.println("\nEmail c�m*: ");
		email = sc.nextLine();
		if (!email.contains("@")) {
			System.err.println("Nem megfelel� form�tum� e-mail c�m!\n");
			hozzaad();
			return;
		}
		
		textolvas.beleir(new Nevjegy(Vname,Kname,Bname,mobile,work,email));
		System.out.println("\nSikeresen felvetted "+Kname+"-t a n�vjegyekbe!\n");
	}
	/**
	 * Megh�v�sakor a kapott stringet �tvizsg�lja, hogy minden tagja integer-e.
	 * @param telszam
	 * @return
	 */
	public boolean vanBetu(String telszam) {
	    for (char c : telszam.toCharArray()) {
	        if (!Character.isDigit(c)) {
	        	return true;		//true-t ad vissza, ha tal�l benne nem sz�mot
	        }
	    }
	    return false;
	}
}
