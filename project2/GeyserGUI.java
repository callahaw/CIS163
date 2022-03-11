package project2;

import project2.Eruption;
import project2.GeyserDatabase;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.text.*;

/***********************************************************************
 * GUI front end for a Yellowstone Geyser database
 *
 * @author Scott Grissom
 * @version August 1, 2016
 **********************************************************************/
public class GeyserGUI extends JFrame implements ActionListener{

    /** results box */
    private JTextArea resultsArea;

    /** creates new Geyser Database */
    private project2.GeyserDatabase db;

    // FIX ME: define labels, text fields and buttons
    /** labels different sections of the GUI */
    private JLabel eruptions;
    private JLabel geysers;

    /** text fields for inputing information */
    private JTextField month;
    private JTextField day;
    private JTextField year;
    private JTextField geyser;

    /** buttons for interaction with geyser information */
    private JButton lateNightEruption;
    private JButton numOnDay;
    private JButton maxEruptionsInYear;
    private JButton byName;
    private JButton mostActive;
    private JButton leastActive;
    private JButton geyserList;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu reportsMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem;
    private JMenuItem countItem;
    private JMenuItem geyserItem;

    /*********************************************************************
     Main Method
     *********************************************************************/
    public static void main(String arg[]){
        project2.GeyserGUI gui = new project2.GeyserGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Yellowstone Geysers");
        gui.pack();
        gui.setVisible(true);
    }

    /*********************************************************************
     Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GeyserGUI(){
        db = new GeyserDatabase();

        //db.readGeyserData("GeyserData.txt");

        // create the Gridbag layout
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        // create the Results Text Area (5 x 10 cells)
        resultsArea = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        position.gridx = 0;
        position.gridy = 0;
        position.gridheight = 10;
        position.gridwidth = 5;
        position.insets =  new Insets(20,20,0,0);
        add(scrollPane, position);

//        // Create label and textfield for Month
//        position = makeConstraints(0,13,1,1,GridBagConstraints.LINE_START);
//        position.insets =  new Insets(0,20,0,0);
//        add(new JLabel("Month2"), position);
//
//        position = makeConstraints(0,14,1,1,GridBagConstraints.LINE_START);
//        position.insets = new Insets(0,20,0,0);
//        month = new JTextField(10);
//        add(month, position);
//
//        // FIX ME: add labels and text fields at bottom
//        // Create label and textfield for Day
//        position = makeConstraints(1,13,1,1,GridBagConstraints.LINE_START);
//        position.insets = new Insets(0,20,0,0);
//        add(new JLabel("Day2"), position);
//
//        position = makeConstraints(1,14,1,1,GridBagConstraints.LINE_START);
//        position.insets = new Insets(0,20,0,0);
//        day = new JTextField(10);
//        add(day, position);
//
//        //Create label and textfield for Year
//        position = makeConstraints(2,13,1,1,GridBagConstraints.LINE_START);
//        position.insets = new Insets(0,20,0,0);
//        add(new JLabel("Year2"), position);
//
//        position = makeConstraints(2,14,1,1,GridBagConstraints.LINE_START);
//        position.insets = new Insets(0,20,0,0);
//        year = new JTextField(10);
//        add(year, position);

        // Create label and textfield for Month
        position = makeConstraints(0,10,1,1,GridBagConstraints.LINE_START);
        position.insets =  new Insets(0,20,0,0);
        add(new JLabel("Month"), position);

        position = makeConstraints(0,11,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        month = new JTextField(10);
        add(month, position);

        // FIX ME: add labels and text fields at bottom
        // Create label and textfield for Day
        position = makeConstraints(1,10,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(new JLabel("Day"), position);

        position = makeConstraints(1,11,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        day = new JTextField(10);
        add(day, position);

        //Create label and textfield for Year
        position = makeConstraints(2,10,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(new JLabel("Year"), position);

        position = makeConstraints(2,11,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        year = new JTextField(10);
        add(year, position);

        // Create lebel and textfield for Geyser
        position = makeConstraints(3,10,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(new JLabel("Geyser"), position);

        position = makeConstraints(3,11,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        geyser = new JTextField(10);
        add(geyser, position);

        // FIX ME: Add buttons and labels on right side
        // Create label for Eruptions and Geysers sections of buttons
        position = makeConstraints(5,0,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(new JLabel("Eruptions"), position);

        position = makeConstraints(5,5,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(new JLabel("Geysers"), position);

        // Create button for late night eruption
        lateNightEruption = new JButton("Late Night Eruption");
        position = makeConstraints(5,1,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(lateNightEruption, position);
        lateNightEruption.addActionListener(this);

        // Create button for eruptions on certian day
        numOnDay = new JButton("# on Date");
        position = makeConstraints(5,2,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(numOnDay, position);
        numOnDay.addActionListener(this);

        // Create button for maximum eruptions in the year
        maxEruptionsInYear = new JButton("Max Eruptions in Year");
        position = makeConstraints(5,3,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(maxEruptionsInYear, position);
        maxEruptionsInYear.addActionListener(this);

        // Create button for searching by name
        byName = new JButton("By Name");
        position = makeConstraints(5,4,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(byName, position);
        byName.addActionListener(this);

        // Create button for most active geyser
        mostActive = new JButton("Most Active");
        position = makeConstraints(5,6,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(mostActive, position);
        mostActive.addActionListener(this);

        // Create button for least active geyser
        leastActive = new JButton("List by X mins");
        position = makeConstraints(5,7,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(leastActive, position);
        leastActive.addActionListener(this);

        // Create button for geyser list
        geyserList = new JButton("Geyser List");
        position = makeConstraints(5,8,1,1,GridBagConstraints.LINE_START);
        position.insets = new Insets(0,20,0,0);
        add(geyserList, position);
        geyserList.addActionListener(this);

        // set up File menus
        setupMenus();
        pack();
    }

    /*********************************************************************
     List all entries given an ArrayList of eruptions.  Include a final
     line with the number of eruptions listed
     @param m list of eruptions
     *********************************************************************/
    private void displayEruptions(ArrayList <project2.Eruption> m){
        resultsArea.setText("");
        for(project2.Eruption e : m){
            resultsArea.append("\n" + e.toString());
        }
    }

    /*********************************************************************
     Respond to menu selections and button clicks
     @param e the button or menu item that was selected
     *********************************************************************/
    public void actionPerformed(ActionEvent e){
        project2.Eruption item = null;

        // either open a file or warn the user
        if (e.getSource() == openItem){
            openFile();
            System.out.println(db.getNumEruptions());
        }
        else if(db.getNumEruptions() == 0){
            String errorMessage = "Did you forget to open a file?";
            resultsArea.setText(errorMessage);
            return;
        }

        // menu item - quit
        if (e.getSource() == quitItem){
            System.exit(1);
        }

        // Displays number of eruptions and geysers
        if (e.getSource() == countItem){
            resultsArea.setText(db.getNumEruptions() + " eruptions ");
            resultsArea.append(db.getNumGeysers() + " geysers");
        }

        // Displays late night eruptions
        if (e.getSource() == lateNightEruption) {
            item = db.getLateNightEruption();
            resultsArea.setText("Latest Eruption \n" + item.toString());
        }

        // Displays all geyser names
        if (e.getSource() == geyserList) {
            String temp = "";
            resultsArea.setText(db.getNumGeysers() + " geysers \n");
            for (int i = 0; i <= db.getNumGeysers() - 1; i++) {
                temp = db.getGeyserList().get(i).getName();
                resultsArea.append(temp + "\n");
            }
        }

        // Max eruptions day in a year (checks for year)
        if (e.getSource() == maxEruptionsInYear) {
            String max = year.getText();

            int numMax = Integer.parseInt(max);

            if (numMax == 2010) {
                resultsArea.setText(db.findDayWithMostEruptions(numMax));
            }
            else if (numMax > 2010 || numMax < 2010) {
                resultsArea.setText(0 + " eruptions in " + numMax);
            }
        }

        // Lists all eruptions for a geyser (checks for name)
        if (e.getSource() == byName) {
            if (geyser.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Enter a geyser's name");
            }
            else {
                String temp = geyser.getText();
                String temp2 = "";
                resultsArea.setText("");
                ArrayList<Eruption> list = db.getEruptions(temp);
                for (int i = 0; i <= list.size()-1; i++) {
                    resultsArea.append( list.get(i).toString() + "\n");
                }

            }
        }

        // Displays eruptions for a particular date
        // checks for month, day and year
        if (e.getSource() == numOnDay ) {
            String m = month.getText();
            String d = day.getText();
            String y = year.getText();

            int numMonth = Integer.parseInt(m);
            int numDay = Integer.parseInt(d);
            int numYear = Integer.parseInt(y);

            if ((numMonth >=1 && numMonth <= 12) && (numDay >= 1 && numDay <= 31) && (numYear == 2010)) {
                resultsArea.setText(db.getNumEruptions(numMonth, numDay, numYear) + " eruptions on " + numMonth + "/" + numDay + "/" + numYear);
            }
            else if (numYear > 2010 || numYear < 2010) {
                resultsArea.setText(0 + " eruptions on " + numMonth + "/" + numDay + "/" + numYear);
            }
        }

        if (e.getSource() == mostActive){
            //Geyser g = new Geyser(db.findMostActiveGeyser().toString());
            //g =
            resultsArea.setText("Most Active Geyser is " + db.findMostActiveGeyser().getName());
        }
        if (e.getSource() == leastActive){
            ArrayList<Eruption> list = db.getLastEruption();
            for (int i = 0; i <= list.size()-1; i++) {
                resultsArea.append( list.get(i) + "\n");
            }   resultsArea.append( list.size() + " geysers listed.");
        }

//        if(geyser.getText().length() == 0){
//            JOptionPane.showMessageDialog(this, "Provide a geyser name");
//        }else{
//            // Displays all eruptions for the requested geyser
//            //resultsArea.setText();
//        }
    }

    /*********************************************************************
     In response to the menu selection - open a data file
     *********************************************************************/
    private void openFile(){
        JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
        int returnVal = fc.showOpenDialog(this);


        // If the user didnt select a file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println(111);
            String filename = fc.getSelectedFile().getName();
            // use the name of your lottery ticket variable
            db.readGeyserData("C:\\Users\\calla\\IdeaProjects\\ClassProjectCIS159\\src\\project2\\GeyserData.txt");
        }
    }

    /*********************************************************************
     Create a custom gridbag constraint
     *********************************************************************/
    private GridBagConstraints makeConstraints(int x, int y, int h, int w, int align){
        GridBagConstraints rtn = new GridBagConstraints();
        rtn.gridx = x;
        rtn.gridy = y;
        rtn.gridheight = h;
        rtn.gridwidth = w;

        // set alignment: LINE_START, CENTER, LINE_END
        rtn.anchor = align;
        return rtn;
    }

    /*********************************************************************
     Set up the menu items
     *********************************************************************/
    private void setupMenus(){
        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        reportsMenu = new JMenu("Reports");
        countItem = new JMenuItem("Counts");

        // assign action listeners
        quitItem.addActionListener(this);
        openItem.addActionListener(this);
        countItem.addActionListener(this);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        reportsMenu.add(countItem);
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(reportsMenu);
        setJMenuBar(menus);
    }
}
