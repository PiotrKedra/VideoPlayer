package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Media media = new Media("C:\\Users\\piotr\\Desktop\\sampleVideo.mp4");
        MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);

        root.getChildrenUnmodifiable().add(view);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275, Color.BLACK));
        primaryStage.show();

        player.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
*/

public class Main extends Application{

    StackPane stack = new StackPane();



    public void start(Stage primaryStage) throws Exception {

        Media media = new
                Media("http://download.oracle.com/otndocs/products/javafx/oow2010- 2.flv");
        MediaPlayer player = new MediaPlayer(media);


        primaryStage.setScene(new Scene(new Group(new MediaView(player)), 540,
                208));
        primaryStage.setTitle("Title");
        primaryStage.show();

        player.play();
    }

    public static void main(String [] args) {
        Application.launch();
    }

}
