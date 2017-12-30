package Comunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientControler extends Thread{
    private boolean isWorking = true;
    private boolean playOrPause = false;
    private boolean scrolForward=false;
    private boolean scrolBackward=false;
    @Override
    public void run() {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 6666);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Dont know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;

        try {
            while (isWorking) {
                sleep(500);
                if(playOrPause) {
                    out.println("1");
                    playOrPause=false;
                }else if(scrolForward){
                    out.println("2");
                    scrolForward=false;
                }else if(scrolBackward){
                    out.println("3");
                    scrolBackward=false;
                }
            }

            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPlayOrPause(){
        this.playOrPause=true;
    }

    public void setScrolForward(){
        this.scrolForward=true;
    }

    public void setScrolBackward(){
        this.scrolBackward=true;
    }
}
