package project2;

/**
 * Write a description of class Geyser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Geyser
{
    // instance variables - replace the example below with your own
    private int eruptions = 0;
    private String name;

    /**
     * Constructor for objects of class Geyser
     */
    public Geyser(String n){
        name = n;
    }
    public void increment(){
        eruptions++;
    }
    public String getName(){
        return name;
    }
    public int getNumEruptions(){
        return eruptions;
    }
}