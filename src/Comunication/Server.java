package Comunication;

import Functionality.PlayOrPauseButton;
import Functionality.ScrolingButton;
import javafx.scene.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    PlayOrPauseButton playOrPauseButton;
    ScrolingButton scrolForwardButton;
    ScrolingButton scrolBackwardButton;

    public Server(PlayOrPauseButton playOrPauseButton, ScrolingButton scrolForwardButton, ScrolingButton scrolBackwardButton){
        super();
        this.playOrPauseButton=playOrPauseButton;
        this.scrolBackwardButton=scrolBackwardButton;
        this.scrolForwardButton=scrolForwardButton;
    }
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println(inputLine);
                if(inputLine.equals("1")){
                    playOrPauseButton.changeStance();
                }
                if(inputLine.equals("2")){
                    scrolForwardButton.scrol();
                }
                if(inputLine.equals("3")){
                    scrolBackwardButton.scrol();
                }
            }
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
