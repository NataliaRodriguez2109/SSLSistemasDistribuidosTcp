/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.tools;

import java.io.ObjectInputStream;
import java.net.Socket;
import nuclearPlant.comunications.MessageProtocolSSL;
import nuclearPlant.comunications.MessageSSL;
import nuclearPlant.comunications.PlantControlSSL;
import nuclearPlant.elements.PlantSSL;
import views.AdmConsoleSSL;

/**
 *
 * @author nata_
 */
public class HiloRecibirSSL extends Thread {

    private Socket cliente;
    private PlantSSL planta;
    private MessageSSL men;
    private AdmConsoleSSL adm;
    private PlantControlSSL control;

    public HiloRecibirSSL(Socket cliente, PlantSSL planta, PlantControlSSL control) {
        this.cliente = cliente;
        this.planta = planta;
        this.control = control;
    }

    public HiloRecibirSSL(Socket cliente, PlantSSL planta, AdmConsoleSSL adm) {
        this.cliente = cliente;
        this.planta = planta;
        this.adm = adm;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream obj = new ObjectInputStream(cliente.getInputStream());
                men = (MessageSSL) obj.readObject();
                System.out.println(men.getContenido()[0]);
                if (control == null) {
                    MessageProtocolSSL.interpretar(planta, men, adm);
                } else {
                    MessageProtocolSSL.interpretar(planta, men, control);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }

    public Socket getCliente() {
        return cliente;
    }

    public PlantSSL getPlanta() {
        return planta;
    }

    public MessageSSL getMen() {
        return men;
    }
}
