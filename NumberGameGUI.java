

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI extends JFrame {
    private Random rand = new Random();
    private int numberToGuess;
    private int attempts;
    private int maxAttempts;
    private int score;
    private int rounds;

    private JLabel label;
    private JTextField inputField;
    private JButton guessButton;
    private JButton playAgainButton;

    public NumberGameGUI() {
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 6;
        score = 0;
        rounds = 0;

        setTitle("Number Game");
        setSize(300, 500); 
        getContentPane().setBackground(Color.decode("#F7F7F7")); // Light gray background

        label = new JLabel("Guess a number between 1 and 100:");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.decode("#3F3F3F")); // Dark gray text color

        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.BOLD, 18));
        inputField.setForeground(Color.decode("#3F3F3F")); // Dark gray text color
        inputField.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2), Color.decode("#007FDA"))); // Blue border

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.setBackground(Color.decode("#007FDA")); // Blue button color
        guessButton.setForeground(Color.WHITE);
        guessButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 18));
        playAgainButton.setBackground(Color.decode("#007FDA")); // Blue button color
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#F7F7F7")); // Light gray background

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(inputField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(guessButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(playAgainButton, constraints);

        add(panel, BorderLayout.CENTER); // Add the panel to the center of the frame

        guessButton.addActionListener(new GuessListener());
        playAgainButton.addActionListener(new PlayAgainListener());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userGuess = Integer.parseInt(inputField.getText());
            attempts++;

            if (userGuess == numberToGuess) {
                label.setText("Correct! You guessed it in " + attempts + " attempts.");
                score++;
            } else if (userGuess > numberToGuess) {
                label.setText("Too high! Try again.");
            } else {
                label.setText("Too low! Try again.");
            }

            inputField.setText("");
        }
    }

    private class PlayAgainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberToGuess = rand.nextInt(100) + 1;
            attempts = 0;
            label.setText("Guess a number between 1 and 100:");
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGameGUI();
            }
        });
    }
}
