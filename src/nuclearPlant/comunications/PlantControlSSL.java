/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.comunications;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import nuclearPlant.elements.PlantSSL;
import nuclearPlant.tools.HiloEnviarSSL;
import nuclearPlant.tools.HiloRecibirSSL;

/**
 *
 * @author nata_
 */
public class PlantControlSSL {
    private SSLServerSocket servidor;
    private PlantSSL planta;
    private ArrayList<Socket> hrs;

    public PlantControlSSL() {
        this.servidor = null;
        hrs = new ArrayList<>();
        this.planta = new PlantSSL(); //32645
        try {
            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) 
            SSLServerSocketFactory.getDefault();
            this.servidor = (SSLServerSocket) 
            sslServerSocketFactory.createServerSocket(32645); 
        } catch (Exception ex2) {
            System.out.println(ex2.getMessage());
        }
    }

    public ArrayList<Socket> getHrs() {
        return hrs;
    }
    
    

    public void iniciarServidor() {
        while (true) {
            try {                
                final   SSLSocket cliente = (SSLSocket)this.servidor.accept();                
                ObjectOutputStream obj = new ObjectOutputStream(cliente.getOutputStream());
                obj.writeObject(planta);                   
                HiloRecibirSSL hr = new HiloRecibirSSL(cliente, this.planta, this);
                hr.start();
                hrs.add(cliente);             
                System.out.println(hrs.size());
            } catch (Exception e) {
                System.out.println(e.toString());

            }            
        }
    }          
    
    public void updateAll(MessageSSL men){
        for (int i = 0; i < hrs.size(); i++) {
            emit(hrs.get(i), men);
        }
    }
    
    public void emit(Socket cliente, MessageSSL men){
        try {
            HiloEnviarSSL he = new HiloEnviarSSL(cliente, men);  
            he.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(final String[] arguments) throws IOException {
        System.setProperty("javax.net.ssl.keyStore","myKeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword","helloworld");
        new PlantControlSSL().iniciarServidor();
    }
}
