/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.tools;

import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author nata_
 */
public class IPScannerSSL {
    private static int port = 32645;

    public static void checkHosts(final String subnet, JList lista) {
        DefaultListModel<String> model = new DefaultListModel<>();
        lista.setModel(model);        
        final int timeout = 1000;
        for (int j = 200; j < 254; j++) {
            for (int i = 200; i < 255; ++i) {
                final String ip = subnet + "." + i+"."+j;
                if (portIsOpen(ip, IPScannerSSL.port, 200)) {
                    System.out.println("The port " + IPScannerSSL.port + " host " + ip + " is ON (probed with a timeout of " + 200 + "ms)");
                    model.add(model.getSize(), ip);
                }
            }
        }
    }

    

    public static boolean portIsOpen(final String ip, final int port, final int timeout) {
        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static int getPort() {
        return IPScannerSSL.port;
    }
}
