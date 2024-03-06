package quiz;

public class Intrebare {
	public String intrebare;
	private int id_intrebare;
	private String raspuns_corect;
	private String raspuns_gresit1;
	private String raspuns_gresit2;
	public String getIntrebare() {
		return intrebare;
	}
	public void setIntrebare(String intrebare) {
		this.intrebare = intrebare;
	}
	public int getId_intrebare() {
		return id_intrebare;
	}
	public void setId_intrebare(int id_intrebare) {
		this.id_intrebare = id_intrebare;
	}
	public String getRaspuns_corect() {
		return raspuns_corect;
	}
	public void setRaspuns_corect(String raspuns_corect) {
		this.raspuns_corect = raspuns_corect;
	}
	public String getRaspuns_gresit1() {
		return raspuns_gresit1;
	}
	public void setRaspuns_gresit1(String raspuns_gresit1) {
		this.raspuns_gresit1 = raspuns_gresit1;
	}
	public String getRaspuns_gresit2() {
		return raspuns_gresit2;
	}
	public void setRaspuns_gresit2(String raspuns_gresit2) {
		this.raspuns_gresit2 = raspuns_gresit2;
	}
	public Intrebare(String intrebare, int id_intrebare, String raspuns_corect, String raspuns_gresit1,
			String raspuns_gresit2) {
		super();
		this.intrebare = intrebare;
		this.id_intrebare = id_intrebare;
		this.raspuns_corect = raspuns_corect;
		this.raspuns_gresit1 = raspuns_gresit1;
		this.raspuns_gresit2 = raspuns_gresit2;
	}
	public String toString() {
		return "Intrebare: intrebare=" + intrebare + ", raspuns1="
				+ raspuns_corect + ", raspuns2=" + raspuns_gresit1 + ", raspuns3=" + raspuns_gresit2;
	}
	public boolean verificare_raspuns(String a, Jucator jucator) {
        boolean isCorrect = a.equalsIgnoreCase(this.raspuns_corect);
        if (isCorrect) {
            jucator.incrementPunctaj(1);
        }
        return isCorrect;
    }
	
	
	
	

}
