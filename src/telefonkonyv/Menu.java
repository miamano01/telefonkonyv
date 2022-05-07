package telefonkonyv;

import java.util.Scanner;

public class Menu {
	public int menusor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--Válasszon menüpontot:--");
		System.out.println("[1] Új névjegy hozzáadása.");
		System.out.println("[2] Meglévõ névjegy módosítása.");
		System.out.println("[3] Névjegy törlése.");
		System.out.println("[4] Névjegyek listázása.");
		System.out.println("[5] Névjegy keresése.");
		System.out.println("[0] Kilépés a programból.");
		System.out.print(">> ");
		return sc.nextInt();
	}
}
