import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int lowerLimit = 1;
    private int upperLimit = 100;
    private int randomNumber;
    private int attemptsCount;
    private int maxAttempts = 10;  
    private int userScore;

    private JLabel titleLabel;
    private JLabel promptLabel;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JButton newGameButton;

    public GuessTheNumberGame() {
        setTitle("Guess The Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        titleLabel = new JLabel("Welcome to the Guess The Number Game!");
        promptLabel = new JLabel("Enter your guess between " + lowerLimit + " and " + upperLimit + ": ");
        guessField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts: 0");
        newGameButton = new JButton("New Game");

        add(titleLabel);
        add(promptLabel);
        add(guessField);
        add(submitButton);
        add(resultLabel);
        add(attemptsLabel);
        add(newGameButton);

        randomNumber = generateRandomNumber(lowerLimit, upperLimit);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        startNewGame();
    }

    private int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attemptsCount++;

            if (userGuess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed it right.");
                userScore++;
                submitButton.setEnabled(false);
            } else if (userGuess < randomNumber) {
                resultLabel.setText("Too low. Try again.");
            } else {
                resultLabel.setText("Too high. Try again.");
            }

            attemptsLabel.setText("Attempts: " + attemptsCount);

            if (attemptsCount >= maxAttempts) {
                resultLabel.setText("You lose. Maximum attempts reached.");
                submitButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private void startNewGame() {
        randomNumber = generateRandomNumber(lowerLimit, upperLimit);
        attemptsCount = 0;
        guessField.setText("");
        resultLabel.setText("");
        attemptsLabel.setText("Attempts: 0");
        submitButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGame().setVisible(true);
            }
        });
    }
}
