package project2;

import java.io.*;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

/******************************************************
 * Contains over 12,000 yellowstone national park
 * geyser eruptions. The user can search for a variety
 * of features such as most active geyser or by name.
 * @author Wade Callahan
 * @version 11/8/2021
 *****************************************************/

public class GeyserDatabase{

    /** large list of eruptions */
    ArrayList<Eruption> eruptions;

    private final int MINS = 31;

    /** large list of geysers */
    ArrayList<Geyser> geysers;

    /****************************************************
     * Constructor initializes arrays for geyser and
     * eruption list.
     *************************************************/
    public GeyserDatabase(){
        //
        eruptions = new ArrayList<>();
        geysers = new ArrayList<>();
    }

    /**************************************************
     Count all eruptions in database
     @return total number of eruptions
     **************************************************/
    public int getNumEruptions(){
        return eruptions.size();
    }

    /******************************************
     * Adds an eruption to the ArrayList
     * @param e eruption within list
     *****************************************/
    public void addEruption(Eruption e) {
        eruptions.add(e);
    }

    /**************************************************
     @return the eruption arraylist
     **************************************************/
    public ArrayList<Eruption> getEruptionList(){
        return eruptions;
    }

    /******************************************
     * @return geyser ArrayList
     *****************************************/
    public ArrayList<Geyser> getGeyserList() {
        return geysers;
    }

    /**************************************************
     Count number of eruptions on the provided day
     @param month of requested date
     @param day of requested date
     @param year of requested date
     @return number of eruptions for requested date
     **************************************************/
    public int getNumEruptions(int month, int day, int year){
        int total = 0;
        // cycles through eruption list and increments counter if dates match
        for (Eruption e: eruptions ) {
            if (e.getMonth() == month && e.getDay() == day && e.getYear() == year) {
                total++;
            }
        }
        return total;
    }

    /**************************************************
     Find day within requested YEAR with most eruptions
     @param year for search criterion
     @return String representation of the date
     **************************************************/
    public String findDayWithMostEruptions(int year){
        int maxNum = 0;
        int month = 0;
        int day = 0;
        String mostEruption = "";

        // Cycles though every month and day value, searching for most eruptions
        for(int m = 1; m <= 12; m++){
            for(int d = 1; d <= 31; d++){
                int counter = getNumEruptions(m, d, year);
                if ( counter > maxNum ){
                    maxNum = counter;
                    month = m;
                    day = d;
                }
            }
        }

        mostEruption = "The day with the most eruptions was "
                + month + "/" + day + "/" + year + ".";
        return mostEruption;
    }

    /**************************************************
     Find eruption that occured the latest in any day
     @return eruption that happened latest (any day)
     **************************************************/
    public Eruption getLateNightEruption(){
        // start with first eruption in list
        Eruption latest = eruptions.get(0);
        for ( Eruption e : eruptions ){
            if (e.getMonth() > latest.getMonth()){
                if(e.getDay() > latest.getDay()) {
                    if (e.getHour() > latest.getHour()) {
                        if (e.getMinute() > latest.getMinute()) {
                            latest = e;
                        }
                    }
                }
            }
        }
        return latest;
    }

    /**************************************************
     Get all eruptions for requested Geyser name
     Ignore case and allow partial matches
     @param searchFor name of geyser
     @return a list of all eruptions with matching name
     **************************************************/
    public ArrayList<Eruption> getEruptions(String searchFor){
        ArrayList<Eruption> list = new ArrayList<Eruption>();

        for (Eruption e : eruptions){
            if( e.getName().toLowerCase().contains(searchFor.toLowerCase())){
                list.add(e);
            }
        }
        return list;
    }

    public ArrayList<Eruption> getLastEruption(){
        ArrayList<Eruption> list = new ArrayList<Eruption>();
        int i;
        for (Geyser g : geysers){
            i = 0;
            for ( Eruption e : eruptions){
                if (e.toString().contains(g.getName())){
                    if (e.getMonth() > list.get(i).getMonth()){
                        if(e.getDay() > list.get(i).getDay()) {
                            if(e.getHour() > list.get(i).getHour()){
                                if(e.getMinute() > list.get(i).getMinute()){
                                    list.add(e);
                                }
                            }
                        }
                    }
                }
                i++;
            }
        }
        return list;
    }


    public ArrayList<Eruption> getXMin(){
        ArrayList<Eruption> list = new ArrayList<Eruption>();
        ArrayList<String> nameList = new ArrayList<String>();

        for (Eruption e : eruptions){
            if( e.getMinute() == MINS){

                list.add(e);
            }
        }

        return list;
    }

    public ArrayList<Geyser> getGeyserMin(){
        ArrayList<Eruption> list = new ArrayList<Eruption>();

        for (Eruption e : eruptions){
            if( e.getMinute() == MINS){
                list.add(e);
            }
        }

        ArrayList<String> nameList = new ArrayList<String>();
        // create temporary list of unique geyser names
        for(Eruption e : list){
            if(!nameList.contains(e.getName())){
                nameList.add(e.getName());
            }
        }

        ArrayList<Geyser> geyserList = new ArrayList<Geyser>();
        for(String s: nameList){

            Geyser g = new Geyser(s);

            for(Eruption e:list){
                if(e.getName().equals(g.getName()))
                    g.increment();
            }

            geyserList.add(g);
        }
        return geyserList;
    }



    /**********************************************************
     Create a list of geysers and the number of their eruptions
     **********************************************************/
    private void createGeyserList(){

        ArrayList<String> nameList = new ArrayList<String>();
        // create temporary list of unique geyser names
        for(Eruption e : eruptions){
            if(!nameList.contains(e.getName())){
                nameList.add(e.getName());
            }
        }

        ArrayList<Geyser> geyserList = new ArrayList<Geyser>();
        for(String s: nameList){

            Geyser g = new Geyser(s);

            for(Eruption e:eruptions){
                if(e.getName().equals(g.getName()))
                    g.increment();
            }

            geyserList.add(g);
        }

        // Stores geyserList inside geyser array
        geysers = geyserList;
    }

    /******************************************
     * @return number of items in geyser ArrayList
     *****************************************/
    public int getNumGeysers() {
        return geysers.size();
    }

    /**************************************************
     Find Geyer with most eruptions
     @return geyser with most eruptions
     **************************************************/
    public Geyser findMostActiveGeyser(){

        // start with first geyser in the list
        Geyser most = geysers.get(0);
        for(Geyser g : geysers){
            if( g.getNumEruptions() > most.getNumEruptions()){
                most = g;
            }
        }
        return most;
    }

    /**************************************************
     Find Geyer with least eruptions
     @return geyser with fewest eruptions
     **************************************************/
    public Geyser findLeastActiveGeyser(){

        //start with first geyser in the list
        Geyser least = geysers.get(0);
        for(Geyser g : geysers){
            if( g.getNumEruptions() < least.getNumEruptions()){
                least = g;
            }
        }
        return least;
    }

    /************************************************************
     * Read Section 11.5 to complete this method
     ***********************************************************/
    public void readGeyserData(String filename){
        //ArrayList<Eruption> eruptions = new ArrayList<Eruption>();


        // Attempt to read the complete set of data from a text file
        try{
            // open the text file and use a Scanner to read the text
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            String text;

            // keep reading as long as there is more data
            while(sc.hasNextLine()) {
                text = sc.nextLine();

                // FIX ME: remove this print statement after method works
                //System.out.println(text);
                Eruption e = new Eruption(text);

                eruptions.add(e);
            }
            System.out.println(eruptions.size());
            sc.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: "
                    + filename);
        }

        createGeyserList();
    }

    /************************************************************
     * writes data to a provided file
     * @param filename is a string that is used to open a file
     ***********************************************************/
    public void writeData(String filename){

        Writer output = null;
        String line;
        try{

            File file = new File(filename);
            output = new BufferedWriter(new FileWriter(file));

            for(Eruption e: eruptions){
                String info = e.getDay()+"/"+e.getMonth()+"/"+(e.getYear());
                info += ","+e.getName()+",";
                info += e.getHour()+":"+e.getMinute();
                System.out.println(info);
                output.write (info+"\n");
            }
            output.close();

            // Could not find file
        }
        catch(FileNotFoundException error1) {
            System.out.println("Failed to read the data file: " +
                    filename);
        }

        // Problems reading the file
        catch(IOException error2){
            System.out.println("Oops!  Something went wrong when I tried " +
                    "to read the file.");
        }
    }
}
