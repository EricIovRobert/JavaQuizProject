package quiz;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Set;
import java.util.HashSet;
import org.junit.Test;

public class TestQuiz {
	@Test
	//verifica daca s-a conectat corect si a extras intrebari din baza de date si daca raspunsurile incarcate sunt unice
	public void testBazaDate(){
	QuizGame a = new QuizGame();
	a.incarcaIntrebariDinBazaDeDate();
	assertNotNull(a.raspunsuri);
	
    
	for (Intrebare intrebare : a.getQuiz().getIntrebari()) {
		Set<String> raspunsuriUnice = new HashSet<>();
        String raspunsCorect = intrebare.getRaspuns_corect();
        String raspunsGresit1 = intrebare.getRaspuns_gresit1();
        String raspunsGresit2 = intrebare.getRaspuns_gresit2();
        raspunsuriUnice.add(raspunsCorect);
        raspunsuriUnice.add(raspunsGresit1);
        raspunsuriUnice.add(raspunsGresit2);
        assertEquals(3,raspunsuriUnice.size());
	}
	 

	}
	@Test
	//verifica daca au fost create obiectele de care depinde jocul
	public void testObiecteCreate() {
        QuizGame quizGame = new QuizGame();
        assertNotNull(quizGame.getJucator());
        assertNotNull(quizGame.getQuiz());
    }
	
	

}
