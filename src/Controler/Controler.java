package Controler;

import Comunication.ClientControler;
import Functionality.PlayOrPauseButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class Controler extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //starting the thread with client
        ClientControler clientControler=new ClientControler();
        clientControler.start();

        VBox root = new VBox();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 40, 15, 40 ));
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #336699;");


        //setting up the buttons
        Button playOrPause = new Button();
        Button scrolForward = new Button();
        Button scrolBackward = new Button();
        playOrPause.setText("Play/Pause");
        scrolBackward.setText("-5sec");
        scrolForward.setText("+5sec");

        playOrPause.setPrefSize(100,20);
        scrolBackward.setPrefSize(100,20);
        scrolForward.setPrefSize(100,20);

        playOrPause.setFocusTraversable(false);
        scrolBackward.setFocusTraversable(false);
        scrolForward.setFocusTraversable(false);

        playOrPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientControler.setPlayOrPause();
            }
        });
        scrolBackward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientControler.setScrolBackward();
            }
        });
        scrolForward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientControler.setScrolForward();
            }
        });

        //Setting up GUI
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 40, 15, 40 ));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");
        final TextField moviePath = new TextField();
        moviePath.setPromptText("Give your movie path");
        moviePath.setPrefColumnCount(20);
        Button sendPath = new Button("Ok");
        sendPath.setFocusTraversable(false);

        sendPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientControler.setMoviePath(moviePath.getText());
            }
        });

        hBox.getChildren().addAll(moviePath,sendPath);


        vBox.getChildren().addAll(playOrPause,scrolBackward,scrolForward);

        root.getChildren().addAll(vBox,hBox);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Controller");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    clientControler.setExit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
