package ticTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        TicTacToe ticTacToe = new TicTacToe();
        BorderPane layout = new BorderPane();

        Label turn = ticTacToe.getGameStatus();

        layout.setTop(turn);
        layout.setCenter(ticTacToe.getView());

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.show();
    }

}
