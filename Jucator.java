package quiz;

public class Jucator {
	public String nume;
	private int id_jucator;
	private int id_quiz;
	public int scor_quiz;
	
	
	public Jucator(String nume, int id_jucator, int id_quiz) {
		super();
		this.nume = nume;
		this.id_jucator = id_jucator;
		this.id_quiz = id_quiz;
		this.scor_quiz = 0;
	}


	@Override
	public String toString() {
		return "Jucator [nume=" + nume + ", id_jucator=" + id_jucator + ", id_quiz=" + id_quiz + ", scor_quiz="
				+ scor_quiz + "]";
	}
	
	public int afisare_punctaj_final() {
		return this.scor_quiz;
	}
	
	public void incrementPunctaj(int puncte) {
        scor_quiz += puncte;
    }
	public String getNume() {
		return this.nume;
	}
	

}
