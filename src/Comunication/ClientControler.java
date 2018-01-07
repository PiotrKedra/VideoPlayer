package Comunication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientControler extends Thread{
    private boolean isWorking = true;
    private boolean playOrPause = true;
    private boolean scrolForward=false;
    private boolean scrolBackward=false;
    private boolean isFullScreen=false;

    private Socket echoSocket;
    private PrintWriter out;
    private BufferedReader in;
    @Override
    public void run() {
        echoSocket = null;
        out = null;
        in = null;

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

    }

    public void setPlayOrPause(){
        out.println("1");
    }

    public void setScrolForward(){
        out.println("2");
    }

    public void setScrolBackward(){
        out.println("3");
    }

    public void setMoviePath(String path){
        out.println(path);
    }
    public void setFullScreen(){
        this.isFullScreen=true;
    }

    public void setExit() throws IOException{
        out.close();
        in.close();
        echoSocket.close();
    }
}
