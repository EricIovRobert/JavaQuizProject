package quiz;
import java.util.ArrayList;
import java.util.Scanner;
public class MainQuiz {

	public static void main(String[] args) {
		Intrebare a = new Intrebare("Intrebare1",1,"Raspuns1","Gresit1","Gresit2");
		Intrebare a1 = new Intrebare("Intrebare2",2,"Raspuns2","Gresit3","Gresit4");
		Intrebare a2 = new Intrebare("Intrebare3",3,"Raspuns3","Gresit5","Gresit6");
		
		ArrayList<Intrebare> intrebariQuiz = new ArrayList<>();
        intrebariQuiz.add(a);
        intrebariQuiz.add(a1);
        intrebariQuiz.add(a2);

        Quiz quiz = new Quiz(intrebariQuiz,1);
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Introduceti numele jucatorului: ");
        String numeJucator = scanner.nextLine();
        Jucator jucator = new Jucator(numeJucator,1,quiz.getID());
        
        for(Intrebare it : quiz.getIntrebari()) {
        	System.out.println(it.toString());
            System.out.print("Raspunsul tau: ");
            String raspuns = scanner.nextLine();
            it.verificare_raspuns(raspuns, jucator);
                
            
        	
        }
        System.out.println("Punctaj obtinut de " + jucator.getNume() + ": " + jucator.afisare_punctaj_final());

		

	}

}
