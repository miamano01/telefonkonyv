package telefonkonyv;

import java.util.Scanner;

public class Menu {
	public int menusor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--V�lasszon men�pontot:--");
		System.out.println("[1] �j n�vjegy hozz�ad�sa.");
		System.out.println("[2] Megl�v� n�vjegy m�dos�t�sa.");
		System.out.println("[3] N�vjegy t�rl�se.");
		System.out.println("[4] N�vjegyek list�z�sa.");
		System.out.println("[5] N�vjegy keres�se.");
		System.out.println("[0] Kil�p�s a programb�l.");
		System.out.print(">> ");
		return sc.nextInt();
	}
}
