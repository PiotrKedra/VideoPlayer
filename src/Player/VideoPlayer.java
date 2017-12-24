package Player;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class VideoPlayer extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        final File file = new File("C:\\Users\\piotr\\Desktop\\sampleVideo.mp4");
        final String MEDIA_URL = file.toURI().toString();
        final Media media = new Media(MEDIA_URL);
        final MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);

        /*DoubleProperty mediaViewWidth = mediaView.fitWidthProperty();
        DoubleProperty mediaViewHeight = mediaView.fitHeightProperty();
        mediaViewWidth.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mediaViewHeight.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));*/
        mediaView.setPreserveRatio(true);
        StackPane root = new StackPane(mediaView);



        Scene scene = new Scene(root,mediaView.getFitWidth(),mediaView.getFitHeight(),Color.BLACK );
        primaryStage.setScene(scene);
//        primaryStage.setScene(new Scene(new Group(new MediaView(player)),400,400));
        primaryStage.show();

        player.play();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
