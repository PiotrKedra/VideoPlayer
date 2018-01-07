package Functionality;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import learning.Main;

public class FullScreenButton extends Button {
    private Stage stage;
    private BorderPane root;
    private MainMenu menu;

    public FullScreenButton(final Stage stage, final BorderPane root, final MainMenu menu){
        super();
        this.stage=stage;
        this.root=root;
        this.menu=menu;
        setPrefSize(50, 25);
        setMaxSize(50,25);
        setMinSize(50,25);
        setFocusTraversable(false);
        setText("[  ]");
        setLayoutX(30);
        setLayoutY(0);
        setStyle("-fx-focus-color: transparent;");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFullScreen();
            }
        });
    }

    private void setFullScreen(){
        if(!stage.isFullScreen()) {
            root.setRight(null);
        }else {
            root.setRight(menu);
        }
        stage.setFullScreen(!stage.isFullScreen());
    }
}
