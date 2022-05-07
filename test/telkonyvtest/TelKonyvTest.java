package telkonyvtest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import telefonkonyv.Listaz;
import telefonkonyv.Menu;
import telefonkonyv.Nevjegy;
import telefonkonyv.Telefonszam;
import telefonkonyv.UjNevjegy;

public class TelKonyvTest {
	Nevjegy n, n2;
	Menu m;
	Listaz l;
	UjNevjegy u;
	Telefonszam t;
	
	@Before
	public void setUp() throws Exception {
		n = new Nevjegy("Teszt","Teszt","Teszt","01234567891","19876543210","tesztmail@mail.hu");
		n2 = new Nevjegy("Teszt","Teszt","Teszt","01234678910"," ","tesztmail@mail.hu");
		m = new Menu();
		l = new Listaz();
		u = new UjNevjegy();
		t = new Telefonszam();
	}

	@Test
	public void testtoString() {
		String s1 = ("Teszt Teszt (Teszt)\t|Tel: 01234567891 / 19876543210 |E-mail: tesztmail@mail.hu");
		String s2 = n.toString();
		assertEquals("",s1,s2);
	}
	
	@Test
	public void testMenu() {
		System.out.println("!! Írjon 3-ast a teszteléshez !!\n");
		int menuRe = m.menusor();
		int harom = 3;
		assertEquals("",harom,menuRe);
	}
	
	//@Test(expected=IOException.class)
	//public void testListazas() throws IOException {
	//	l.listazas("nincsilyen.txt");
	//}
	
	@Test
	public void testVanBetu() {
		boolean t1 = u.vanBetu("124424r4242");
		assertTrue("",t1);
	}
	
	@Test
	public void testTelToInt() {
		int szam = 645673;
		String szamStr = "645673";
		int joszam = t.telToInt(szamStr);
		assertEquals("",szam,joszam);
	}
	
	@Test
	public void testIsGreater() {
		boolean great = t.isGreater(n2.getMobile(),n.getMobile());
		assertTrue("",great);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testIsGreaterWork() {
		boolean greatwork = t.isGreater(n.getWork(),n2.getWork());
		assertFalse("",greatwork);
	}

	@Test
	public void testTizenegy() {
		boolean tizenegy_e = t.tizenegy("1212");
		assertFalse("",tizenegy_e);
	}
	
	@Test
	public void testKarakterNagy() {
		String s1 = t.karakterNagy("teszt");
		String s2 = "TESZT";
		assertEquals("",s1,s2);
		
	}
}