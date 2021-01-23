/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farukarig
 */
public class Login extends Thread {

    public ArrayList<Floor> floors;
    
    public Control control;
    
    Login(ArrayList<Floor> floors, Control control) {
        this.floors = floors;
        this.control = control;
    }

    public void run() {
        while(true){
            this.control.write();
            
            Random random = new Random();
            for(int i=0;i<random.nextInt(10)+1;i++){
                this.floors.get(0).order.add(new Person(this.floors));
            }
            
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
