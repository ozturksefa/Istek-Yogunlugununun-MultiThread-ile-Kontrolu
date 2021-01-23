/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author farukarig
 */
public class Person {
    public Floor floor;
    public Floor goal;
    
    Person(ArrayList<Floor> floors) {
        this.floor = floors.get(0);
        Random random = new Random();
        this.goal = floors.get(random.nextInt(4)+1);
    }
}
