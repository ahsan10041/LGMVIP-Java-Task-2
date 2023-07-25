import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();
    }

    private void initializeButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));

                final int finalRow = row;
                final int finalCol = col;

                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[finalRow][finalCol].getText().equals("")) {
                            buttons[finalRow][finalCol].setText(String.valueOf(currentPlayer));
                            if (checkWin(finalRow, finalCol)) {
                                showWinDialog(currentPlayer + " wins!");
                                resetBoard();
                            } else if (checkDraw()) {
                                showWinDialog("It's a draw!");
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });

                panel.add(buttons[row][col]);
            }
        }

        add(panel);
    }

    private boolean checkWin(int row, int col) {
        String symbol = String.valueOf(currentPlayer);

        // Check row
        if (buttons[row][0].getText().equals(symbol) &&
                buttons[row][1].getText().equals(symbol) &&
                buttons[row][2].getText().equals(symbol)) {
            return true;
        }

        // Check column
        if (buttons[0][col].getText().equals(symbol) &&
                buttons[1][col].getText().equals(symbol) &&
                buttons[2][col].getText().equals(symbol)) {
            return true;
        }

        // Check diagonals
        if (row == col) {
            if (buttons[0][0].getText().equals(symbol) &&
                    buttons[1][1].getText().equals(symbol) &&
                    buttons[2][2].getText().equals(symbol)) {
                return true;
            }
        }

        if (row + col == 2) {
            if (buttons[0][2].getText().equals(symbol) &&
                    buttons[1][1].getText().equals(symbol) &&
                    buttons[2][0].getText().equals(symbol)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
}
