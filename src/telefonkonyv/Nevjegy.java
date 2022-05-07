package telefonkonyv;

public class Nevjegy {
	private String Vname;
	private String Kname;
	private String Bname;
	private String mobile;
	private String work;
	private String email;
	
	public Nevjegy(String Vname, String Kname, String Bname, String mobile, String work, String email) {
		this.Vname = Vname;
		this.Kname = Kname;
		this.Bname = Bname;
		this.mobile = mobile;
		this.work = work;
		this.email = email;
	}
	
	public String getVname() {
		return Vname;
	}
	
	public String getKname() {
		return Kname;
	}
	
	public String getBname() {
		return Bname;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getWork() {
		return work;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		if (Bname.equals(" ") && work.equals(" ")) {
			return Vname+" "+Kname+" \t\t|Tel: "+mobile+"\t\t|E-mail: "+email;
		}
		else if (work.equals(" ")) {
			return Vname+" "+Kname+" ("+Bname+")\t|Tel: "+mobile+"\t\t|E-mail: "+email;
		}
		else if (Bname.equals(" ")) {
			return Vname+" "+Kname+" \t\t|Tel: "+mobile+" / "+work+" |E-mail: "+email;
		}
		return Vname+" "+Kname+" ("+Bname+")\t|Tel: "+mobile+" / "+work+" |E-mail: "+email;
	}

	public void setVname(String vname) {
		Vname = vname;
	}

	public void setKname(String kname) {
		Kname = kname;
	}

	public void setBname(String bname) {
		Bname = bname;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
