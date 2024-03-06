package quiz;
import java.util.ArrayList;
import java.util.List;
public class Quiz {
	public ArrayList<Intrebare> intrebari;
	private int punctaj;
	private int id;
	public ArrayList<Intrebare> getIntrebari() {
		return intrebari;
	}
	public void setIntrebari(ArrayList<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}
	public int afisarePunctaj() {
		return punctaj;
	}
	
	@Override
	public String toString() {
		return "Quiz: intrebari=" + intrebari + ", punctaj=" + punctaj;
		
	}
	public Quiz(ArrayList<Intrebare> intrebari,int k) {
		super();
		this.intrebari = intrebari;
		this.id = k;
		this.punctaj = 0;
	}
	
	
	
	public int getID() {
		return this.id;
	}
	
	

}
