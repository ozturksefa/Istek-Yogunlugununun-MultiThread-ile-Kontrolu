/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farukarig
 */
public class Control extends Thread {

    public ArrayList<Floor> floors;
    public ArrayList<Lift> lifts;
    
    public int exit_count = 0;
    
    Control(ArrayList<Floor> floors, ArrayList<Lift> lifts) {
        this.floors = floors;
        this.lifts = lifts;
    }
    
    public void run() {
            //this.writeA();
        while(true){
            
            try {
            Lift tmp = null;
            
            int orderCount = 0;
            for(Floor i : this.floors){
                orderCount += i.order.size();
            }
            if(orderCount>20){
                tmp = null;
                for(Lift j : this.lifts){
                    if(!j.status){
                        tmp = j;
                        break;
                    }
                }
                if(tmp!=null){
                    tmp.status = true;
                    if(tmp.firstRun)
                        tmp.start();
                }
            }
            
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
            
        
    }
    
    public void write(){}
    public void writeA(){
        while(true){
            
            System.out.println("---------------------------------------------------");
            
            int a = 0;
            for (Floor i : floors){
                System.out.println(a + ". Floor : " + i.stay.size() + "       queue : " + i.order.size());
                a++;
            }
            
            this.exit_count = 0;
            floors.forEach(i -> {
                i.order.forEach(j->{
                    if(j!=null && j.goal==floors.get(0)){
                        this.exit_count++;
                    }
                });
            });
            System.out.println("exit count : " + this.exit_count);
            
            int b = 0;
            for (Lift i : lifts){
                System.out.println("active : " + i.status);
                if(i.status){
                    System.out.println("                        mode : working");
                }else {
                    System.out.println("                        mode : idle");
                }
                System.out.println("                        floor : "+i.floor.k);
                System.out.println("                        destination : "+i.destination.k);
                if(i.direction){
                    System.out.println("                        direction : up");
                }else {
                    System.out.println("                        direction : down");
                }
                System.out.println("                        capacity : "+i.capacity);
                System.out.println("                        count_inside : "+i.count_inside);
                
                System.out.print("                        inside : [");
                
                for (int j=0; j<5; j++){
                    
                    int count = 0;
                    for(Person k : i.inside){
                        if(k.goal == floors.get(j)){
                            count++;
                        }
                    }
                    if(count!=0)
                        System.out.print("["+count+ " " + j +"]");
                }
                System.out.print("]");
                System.out.println("");
                
                b++;
            }            
            
            int c = 0;
            for (Floor i : floors){
                System.out.print(c + ". Floor : [");
                
                for (int j=0; j<5; j++){
                    
                    int count = 0;
                    for(Person k : i.order){
                        if(k.goal == floors.get(j)){
                            count++;
                        }
                    }
                    if(count!=0)
                        System.out.print("["+count+ " " + j +"]");
                }
                System.out.print("]");
                System.out.println("");
                
                c++;
            }
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
