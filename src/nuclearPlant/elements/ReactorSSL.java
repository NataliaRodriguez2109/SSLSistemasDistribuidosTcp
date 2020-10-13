/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.elements;

import Exceptions.AlreadyDischargeException;
import Exceptions.NotSwitchedOnException;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author nata_
 */
public class ReactorSSL implements Serializable{
     private boolean switchedOn;
    private int charge;
    
    private StateSSL state = new StateSSL();

    public ReactorSSL() {
        charge = 0;
        switchedOn = false;
    }

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public void setSwitchOn(boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean turnOn() {
        switchedOn = true;
        return switchedOn;
    }
    
    public boolean turnOff() {
        switchedOn = false;  
        charge = 0;
        return switchedOn;
    }
    
    
    public void repair() {
        state.changeLabel();
    }

    public StateSSL getState() {
        return state;
    }
    
    
    public int chargeReactor(int value) {
        if (switchedOn == true){
                
                charge = charge + value;   
                if(charge > 100){
                    dañar();
                    charge = 0;
                }
        }else {
            //JOptionPane.showMessageDialog(null, "Debe encender el reactor para ingresar un valor", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NotSwitchedOnException("Debe encender el reactor e ingresar un valor");
        }
        return charge;
    }
    
    public void dañar(){
        state.changeLabel();
        //charge = 0;
    }
    
    public int dischargeReactor(int value) {
        if (switchedOn == true) {
                if (charge == 0) {
                //JOptionPane.showMessageDialog(null, "El reactor ya está totalmente descargado", "Error", JOptionPane.ERROR_MESSAGE);
                throw new AlreadyDischargeException("Ya está completamente descargado");
            } else
            {
                if((charge - value) < 0){
                    charge = 0;
                    JOptionPane.showMessageDialog(null, "El valor ingresado es demasiado alto, se descargará a 0%", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    charge = charge - value; 
                }     
            }
        }else {
            //JOptionPane.showMessageDialog(null, "El reactor ya está totalmente descargado", "Error", JOptionPane.ERROR_MESSAGE);
                throw new AlreadyDischargeException("Ya está completamente descargado");            
        }
        return charge;
    }
}
