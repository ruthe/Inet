package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * 
 */
public class Client {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
   
    /**
     * 1. Establish a connection to the server. We create a Socket object,
     * supplying its constructor with the following two arguments: • the
     * server’s IP address (of type InetAddress); • the appropriate port number
     * for the service. For example:
     * <code>Socket clientSocket = new Socket(ip, port); </code>
     *
     * 2. Set up input and output streams. These are set up in exactly the same
     * way as the server streams were set up (by calling methods getInputStream
     * and getOutputStream).
     *
     * @param ip
     * @param port
     */
    public void accessServer (int port) {

        try {
            //Open socket connection to server with hostname and port
            clientSocket = new Socket("localhost", port);
            //Open the input/output streams from the client socket
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            //ClientHandler c = new ClientHandler(clientSocket, frameC);
            System.out.println("Connected to The Server...");
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + port + "");
            System.exit(1);
        }
    }
    /** **/


    public void Disconnect(String msg) {

        try {
            String closeConnectionMsg = msg;
            //convert to byte array
            byte[] toServer = closeConnectionMsg.getBytes();
            // send message
            out.write(toServer, 0, toServer.length);
            // ensure that message is sent
            out.flush();

            out.close();// close output stream
            in.close();// close input stream
            clientSocket.close();// close connection

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
public static void main (String args[]) throws Exception{
    
    Client c = new Client();
    c.accessServer(1100);
}

}
