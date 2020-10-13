/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuclearPlant.elements;

import java.io.Serializable;

/**
 *
 * @author nata_
 */
public class StateSSL implements Serializable{
    String label;

    public StateSSL() {
        label = "Funcional";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    
    
    public String changeLabel() {
        if(label.equals("Funcional")){
            label = "Dañado";
        }else 
        {
            if(label.equals("Dañado")){
                label = "Funcional";
            }
        }
        
        return label;
    }  
}
