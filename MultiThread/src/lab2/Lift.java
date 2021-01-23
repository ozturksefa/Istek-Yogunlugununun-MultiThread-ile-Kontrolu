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
public class Lift extends Thread {
    public boolean status = false;
    
    public Floor floor;
    
    public ArrayList<Floor> floors;
    
    public Floor destination = new Floor(0);
    
    public boolean direction = true;
    
    public int capacity = 10;
    
    public int count_inside = 0;
    
    public ArrayList<Person> inside = new ArrayList();
    
    public boolean firstRun = true;
    
    public int k;

    Lift(ArrayList<Floor> floors,int k) {
        this.floor = floors.get(0);
        this.floors = floors;
        this.k = k;
    }
    
    
    @Override
    public void run() {
        while(true){
            
            if(this.status){
                
                if(this.firstRun){
                    this.firstRun = false;
                }else{
                    if(this.floor.k==0 && !this.direction){
                        this.direction = true;
                    }

                    if(this.floor.k==4 && this.direction){
                        this.direction = false;
                    }

                    if(this.direction){
                        this.floor = this.floors.get(this.floor.k+1);
                    }else {
                        this.floor = this.floors.get(this.floor.k-1);
                    }
                }
                
                if(this.inside.size()>0){
                    ArrayList<Person> removeList = new ArrayList();
                    this.inside.forEach((i) -> {
                        if(i.goal == this.floor){
                            if(i.goal.k!=0){
                                this.floors.get(i.goal.k).stay.add(i);
                                i.goal = null;
                            }
                            removeList.add(i);
                        }
                    });
                    removeList.forEach(e->{
                        this.inside.remove(e);
                        this.count_inside--;
                    });
                }
                
                if(this.floor.k==0 && this.k!=0){
                    int orderCount = 0;
                    for(Floor i : this.floors){
                        orderCount += i.order.size();
                    }
                    if(orderCount<10){
                       this.status = false;
                       this.stop();
                    }
                }
                
                int limit = this.capacity - this.count_inside;
                
                ArrayList<Person> removeList = new ArrayList();
                int a = 0;
                for(int i=0;i<limit;i++){
                    if(this.floor.order.size()>a && this.floor.order.get(a)!=null){
                        Person person = this.floor.order.get(a);
                        if((this.direction && person.goal.k>this.floor.k) || (!this.direction && person.goal.k<this.floor.k) || this.floor.k==0 || this.floor.k==this.floors.size()-1){
                            removeList.add(person);
                            this.inside.add(person);
                            this.count_inside++;
                        }else {
                            i--;
                        }
                        a++;
                    }
                }
                    removeList.forEach(e->{
                            this.floor.order.remove(e);
                    });
                
                
                
                
            }
            
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
