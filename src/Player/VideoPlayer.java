package Player;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class VideoPlayer extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        final File file = new File("C:\\Users\\piotr\\Desktop\\sampleVideo.mp4");
        final String MEDIA_URL = file.toURI().toString();
        final Media media = new Media(MEDIA_URL);
        final MediaPlayer player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);

        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        Group root = new Group(mediaView);


        PlayOrPauseButton playOrPauseButton = new PlayOrPauseButton(player);
        ScrolingButton scrolingForwardButton = new ScrolingButton(player, 5000);
        ScrolingButton scrolingBackwardButton = new ScrolingButton(player, -5000);

        root.getChildren().add(playOrPauseButton);
        root.getChildren().add(scrolingForwardButton);
        root.getChildren().add(scrolingBackwardButton);

        Scene scene = new Scene(root,width.doubleValue(),height.doubleValue(),Color.BLACK );
        primaryStage.setScene(scene);
        primaryStage.show();

        player.play();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
