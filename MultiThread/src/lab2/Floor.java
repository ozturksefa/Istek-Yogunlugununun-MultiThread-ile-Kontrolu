/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.ArrayList;

/**
 *
 * @author farukarig
 */
public class Floor {
    
    public int k;
    
    public ArrayList<Person> order = new ArrayList();
    
    public ArrayList<Person> stay = new ArrayList();

    Floor(int i) {
        this.k = i;
    }
    
}
