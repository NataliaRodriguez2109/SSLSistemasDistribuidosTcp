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
public class PlantSSL implements Serializable{
    private ReactorSSL reactores[];

    public PlantSSL() {
        this.reactores = new ReactorSSL[3];
        reactores[0] = new ReactorSSL();
        reactores[1] = new ReactorSSL();
        reactores[2] = new ReactorSSL();
    }

    public ReactorSSL[] getReactores() {
        return reactores;
    }

    public void setReactores(ReactorSSL[] reactores) {
        this.reactores = reactores;
    }
    
    public ReactorSSL getReactor(int pos){
        return reactores[pos];
    }
}
