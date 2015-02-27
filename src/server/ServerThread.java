/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author ruth
 */
class ServerThread extends Thread {

    private Socket clientsocket;
    private BufferedReader in;
    PrintWriter out;

    public ServerThread(Socket clientsocket,Map<String, Account> accounts) {
        //super("ServerThread");
        this.clientsocket = clientsocket;
    }

    private String readLine() throws IOException {
        String str = in.readLine();
        //System.out.println(""  + socket + " : " + str);
        return str;
    }

    private boolean validateUser() {
        return true;
    }

    public void run() {

        try {
            out = new PrintWriter(clientsocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

            String inputLine, outputLine;

            int balance = 1000;
            int value;
            validateUser();
            out.println("Welcome to Bank! (1)Balance, (2)Withdrawal, (3)Deposit, (4)Exit");
            inputLine = readLine();
            int choise = Integer.parseInt(inputLine);
            while (choise != 4) {
                int deposit = 1;
                switch (choise) {
                    case 2:
                        deposit = -1;
                    case 3:
                        out.println("Enter amount: ");
                        inputLine = readLine();
                        value = Integer.parseInt(inputLine);
                        balance += deposit * value;
                    case 1:
                        out.println("Current balance is " + balance + " dollars");
                        out.println("(1)Balance, (2)Withdrawal, (3)Deposit, (4)Exit");
                        inputLine = readLine();
                        choise = Integer.parseInt(inputLine);
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
            }
            out.println("Good Bye");
            out.close();
            in.close();
            clientsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
