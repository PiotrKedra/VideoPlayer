package Controler;

import Functionality.PlayOrPauseButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Controler extends Application {
    private boolean isPlaing = true;
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        Button playOrPause = new Button();
        Button scrolForward = new Button();
        Button scrolBackward = new Button();
        playOrPause.setText("Play/Pause");
        scrolBackward.setText("-5s");
        scrolForward.setText("+5s");

        playOrPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        root.setLeft(scrolBackward);
        root.setCenter(playOrPause);
        root.setRight(scrolForward);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Controller");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}