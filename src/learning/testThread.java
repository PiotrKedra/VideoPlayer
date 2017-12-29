package learning;

import Player.VideoPlayer;

public class testThread extends Thread {
    public void run(){
        new VideoPlayer().run();
    }
}
