package telefonkonyv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Textolvas {
	/**
	 * Paraméterül kapott Névjegy-et kötõjellel elválasztva file-ba írja, és új sort kezd.
	 * @param n
	 * @throws IOException
	 */
	public void beleir(Nevjegy n) throws IOException {
		try {
			FileWriter fw = new FileWriter("kontakt.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(n.getVname()+"-");
			pw.print(n.getKname()+"-");
			pw.print(n.getBname()+"-");
			pw.print(n.getMobile()+"-");
			pw.print(n.getWork()+"-");
			pw.print(n.getEmail()+"-");
			pw.println();
			pw.close();
		}
		catch (IOException e){
			System.err.println("Nincs hozzáadva adatbázis!\n");
		}
	}
}
