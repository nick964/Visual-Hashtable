/*
 * Assignment #8 - Visual Hash Table
 * Nick Robinson - CIS 2168 Section 1
 * HashTable.java - This file builds the structure of the hash table
 */
package visualhashtable_eb;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import simplegui.SimpleGUI;


public class HashTable {
    public double hashtable[];
    public double copy_hashtable[];
    Random rand = new Random();
    public int collisons[];
    int totalCol;
    int NumberOfElements;

    
    public HashTable(int size) {
        hashtable = new double[size];
        copy_hashtable = new double[size];
        collisons = new int[size];
        
        Arrays.fill(hashtable, -1);
        Arrays.fill(copy_hashtable, -1);
        Arrays.fill(collisons, -1);
        
    }
    
    
    public boolean isFull() {
        if (NumberOfElements == hashtable.length) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public void insert() {
        for(int i = 0; i < 10; i++) {
            double v = Math.random() * 1000;
            insert_in_hash(v);
            NumberOfElements++;
        }
    }
    
    
    public int computeIndex(int v) {
        //this is the hash function
        int pos = (int) ((Math.random() * v) % hashtable.length);

        if (hashtable[pos] == -1){
            //if hashtable[pos] = -1, then that means it can be inserted without collisons
            //so we should set the collisons to 0 at this point
           collisons[pos] = 0;
           return pos;
        } else {
            
        //keep track of the current positions    
        int startingPosition = pos;
        while (hashtable[pos] != -1) {
            //set the collision to the current index
             collisons[pos]++;
             //increment  total number of collisons..that way you can get average
             totalCol++;
             pos = pos + 1;

             if (pos > 99) {
                 pos = 0;
             }
         }
        }
        return pos;
    }

    private void insert_in_hash(double v) {
        int index;
        index = computeIndex((int) v);
        hashtable[index] = v;
    }
    
    public void visualize(SimpleGUI sg) {
        int boxSize = 25;
        int xPos = 10;
        int yPos = 10;
        
        //keep track of whether to increment y
        int columnCount = 0;
        
        int x = xPos;
        int y = yPos;
        
        for(int i = 0; i < hashtable.length; i++) {
            drawBox(x, y, boxSize, collisons[i], sg);
            x = x + 40;
            columnCount = columnCount + 1;
            if(columnCount == 10) {
                y = y + 40;
                x = 10;
                columnCount = 0;
            }
        }
        
        
    }
    
    private void drawBox(int x, int y, int size, int coll, SimpleGUI sg){
        
        // Compute the appropriate color for this number of collisions
        Color cellColor = computeCellColor(coll);
        
        // Redraw the box with the appropriate cell color
        sg.drawFilledBox(x, y, size, size, cellColor, 1, null);       
    }
    
    public Color computeCellColor(int collisions) {
        if(collisions == -1) {
            return Color.black;
        }
         int r = (int)(255.0/7.0*collisions); // collisions = 0: r = 0. coll >=7: r>=255.
         r = (int)Math.min(r,255); // same as: if (r>255) r = 255;
         int g = 255-r; // green value: the 'opposite' of r
         int b = 0; // sets blue to zero, only red/green values are displayed
         return(new Color(r,g,b));
    }
    
    public void hashDebug() {
        System.out.println("Total Col: " + totalCol);
        System.out.println("Total Num of Elements: " + NumberOfElements);
    }
    
    public void printHash(SimpleGUI sg) {
        sg.eraseSingleDrawable("TOTALCOL");
        sg.eraseSingleDrawable("TOTALELE");
        String col = "Total Collisons: " + totalCol;
        String numEle = "Total Number of Elements in the Hash Table: " + NumberOfElements;
        sg.drawText(col, 10, 450, Color.black, 1.0, "TOTALCOL");
        sg.drawText(numEle, 150, 450, Color.black, 1.0, "TOTALELE");
        
    }
    

            
            
}
