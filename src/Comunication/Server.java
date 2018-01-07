package Comunication;

import Functionality.PlayOrPauseButton;
import Functionality.ScrolingButton;
import Player.VideoPlayer;
import javafx.concurrent.Task;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private boolean isWorking=true;
    private PlayOrPauseButton playOrPauseButton;
    private ScrolingButton scrolForwardButton;
    private ScrolingButton scrolBackwardButton;
    private VideoPlayer videoPlayer;

    //server
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;

    public Server(PlayOrPauseButton playOrPauseButton, ScrolingButton scrolForwardButton,
                  ScrolingButton scrolBackwardButton, VideoPlayer videoPlayer){
        super();
        this.playOrPauseButton=playOrPauseButton;
        this.scrolBackwardButton=scrolBackwardButton;
        this.scrolForwardButton=scrolForwardButton;
        this.videoPlayer = videoPlayer;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        try {
            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;

            while (isWorking) {
                inputLine = in.readLine();
                if(inputLine.equals("1")){
                    playOrPauseButton.changeStance();
                }
                if(inputLine.equals("2")){
                    scrolForwardButton.scrol();
                }
                if(inputLine.equals("3")){
                    scrolBackwardButton.scrol();
                }
                if(inputLine.equals("4")){
                    videoPlayer.setFullScreen();
                }
                if(inputLine.length()>10){
                    videoPlayer.setPlayer(inputLine);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setExit() throws IOException {
        isWorking=false;
        serverSocket.close();
        if(clientSocket!=null){
            clientSocket.close();
        }
        if(in!=null){
            in.close();
        }
    }



}
