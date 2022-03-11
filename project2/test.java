package project2;


import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class test {
    public static void main(String args[]){
        GeyserDatabase db = new GeyserDatabase();

        // read small data file created just for testing
        db.readGeyserData("C:\\Users\\calla\\IdeaProjects\\ClassProjectCIS159\\src\\project2\\GeyserData.txt");
        ArrayList<Eruption> eruptions;

        System.out.println(db.geysers.toString());

        assertEquals(db.getNumEruptions(), 4);
        assertEquals(db.getNumGeysers(), 2);
        assertEquals(db.getLateNightEruption(), "Scott");

        // check late night eruption
        Eruption e = db.getLateNightEruption();
        if(e.getHour() != 20 && e.getMinute() != 35){
            System.out.println("Error: Latest eruption should be at 20:35");
        }

        System.out.println("Scanning complete.");
    }

}
