package ticTacToe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TicTacToe {

    private final String playerX = "X";
    private final String playerO = "O";
    private int count;
    private Label label;
    private Button[][] buttons;

    public TicTacToe() {
        count = 0;
        label = new Label("Turn: X");
        buttons = new Button[3][3];
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[j][i] = createButton();
                layout.add(buttons[j][i], j, i);
                click(buttons[j][i]);
            }
        }

        return layout;
    }

    public Button createButton() {
        Button btn = new Button(" ");
        btn.setFont(Font.font("Monospaced", 40));
        return btn;
    }

    public void click(Button button) {
        button.setOnMouseClicked((event -> {
            if (button.getText().equals(" ")) {
                String currentPlayer = (count == 0) ? playerX : playerO;
                button.setText(currentPlayer);

                if (checkHorizontalWins(currentPlayer) == 1 || checkVerticalWins(currentPlayer) == 1 || checkDiagonalWins(currentPlayer) == 1) {
                    label.setText(currentPlayer + " Wins!");
//                    label.setText("The end!");
                    disableAllButtons();

                } else if (isBoardFull()) {
                    label.setText("The end!");
                    disableAllButtons();
                } else {
                    count = 1 - count;
                    label.setText("Turn: " + ((count == 0) ? playerX : playerO));
                }
            }
        }));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[j][i].setDisable(true);
            }
        }
    }

    public Label getGameStatus() {
        label.setFont(Font.font("Arial", 40));
        return label;
    }

    public int checkHorizontalWins(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(player) &&
                    buttons[1][i].getText().equals(player) &&
                    buttons[2][i].getText().equals(player)) {
                return 1;
            }
        }
        return 0;
    }

    private int checkVerticalWins(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) &&
                    buttons[i][1].getText().equals(player) &&
                    buttons[i][2].getText().equals(player)) {
                return 1;
            }
        }
        return 0;
    }

    private int checkDiagonalWins(String player) {
        int firstDiagonalCount = 0;
        int secondDiagonalCount = 0;

        for (int i = 0; i < 3; i++) {
            if (buttons[i][i].getText().equals(player)) {
                firstDiagonalCount++;
                if (firstDiagonalCount == 3) {
                    return 1;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (buttons[i][3 - 1 - i].getText().equals(player)) {
                secondDiagonalCount++;
                if (secondDiagonalCount == 3) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int checkWins(String mark) {
        return checkHorizontalWins(mark) | checkVerticalWins(mark) | checkDiagonalWins(mark);
    }

}
