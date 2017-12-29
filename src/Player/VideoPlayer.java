package Player;

import Comunication.Server;
import Functionality.PlayOrPauseButton;
import Functionality.ScrolingButton;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class VideoPlayer extends Application {

    private int mouseCliks = 0;
    private long lastTimeClik = 0;
    private long currentTime = 0;
    private boolean fullScreen=false;
    private boolean isPlaing=true;

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

        player.setAutoPlay(true);

        BorderPane root = new BorderPane(mediaView);




        PlayOrPauseButton playOrPauseButton = new PlayOrPauseButton(player);
        ScrolingButton scrolingForwardButton = new ScrolingButton(player, 5000);
        ScrolingButton scrolingBackwardButton = new ScrolingButton(player, -5000);

        /*root.getChildren().add(playOrPauseButton);
        root.getChildren().add(scrolingForwardButton);
        root.getChildren().add(scrolingBackwardButton);*/

        Scene scene = new Scene(root,width.doubleValue(),height.doubleValue());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.SPACE){
                    System.out.println("space");
                    playOrPauseButton.changeStance();
                }
                if(event.getCode()==KeyCode.RIGHT){
                    System.out.println("right");
                    scrolingForwardButton.scrol();
                }else if(event.getCode()==KeyCode.LEFT){
                    scrolingBackwardButton.scrol();
                    System.out.println("left");
                }
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(System.currentTimeMillis());
                if((System.currentTimeMillis()-lastTimeClik)<=500) {
                    playOrPauseButton.changeStance();
                    mouseCliks = 0;
                    System.out.println("we in home");
                    fullScreen = !fullScreen;
                    primaryStage.setFullScreen(fullScreen);
                }else {
                    playOrPauseButton.changeStance();
                    System.out.println("one clikc");
                }
                lastTimeClik=System.currentTimeMillis();

            }
        });
        primaryStage.setTitle(file.getName());
        primaryStage.setScene(scene);
        primaryStage.show();

        new Server(playOrPauseButton).start();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void run(){
        Application.launch();
    }

}
