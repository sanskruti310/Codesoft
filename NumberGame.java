import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class NumberGame {

    private JFrame mainFrame;
    private JLabel headerLabel1, headerLabel2;
    private JLabel statusLabel1, statusLabel2, bottomLabel;
    private JPanel userInputPanel;
    private JButton guessButton;
    private JButton newButton;
    private JButton quitButton;
    private JLabel numLabel;
    private JTextField userText;
    private JLabel attemptsLabel, roundsLabel, scoreLabel;

    private int number, num_guesses;
    private int maxAttempts = 10;
    private int attempts, rounds, score;

    public NumberGame() {
        Random randGen = new Random();
        number = randGen.nextInt(100) + 1;
        num_guesses = 0;
        prepareGUI();
    }

    public static void main(String[] args) {
        NumberGame g = new NumberGame();
        g.start();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Number Guessing Game");
        mainFrame.setSize(800, 500);
        mainFrame.setLayout(new GridLayout(8, 1)); 
        mainFrame.setResizable(false);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel1 = new JLabel("Welcome To The Number Game");
        headerLabel1.setForeground(new Color(102,0,0));
        headerLabel1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        headerLabel2 = new JLabel("Guess a number between 1-100", JLabel.CENTER);
        headerLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        headerLabel2.setForeground(new Color(0,0,153));

        numLabel = new JLabel("Number : ", JLabel.CENTER);
        numLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userText = new JTextField(10);
        guessButton = new JButton("Guess");

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new FlowLayout()); 
        newButton = new JButton("New Game");
        quitButton = new JButton("Quit Game");
        buttonPanel.add(newButton);
        buttonPanel.add(quitButton);

        JPanel labelPanel = new JPanel(); 
        labelPanel.setLayout(new FlowLayout()); 
        attemptsLabel = new JLabel("Attempts: " + attempts);
        roundsLabel = new JLabel("Rounds: " + rounds);
        scoreLabel = new JLabel("Score: " + score);
        labelPanel.add(attemptsLabel);
        labelPanel.add(roundsLabel);
        labelPanel.add(scoreLabel);

        statusLabel1 = new JLabel("Let's see if you can guess my number", JLabel.CENTER);
        statusLabel1.setForeground(Color.blue);
        statusLabel2 = new JLabel("", JLabel.CENTER);

        bottomLabel = new JLabel("", JLabel.CENTER);

        mainFrame.add(headerLabel1);
        mainFrame.add(headerLabel2);

        userInputPanel = new JPanel();
        userInputPanel.setLayout(new FlowLayout()); 
        userInputPanel.add(numLabel);
        userInputPanel.add(userText);
        userInputPanel.add(guessButton);

        mainFrame.add(userInputPanel);
        mainFrame.add(statusLabel1);
        mainFrame.add(statusLabel2);
        mainFrame.add(buttonPanel); 
        mainFrame.add(labelPanel); 
        mainFrame.add(bottomLabel);
        mainFrame.setVisible(true);
    }

    private void start() {
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attempts >= maxAttempts) {
                    statusLabel1.setText("OOPS! Sorry, you've run out of attempts.");
                    statusLabel1.setForeground(Color.red);
                    statusLabel2.setText("The correct number was : " + number);
                    statusLabel2.setForeground(Color.BLUE);
                    guessButton.setEnabled(false);
                    newButton.setEnabled(true);
                    return;
                }

                String str = userText.getText();
                int guess;
                try {
                    guess = Integer.parseInt(str);
                } catch (NumberFormatException err) {
                    statusLabel1.setText("Please enter a number");
                    statusLabel1.setForeground(Color.red);
                    statusLabel2.setText("");
                    return;
                }
                num_guesses++;

                String guessStr = "Guess " + num_guesses + " is " + guess;
                statusLabel1.setText(guessStr);
                statusLabel1.setForeground(Color.BLUE);

                attempts++;
                attemptsLabel.setText("Attempts : " + attempts);

                String feedbackStr;
                if (guess < number) {
                    feedbackStr = "Oops!!! Your guess is too low";
                } else if (guess > number) {
                    feedbackStr = "Oh! Your guess is too high";
                } else {
                    feedbackStr = "Congratulations!! You guessed the correct number.";
                    guessButton.setEnabled(false);
                    newButton.setEnabled(true);
                    score += (maxAttempts - attempts + 1);
                    scoreLabel.setText("Score : " + score);
                }
                statusLabel2.setText(feedbackStr);
                statusLabel2.setForeground(Color.DARK_GRAY);
            }
        });

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rounds > 0) {
                    Random randGen = new Random();
                    number = randGen.nextInt(100) + 1;
                    num_guesses = 0;
                    attempts = 0;
                    statusLabel1.setText("Now enter your guess number and press 'Guess'.");
                    statusLabel2.setText("");
                    guessButton.setEnabled(true);
                } else {
                    statusLabel1.setText("Round " + (rounds + 1) + ": Please guess a number between 1-100");
                }
                rounds++;
                roundsLabel.setText("Rounds : " + rounds);
                newButton.setEnabled(false);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}