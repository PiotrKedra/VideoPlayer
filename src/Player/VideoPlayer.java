package Player;

import Comunication.Server;
import Functionality.FullScreenButton;
import Functionality.MainMenu;
import Functionality.PlayOrPauseButton;
import Functionality.ScrolingButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;


public class VideoPlayer extends Application {

    private int mouseCliks = 0;
    private long lastTimeClik = 0;
    private long currentTime = 0;
    private boolean fullScreen=false;
    private boolean isPlaing=true;
    private Server server;
    private boolean isWorking = true;

    private Stage stage;
    private Scene scene;
    private DoubleProperty width;
    private DoubleProperty height;

    private BorderPane root;
    private MainMenu menu;

    public PlayOrPauseButton playOrPauseButton;
    ScrolingButton scrolingForwardButton;
    ScrolingButton scrolingBackwardButton;

    //player
    private File file;
    private String MEDIA_URL;
    private Media media;
    private MediaPlayer player;
    private MediaView mediaView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;

        width = new SimpleDoubleProperty();
        height=new SimpleDoubleProperty();

        /*file = new File("C:\\Users\\piotr\\Desktop\\tgt.mp4");
        MEDIA_URL = file.toURI().toString();
        media = new Media(MEDIA_URL);
        player = new MediaPlayer(media);
        mediaView = new MediaView(player);
        player.setAutoPlay(true);*/

        root = new BorderPane();
        root.setStyle("-fx-background-color: #000000;");

        playOrPauseButton = new PlayOrPauseButton(player);
        scrolingForwardButton = new ScrolingButton(player, 5000);
        scrolingBackwardButton = new ScrolingButton(player, -5000);
        FullScreenButton fullScreenButton=new FullScreenButton(stage,root,menu);

        menu = new MainMenu();
        menu.getChildren().addAll(scrolingBackwardButton,playOrPauseButton,scrolingForwardButton,fullScreenButton);
        root.setRight(menu);

        //setting up scene event handler ...
        scene = new Scene(root,width.doubleValue(),height.doubleValue());
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
                if((System.currentTimeMillis()-lastTimeClik)<=500) {
                    playOrPauseButton.changeStance();
                    if(!primaryStage.isFullScreen()) {
                        root.setRight(null);
                    }else {
                        root.setRight(menu);
                    }
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                }else {
                    playOrPauseButton.changeStance();
                    System.out.println("one clikc");
                }
                lastTimeClik=System.currentTimeMillis();

            }
        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    server.setExit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(300);
        primaryStage.setScene(scene);
        primaryStage.show();

        //running background server
        server = new Server(playOrPauseButton,scrolingForwardButton,scrolingBackwardButton,this);
        new Thread(server).start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void run(){
        Application.launch();
    }

    public void setFullScreen(){
        stage.setFullScreen(!stage.isFullScreen());
    }

    public void setPlayer(String path){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(path);
                file = new File(path);
                MEDIA_URL = file.toURI().toString();
                media = new Media(MEDIA_URL);
                player = new MediaPlayer(media);
                playOrPauseButton.reset(player);
                scrolingBackwardButton.reset(player);
                scrolingForwardButton.reset(player);
                player.setAutoPlay(true);
                mediaView = new MediaView(player);

                //resaizing properly media view
                width = mediaView.fitWidthProperty();
                height = mediaView.fitHeightProperty();
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

                root.setCenter(mediaView);
            }
        });

    }
}
