/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farukarig
 */
public class Main {
    
    public int exit_count = 0;
    
    public static void main(String[] args) {
        
        
        
        ArrayList<Floor> floors = new ArrayList();
        
        floors.add(new Floor(0));
        floors.add(new Floor(1));
        floors.add(new Floor(2));
        floors.add(new Floor(3));
        floors.add(new Floor(4));
        
        ArrayList<Lift> lifts = new ArrayList();
        
        lifts.add(new Lift(floors,0));
        lifts.add(new Lift(floors,1));
        lifts.add(new Lift(floors,2));
        lifts.add(new Lift(floors,3));
        lifts.add(new Lift(floors,4));
        
        
        Control control = new Control(floors,lifts);
        control.start();
        
        lifts.get(0).status = true;
        lifts.get(0).start();
        
        Login login = new Login(floors,control);
        login.start();
        
        Exit exit = new Exit(floors,control);
        exit.start();
        
        
        while(true){
            
            System.out.println("---------------------------------------------------");
            
            int a = 0;
            for (Floor i : floors){
                System.out.println(a + ". Floor : " + i.stay.size() + "       queue : " + i.order.size());
                a++;
            }
            /*
            this.exit_count = 0;
            floors.forEach(i -> {
                i.order.forEach(j->{
                    if(j!=null && j.goal==floors.get(0)){
                        this.exit_count++;
                    }
                });
            });
            System.out.println("exit count : " + this.exit_count);*/
            
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
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
