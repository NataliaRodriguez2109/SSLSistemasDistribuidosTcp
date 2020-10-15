/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.comunications;

import java.net.Socket;
import nuclearPlant.elements.PlantSSL;
import nuclearPlant.elements.ReactorSSL;
import views.AdmConsoleSSL;
import views.PlantaGraphSSL;
import views.ReactorMngSSL;

/**
 *
 * @author nata_
 */
public class MessageProtocolSSL {

    public static void interpretar(PlantSSL planta, MessageSSL message, PlantControlSSL pc, Socket cliente) {
        if (!message.getContenido()[0].equals("adios")) {
            ReactorSSL r = planta.getReactor(Integer.parseInt(message.getContenido()[0]));
            if (message.getContenido()[1].equals("cargar")) {
                r.chargeReactor(Integer.parseInt(message.getContenido()[2]));
            } else if (message.getContenido()[1].equals("descargar")) {
                r.dischargeReactor(Integer.parseInt(message.getContenido()[2]));
            } else if (message.getContenido()[1].equals("encender")) {
                r.turnOn();
            } else if (message.getContenido()[1].equals("apagar")) {
                r.turnOff();
            } else if (message.getContenido()[1].equals("reparar")) {
                r.repair();
            }
        } else {
            pc.removeSocket(cliente);
        }
        String contenido[] = message.getContenido();
        contenido[3] = "update";
        pc.updateAll(new MessageSSL(contenido));
    }

    public static void interpretar(PlantSSL planta, MessageSSL message, AdmConsoleSSL padre) {
        ReactorSSL r = planta.getReactor(Integer.parseInt(message.getContenido()[0]));
        if (message.getContenido()[1].equals("cargar")) {
            r.chargeReactor(Integer.parseInt(message.getContenido()[2]));
        } else if (message.getContenido()[1].equals("descargar")) {
            r.dischargeReactor(Integer.parseInt(message.getContenido()[2]));
        } else if (message.getContenido()[1].equals("encender")) {
            r.turnOn();
        } else if (message.getContenido()[1].equals("apagar")) {
            r.turnOff();
        } else if (message.getContenido()[1].equals("reparar")) {
            r.repair();
        }

        if (padre.getPanel().getClass().getName().contains("Reactor")) {
            ReactorMngSSL rm = (ReactorMngSSL) padre.getPanel();
            rm.setReactor(padre.getAdmm().getPlanta().getReactor(rm.getPos()));
        } else if (padre.getPanel().getClass().getName().contains("Planta")) {
            PlantaGraphSSL pg = (PlantaGraphSSL) padre.getPanel();
            pg.initialize(padre.getAdmm().getPlanta());
        }
    }
}
