package telefonkonyv;

public class Telefonszam {
	/**
	 * Visszaadja a megadott stringet integerk�nt.
	 * @param tel
	 * @return
	 */
	public int telToInt(String tel) {
		return Integer.valueOf(tel);
	}
	
	/**
	 * K�t stringet �tkonvert�l integerr� a telToInt() met�dus seg�ts�g�vel, majd eld�nti, az els� nagyobb-e a m�sodikn�l.
	 * @param tel1
	 * @param tel2
	 * @return
	 */
	public boolean isGreater(String tel1, String tel2) {
		if (telToInt(tel1) > telToInt(tel2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Megvizsg�lja, hogy a megadott telefonsz�m string hossza 11 hossz�-e.
	 * @param tel
	 * @return
	 */
	public boolean tizenegy(String tel) {
		if (tel.length() == 11) {
			return true;
		}
		return false;
	}
	/**
	 * Visszaadja a megadott string nagybet�s form�j�t.
	 * @param c
	 * @return
	 */
	public String karakterNagy(String c) {
		return c.toUpperCase();
	}
}
