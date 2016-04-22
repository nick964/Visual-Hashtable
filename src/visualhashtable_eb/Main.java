/*
 * Assignment #8 - Visual Hash Table
 * Nick Robinson - CIS 2168 Section 1
 * Main.java - This file builds the structure of the hash table
 */
package visualhashtable_eb;

import java.awt.Color;
import simplegui.*;


public class Main implements GUIListener{
    private SimpleGUI sg;
    HashTable hash;
    
    public Main(){
       sg = new SimpleGUI();
       createGUI();
       sg.registerToGUI(this);
       
       hash = new HashTable(100);
       hash.visualize(sg);
        
    }
    
  public void createGUI() {
        
        int r = 211;
        Color lightgrey = new Color(r,r,r);
        sg = new SimpleGUI();
        sg.setBackgroundColor(lightgrey);
        
        
        sg.labelButton1(("Next 10"));
        sg.labelButton2("Reset");
    }
    
    public static void main(String args[]) {
        Main mn = new Main();
    }
    
    
    
    @Override
    public void reactToButton1() {
        if(!hash.isFull()) {
        hash.insert();
        hash.visualize(sg);
        hash.printHash(sg);
       // hash.hashDebug();
        } else {
            printFull();
            
        }
        
    }
    
    public void printFull() {
        
        sg.drawFilledBox(400, 130, 200, 100, Color.black, 1.0, "bg");
        sg.drawText("Heap is full!", 450, 150, Color.red, 1.0, "fullheap");
        sg.drawText("Please reset to continue.", 420, 170, Color.red, 1.0, "fullheap");
        
    }

    @Override
    public void reactToButton2() {
       reset();
       hash.visualize(sg);
    }
    
    public void reset() {
        hash = new HashTable(100);
        sg.eraseAllDrawables();
        hash.visualize(sg);
    }

    @Override
    public void reactToSwitch(boolean bln) {
        System.out.println("This is not supported.");
    }

    @Override
    public void reactToSlider(int i) {
        System.out.println("This is not supported.");
    }
    
}
