/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farukarig
 */
public class Exit extends Thread {

    public ArrayList<Floor> floors;
    
    public Control control;
    
    Exit(ArrayList<Floor> floors,Control control) {
        this.floors = floors;
        this.control = control;
    }
    
    public void run() {
        while(true){
            
            Random random = new Random();
            for(int i=0;i<random.nextInt(5) + 1;i++){
                Floor randomFloor = this.floors.get(random.nextInt(4) + 1);
                if(randomFloor.stay.size()!=0){
                    Person randomPerson =randomFloor.stay.get(random.nextInt(randomFloor.stay.size()));
                    randomPerson.goal = floors.get(0);
                    randomFloor.order.add(randomPerson);
                    randomFloor.stay.remove(randomPerson);
                    this.control.write();
                }else {
                    i--;
                }
            }
            
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
