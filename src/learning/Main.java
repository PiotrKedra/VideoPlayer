package learning;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


import java.io.File;
import java.util.Scanner;

public class Main extends Application {
    private MediaPlayer player;
    private MediaView view;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            BorderPane root=new BorderPane();

            File file = new File("C:\\Users\\piotr\\Desktop\\tgt.mp4");
            String MEDIA_URL = file.toURI().toString();
            Media media = new Media(MEDIA_URL);
            player = new MediaPlayer(media);
            view = new MediaView(player);
            root.setCenter(view);

            TextField textField = new TextField();
            Button button = new Button("ok");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    System.out.println(textField.getText());
                    File file = new File(textField.getText());
                    String MEDIA_URL = file.toURI().toString();
                    Media media = new Media(MEDIA_URL);

                    player.stop();
                    player=null;

                    player = new MediaPlayer(media);
                    view = new MediaView(player);
                    player.setAutoPlay(true);
                    root.setCenter(null);
                    root.setCenter(view);


                }
            });

            root.setTop(textField);
            root.setBottom(button);
            player.setAutoPlay(true);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
