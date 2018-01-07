package Functionality;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class PlayOrPauseButton extends Button {
    private String stance = "Pause";
    MediaPlayer player;

    public PlayOrPauseButton(final MediaPlayer _player){
        super();
        player = _player;
        setPrefSize(50, 25);
        setMaxSize(50,25);
        setMinSize(50,25);
        setFocusTraversable(false);
        setText(stance);
        setLayoutX(30);
        setLayoutY(0);
        setStyle("-fx-focus-color: transparent;");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStance();
            }
        });
    }

    public void changeStance(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (player != null) {
                    if (stance.equals("Play")) {
                        stance = "Pause";
                        player.play();
                    } else {
                        stance = "Play";
                        player.pause();
                    }
                    setText(stance);
                }
            }
        });
    }


    public void reset(MediaPlayer player){
        this.player = player;
        stance = "Pause";
    }
}
