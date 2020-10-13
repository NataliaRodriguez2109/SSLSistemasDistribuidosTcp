/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author nata_
 */
public class NotSwitchedOnException extends RuntimeException{

     public NotSwitchedOnException(String mesg) {
        JOptionPane.showMessageDialog(null, mesg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
