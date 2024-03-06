package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMeniu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Initializarea aplicatiei, cu o pagina de meniu care ofera utilizatorului mai multe variante. (Start, Exit, Leaderboard)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMeniu frame = new MainMeniu();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructorul care este folosit la crearea interfetei.
     */
    public MainMeniu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel titleLabel = new JLabel("QuizGame");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        titleLabel.setForeground(Color.BLACK); 
        titleLabel.setBounds(170, 30, 120, 30);
        contentPane.add(titleLabel);

        JButton btnStartQuiz = new JButton("Start Quiz");
        btnStartQuiz.setBounds(160, 100, 120, 25);
        contentPane.add(btnStartQuiz);

        JButton btnLeaderboards = new JButton("Leaderboards");
        btnLeaderboards.setBounds(160, 150, 120, 25);
        contentPane.add(btnLeaderboards);
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(160, 200, 120, 25);
        contentPane.add(btnExit);

        btnStartQuiz.addActionListener(e -> {
            openQuizGame();
        });

        btnLeaderboards.addActionListener(e -> {
            showLeaderboards();
        });
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
   
     /**
     * Deschide o instanta a clasei QuizGame pentru inceperea quiz-ului.
     */
    private void openQuizGame() {
        String numeJucator = getInputPlayerName(this);
        if (numeJucator != null) {
            QuizGame quizGame = new QuizGame();
            quizGame.setNumeJucator(numeJucator);
            quizGame.setLocationRelativeTo(null);
            quizGame.setVisible(true);
            dispose();
        }
    }

     /**
     * Deschide o fereastra de dialog pentru a afla numele jucatorului la acel moment.
     */
    private String getInputPlayerName(JFrame frame) {
        String numeJucator = JOptionPane.showInputDialog(frame, "Nume jucator:");
        if (numeJucator == null) {
            return null;
        } else if (numeJucator.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Introduceti un nume valid.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return getInputPlayerName(frame);
        }
        return numeJucator;
    }
     /**
     * Afiseaza primele 3 scoruri din istoricul quiz-urilor.
     */
    private void showLeaderboards() {
        QuizGame quizGame = new QuizGame();
        quizGame.afisareLeaderboards();
    }
}
