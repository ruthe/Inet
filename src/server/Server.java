/*
 * This class has two methods, startserver() and startBankAccounts().
 * startserver() creates a ServerSocket object and waits indefinitely (‘blocks’) for a client to connect.
 * It does this by calling method accept of class ServerSocket, which returns a Socket
 * object when a connection is made. Creates a handler thread and passes references 
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ruth
 */
public class Server {

    private Map<String, Account> accounts;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startserver();
    }

    public void startserver() throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        //Create a ServerSocket object
        try {
            serverSocket = new ServerSocket(1100);

            System.out.println("Bank started listening on port: 1100");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + e);
            System.exit(1);
        }
        //wait for a client connection request
        //Blocks the current thread until a client connects
        //Returns a connected Socket of the accepted connection.
        //Create a handler thread and pass referenser 
        while (listening) {
            Socket clientsocket = serverSocket.accept();
            ServerThread thread = new ServerThread(clientsocket, accounts);
            thread.start();
            System.out.println("starting thread ");
        }
        serverSocket.close();
    }

    private void startBankAccounts() {
        //security securityCodes
        int[] securityCodes = new int[50];
        int i = 0;
        for (int counter = 1; counter < 100; counter = counter + 2) {
            securityCodes[i] = counter;
            i++;
        }
        //create Bank accounts
        accounts = new HashMap<>();
        Account konton[] = {
            new Account("0000", "000", 10000, securityCodes),
            new Account("1111", "111", 10000, securityCodes),
            new Account("2222", "222", 10000, securityCodes),
            new Account("3333", "333", 10000, securityCodes),
            new Account("4444", "444", 10000, securityCodes),
            new Account("5555", "555", 10000, securityCodes)
        };
        //stores bank accounts
        for (Account konto : konton) {
            accounts.put(konto.getName(), konto);
        }

    }
}
