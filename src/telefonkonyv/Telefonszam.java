package telefonkonyv;

public class Telefonszam {
	/**
	 * Visszaadja a megadott stringet integerként.
	 * @param tel
	 * @return
	 */
	public int telToInt(String tel) {
		return Integer.valueOf(tel);
	}
	
	/**
	 * Két stringet átkonvertál integerré a telToInt() metódus segítségével, majd eldönti, az elsõ nagyobb-e a másodiknál.
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
	 * Megvizsgálja, hogy a megadott telefonszám string hossza 11 hosszú-e.
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
	 * Visszaadja a megadott string nagybetûs formáját.
	 * @param c
	 * @return
	 */
	public String karakterNagy(String c) {
		return c.toUpperCase();
	}
}
