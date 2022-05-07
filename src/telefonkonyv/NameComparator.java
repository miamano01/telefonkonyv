package telefonkonyv;

import java.util.Comparator;
/**
 * Bek�rt n�vjegyeket rendezi Vname (vezet�kn�v) alapj�n.
 * @author HL51UB
 *
 */
public class NameComparator implements Comparator<Nevjegy> {
	public int compare(Nevjegy n1, Nevjegy n2) {
		String s1 = n1.getVname().toUpperCase();
		String s2 = n2.getVname().toUpperCase();
		return s1.compareTo(s2);
	}
}
