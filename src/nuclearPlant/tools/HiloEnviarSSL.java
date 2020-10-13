/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.tools;

import java.io.ObjectOutputStream;
import java.net.Socket;
import nuclearPlant.comunications.MessageSSL;

/**
 *
 * @author nata_
 */
public class HiloEnviarSSL extends Thread{
    private Socket cliente;
    private MessageSSL men;

    public HiloEnviarSSL(Socket cliente, MessageSSL men) {
        this.cliente = cliente;
        this.men = men;
    }

    @Override
    public void run() {
        try {            
            ObjectOutputStream obj = new ObjectOutputStream(cliente.getOutputStream());
            obj.writeObject(men);
            stop();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "hola");
        }
    }

    public Socket getCliente() {
        return cliente;
    }
};
