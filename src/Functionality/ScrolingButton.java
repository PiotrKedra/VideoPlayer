package Functionality;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class ScrolingButton extends Button {
    MediaPlayer player;
    private int millis;

    public ScrolingButton(MediaPlayer _player, final int _millis){
        this.player = _player;
        millis = _millis;

        setFocusTraversable(false);
        if(millis>0) {
            setLayoutX(80);
            setLayoutY(0);
            setText("+5s");
        }
        else {
            setLayoutX(0);
            setLayoutY(0);
            setText("-5s");
        }
        setPrefSize(50, 25);
        setMinSize(50,25);
        //setMaxSize(28,10);
        setStyle("-fx-focus-color: transparent;");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scrol();
            }
        });
    }

    public void scrol(){
        if(player!=null) {
            Duration currentTime = player.getCurrentTime();
            System.out.println(currentTime);
            currentTime = currentTime.add(new Duration(millis));
            System.out.println(currentTime);
            player.seek(currentTime);
        }
    }

    public void reset(MediaPlayer player){
        this.player = player;
    }

}
