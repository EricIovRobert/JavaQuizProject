package quiz;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.util.Collections;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Timer;
/**
 * Clasa QuizGame care instanteaza un obiect pentru jocul de quiz.
 * Aceasta extinde clasa JFrame si reprezinta fereastra aplicatiei.
 * 
 */
public class QuizGame extends JFrame {

    private JPanel contentPane;
    private JLabel lblIntrebarea;
    private JRadioButton radioButtonA;
    private JRadioButton radioButtonB;
    private JRadioButton radioButtonC;
    private ButtonGroup buttonGroup;
    private String numeJucator;
    private Quiz quiz;
    private Jucator jucator;
    private int indexIntrebareCurenta;
    private Timer timer;
    private int secunde;
    ArrayList<String> raspunsuri = new ArrayList<>();
    /**
    * Metoda main pentru a incepe jocul. 

    *
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuizGame frame = new QuizGame();
               
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Seteaza numele jucatorului, fara de care nu poate continua jocul.
     * 
     * @param numeJucator Numele jucatorului.
     */
    public void setNumeJucator(String numeJucator) {
        this.numeJucator = numeJucator;
        secunde = 0;
        timer.start();
        setTitle("Timp: " + secunde + " secunde");
    }
    /**
     * Getter pentru obiect de tipul jucator
     * @return jucator
     */
    public Jucator getJucator() {
        return this.jucator;
    }
    /**
     * Getter pentru obiect de tipul quiz
     * @return quiz
     */
    public Quiz getQuiz() {
        return this.quiz;
    }

    /**
     * Constructorul clasei QuizGame.
     * Inițializeaza componentele jocului si incarca intrebarile din baza de date.
     */
   
    public QuizGame() {
        quiz = new Quiz(new ArrayList<>(), 1);
        jucator = new Jucator("", 1, quiz.getID());
        indexIntrebareCurenta = 0;

        incarcaIntrebariDinBazaDeDate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 654, 353);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificareRaspuns();
                afisareUrmatoareaIntrebare();
            }
        });
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        btnNext.setBounds(279, 252, 89, 23);
        contentPane.setLayout(null);
        contentPane.add(btnNext);

        lblIntrebarea = new JLabel();
        lblIntrebarea.setVerticalAlignment(SwingConstants.TOP);
        lblIntrebarea.setBounds(57, 39, 590, 19);
        contentPane.add(lblIntrebarea);

        radioButtonA = new JRadioButton("A");
        radioButtonB = new JRadioButton("B");
        radioButtonC = new JRadioButton("C");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonA);
        buttonGroup.add(radioButtonB);
        buttonGroup.add(radioButtonC);

        radioButtonA.setBounds(28, 80, 462, 23);
        radioButtonB.setBounds(28, 121, 462, 23);
        radioButtonC.setBounds(28, 159, 462, 23);

        contentPane.add(radioButtonA);
        contentPane.add(radioButtonB);
        contentPane.add(radioButtonC);
        
        afisareUrmatoareaIntrebare();
    }
    /**
     * Actualizeaza cronometrul jocului.
     */
    public void updateTimer() {
        secunde++;
        setTitle("Timp: " + secunde + " secunde");
    }
    /**
     * Incarca intrebarile si raspunsurile din baza de date.
     */
    public void incarcaIntrebariDinBazaDeDate() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.connect();

            String query = "SELECT Intrebare, RaspunsCorect, RaspunsGresit1, RaspunsGresit2 FROM Intrebari ORDER BY RAND() LIMIT 10";
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String intrebare = resultSet.getString("Intrebare");
                String raspunsCorect = resultSet.getString("RaspunsCorect");
                String raspunsGresit1 = resultSet.getString("RaspunsGresit1");
                String raspunsGresit2 = resultSet.getString("RaspunsGresit2");
               
                raspunsuri.clear();//se reseteaza arrayul de intrebare si raspunsuri
                
                raspunsuri.add(raspunsCorect);
                raspunsuri.add(raspunsGresit1);
                raspunsuri.add(raspunsGresit2);

                Intrebare intrebareNoua = new Intrebare(intrebare, 1, raspunsuri.get(0), raspunsuri.get(1), raspunsuri.get(2));
                quiz.getIntrebari().add(intrebareNoua);
                //System.out.println(intrebareNoua.toString());
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.disconnect(connection);
        }
    }

    /**
     * Afiseaza urmatoarea intrebare si raspunsurile acesteia intr-o ordine aleatoare.
     */
    public void afisareUrmatoareaIntrebare() {
        if (indexIntrebareCurenta < quiz.getIntrebari().size()) {
            Intrebare intrebareCurenta = quiz.getIntrebari().get(indexIntrebareCurenta);

          
            ArrayList<String> raspunsuri = new ArrayList<>();
            raspunsuri.add(intrebareCurenta.getRaspuns_corect());
            raspunsuri.add(intrebareCurenta.getRaspuns_gresit1());
            raspunsuri.add(intrebareCurenta.getRaspuns_gresit2());

          
            Collections.shuffle(raspunsuri);

            lblIntrebarea.setText(intrebareCurenta.getIntrebare());
            radioButtonA.setText("A. " + raspunsuri.get(0));
            radioButtonB.setText("B. " + raspunsuri.get(1));
            radioButtonC.setText("C. " + raspunsuri.get(2));
            buttonGroup.clearSelection();
            indexIntrebareCurenta++;
        } else {
            afisareRezultatFinal();
        }
    }
    /**
     * Verifica raspunsul dat de jucator si metoda verificare_raspuns incrementeaza scorul daca raspunsul este corect
     */
    public void verificareRaspuns() {
        Intrebare intrebareCurenta = quiz.getIntrebari().get(indexIntrebareCurenta - 1);
        String raspunsSelectat = getRaspunsSelectat();

        if (raspunsSelectat != null && intrebareCurenta.verificare_raspuns(raspunsSelectat, jucator)) {
            JOptionPane.showMessageDialog(this, "Raspuns corect!");
        } else if (raspunsSelectat == null) {
            JOptionPane.showMessageDialog(this, "Selectează un răspuns înainte de a apăsa Next!");
        } else {
            JOptionPane.showMessageDialog(this, "Raspuns Gresit!");
        }
    }
    /**
     * Returneaza raspunsul selectat de jucator intr-un format corespunzator.
     * 
     * @return Raspunsul selectat de jucator sau null daca niciun raspuns nu este selectat.
     */
    public String getRaspunsSelectat() {
        if (radioButtonA.isSelected()) {
            return radioButtonA.getText().substring(3);  
        } else if (radioButtonB.isSelected()) {
            return radioButtonB.getText().substring(3);  
        } else if (radioButtonC.isSelected()) {
            return radioButtonC.getText().substring(3); 
        }

        return null;
    }
        
    /**
     * Afiseaza clasamentul primilor 3 jucatori.
     */
    public void afisareLeaderboards() {
        try {
            Connection connection = DatabaseConnection.connect();
            String selectQuery = "SELECT nume_jucator, punctaj, timp FROM scoruri ORDER BY punctaj DESC, timp ASC LIMIT 3";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder leaderboardsMessage = new StringBuilder("Top 3 scoruri:\n");

            while (resultSet.next()) {
                String numeJucator = resultSet.getString("nume_jucator");
                int punctaj = resultSet.getInt("punctaj");
                int timp = resultSet.getInt("timp");

                leaderboardsMessage.append(numeJucator).append(": ").append(punctaj).append(" puncte, ").append(timp).append(" secunde").append("\n");
            }

            JOptionPane.showMessageDialog(this, leaderboardsMessage.toString(), "Top 3 scoruri", JOptionPane.INFORMATION_MESSAGE);

            preparedStatement.close();
            DatabaseConnection.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Afiseaza scorul final si salveaza in baza de date.
     */
    public void afisareRezultatFinal() {
        try {
            Connection connection = DatabaseConnection.connect();
            timer.stop();
            String insertQuery = "INSERT INTO scoruri (nume_jucator, punctaj, timp) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, numeJucator);
            preparedStatement.setInt(2, jucator.afisare_punctaj_final());
            preparedStatement.setInt(3, secunde);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            DatabaseConnection.disconnect(connection);
            JOptionPane.showMessageDialog(this, "Punctaj final: " + jucator.afisare_punctaj_final() + "\nTimp: " + secunde + " sec\nScorul tău a fost salvat!");
            //afisareLeaderboards();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
