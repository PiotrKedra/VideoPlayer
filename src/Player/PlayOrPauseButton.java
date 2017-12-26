package Player;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class PlayOrPauseButton extends Button {
    private String stance = "Play";
    MediaPlayer player;

    PlayOrPauseButton(final MediaPlayer _player){
        super();
        player = _player;
        setText(stance);
        setLayoutX(30);
        setLayoutY(0);
        setMinSize(50,10);
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStance();
            }
        });
    }

    private void changeStance(){
        if(stance.equals("Play")){
            stance = "Pause";
            player.pause();
        }else{
            stance = "Play";
            player.play();
        }
        setText(stance);
        System.out.println(stance);
    }
}
