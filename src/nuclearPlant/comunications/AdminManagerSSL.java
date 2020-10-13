/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.comunications;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import nuclearPlant.elements.PlantSSL;
import nuclearPlant.tools.HiloEnviarSSL;
import nuclearPlant.tools.HiloRecibirSSL;
import nuclearPlant.tools.IPScannerSSL;
import views.AdmConsoleSSL;

/**
 *
 * @author nata_
 */
public class AdminManagerSSL {
    private SSLSocket socket;
    private ArrayList<String> dirs;
    private int port;
    private AdmConsoleSSL consola;
    private PlantSSL planta;

    public AdminManagerSSL(AdmConsoleSSL consola) {
        System.setProperty("javax.net.ssl.trustStore", "myKeystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "helloworld");
        this.consola = consola;
        this.port = IPScannerSSL.getPort();
        HiloScanner elHilo = new HiloScanner();
        elHilo.start();
    }

    public class HiloScanner extends Thread {

        @Override
        public void run() {
            try {
                new IPScannerSSL();
                IPScannerSSL.checkHosts("25.109", consola.getLista());//////******192.168.1
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };

    

    public void conect(String dirIP) {
        try {
            //socket = new Socket(dirIP, this.port);
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) 
            SSLSocketFactory.getDefault();
            this.socket = (SSLSocket) 
            sslSocketFactory.createSocket(dirIP, this.port);
            socket.setSoTimeout(2000);
            socket.setKeepAlive(true);
            ObjectInputStream perEnt = new ObjectInputStream(socket.getInputStream());
            planta = (PlantSSL) perEnt.readObject();
            HiloRecibirSSL hr = new HiloRecibirSSL(socket, planta, consola);            
            hr.start();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public PlantSSL getPlanta() {
        return planta;
    }

    public void disConect() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Error al desconectar");
        }
    }

    public void emit(MessageSSL me) {
        try {
            HiloEnviarSSL he = new HiloEnviarSSL(socket, me);
            he.run();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getDirs() {
        return this.dirs;
    }

    public int getPort() {
        return this.port;
    }
}
