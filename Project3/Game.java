package Project3;

/******************************************************
 * Cabella's shopping Trip
 * This game contains several items and rooms to collect
 * and explore
 * @author Wade Callahan
 * @version 11/29/2021
 *****************************************************/

import java.util.*;

public class Game
{
    // stores item inventory
    private ArrayList <Item> inventory;
    // stores the curRoom
    private Room curRoom;
    // sends a message to the GUI
    private String msg;

    private ArrayList <Room> pastRooms;


    // declares all items used in the game
    private Item OrangeVest;
    private Item Ammo;
    private Item FishingHooks;
    private Item Boots;
    private Item FakeBear;
    private Item SteakStrips;

    // declares all rooms used in the game
    private Room Entrance;
    private Room ClothingSection;
    private Room DecoySection;
    private Room AmmoSection;
    private Room FishingSection;
    private Room FootwearSection;
    private Room SnackSection;
    private Room Checkout;

    public Game(){
        // create list for inventory
        inventory = new ArrayList();
        // create the rooms
        createRooms();
        // start at the entrance
        curRoom = Entrance;

        pastRooms = new ArrayList();
        pastRooms.add(curRoom);
    }
    /**************************************************
     Creates, and initiliazes all items and rooms
     Maps out game board
     **************************************************/
    private void createRooms(){

        // assigns the name, description, and weight of all items
        OrangeVest = new Item("Orange Vest", "Hunting vest used to make your presence known to other hunters", 15.00);
        Ammo = new Item("Ammo", "Ammunition for your hunting rifle", 25.00);
        FishingHooks = new Item("Fishing Hooks", "Hooks used for catching fish", 6.00);
        Boots = new Item("Boots", "Waterproof boots are great for wading in shallow water", 4.00);
        FakeBear = new Item("Fake Bear", "A decoy that looks like a black bear", 50.00);
        SteakStrips = new Item("Steak Strips", "Steak Strips are edible", 10.00);

        // assigns the name of each room and adds any items
        Entrance = new Room ("Entrance");
        ClothingSection = new Room ("Clothing Section", OrangeVest);
        DecoySection = new Room ("Decoy Section", FakeBear );
        AmmoSection = new Room ("Ammo Section", Ammo);
        FishingSection = new Room ("Fishing Section", FishingHooks);
        FootwearSection = new Room ("Footwear Section", Boots);
        SnackSection = new Room ("Snack Section", SteakStrips);
        Checkout = new Room ("Checkout");

        // maps out all of the rooms
        Entrance.addNeighbor("West", Checkout);
        Entrance.addNeighbor("North", ClothingSection);
        ClothingSection.addNeighbor("South", Entrance);
        ClothingSection.addNeighbor("West", SnackSection);
        ClothingSection.addNeighbor("North", DecoySection);
        AmmoSection.addNeighbor("South", DecoySection);
        AmmoSection.addNeighbor("West", FishingSection);
        DecoySection.addNeighbor("South", ClothingSection);
        DecoySection.addNeighbor("West", FootwearSection);
        DecoySection.addNeighbor("North", AmmoSection);
        FootwearSection.addNeighbor("South", SnackSection);
        FootwearSection.addNeighbor("East", DecoySection);
        FootwearSection.addNeighbor("North", FishingSection);
        FishingSection.addNeighbor("South", FootwearSection);
        FishingSection.addNeighbor("East", AmmoSection);
        SnackSection.addNeighbor("South", Checkout);
        SnackSection.addNeighbor("East", ClothingSection);
        SnackSection.addNeighbor("North", FootwearSection);
        Checkout.addNeighbor("East", Entrance);
        Checkout.addNeighbor("North", SnackSection);
    }
    /**************************************************
     returns the string that prompts the user
     @return string for welcome message
     **************************************************/
    public String setWelcomeMessage(){
        return  "Welcome to the Cabela's! Here is your shopping list: "
                + " \n Orange Vest, Ammunition, Fishing Hooks, and Boots"
                + "\n \n You are in the entrance.";
    }
    /**************************************************
     returns the string that is stored within msg
     @return msg
     **************************************************/
    public String getMessage(){
        return msg;
    }
    /**************************************************
     Information for every room, relies on user input
     **************************************************/
    public void help(){
        if(curRoom.getDesc().equals("Entrance")){
            msg = "Welcome to Cabela's! You are at the entrance.";
        }
        if(curRoom.getDesc().equals("Clothing Section")){
            msg = "You need an orange vest!";
        }
        if(curRoom.getDesc().equals("Decoy Section")){
            msg = "That bear looks kind of big. You can't fit that in your Prius.";
        }
        if(curRoom.getDesc().equals("Ammo Section")){
            msg = "You should stock up on ammunition.";
        }
        if(curRoom.getDesc().equals("Fishing Section")){
            msg = "You need fishing hooks.";
        }
        if(curRoom.getDesc().equals("Footwear Section")){
            msg = "Your current boots have a whole in the bottom!";
        }
        if(curRoom.getDesc().equals("Snack Section")){
            msg = "Cabela's steak strips are fire!";
        }
        if(curRoom.getDesc().equals("Checkout")){
            msg = "I don't have everything I need!";
        }
    }
    /**************************************************
     Displays information for the current room
     **************************************************/
    public void look(){
        msg = curRoom.getLongDescription();
    }
    /**************************************************
     Moves in the desired direction
     @param direction is the direction that is clicked
     **************************************************/
    public void move(String direction){
        Room nextRoom = curRoom.getNeighbor(direction);
        if (nextRoom == null){
            msg = "You can't go in that direction";
        }else{
            curRoom = nextRoom;
            pastRooms.add(curRoom);
            msg = curRoom.getLongDescription();
        }
    }

    public void moveBack(){

            curRoom = pastRooms.get( pastRooms.size() - 2 );
            pastRooms.remove(pastRooms.size() - 1 );
            msg = curRoom.getLongDescription();

    }

    /**************************************************
     Checks to see if you are at checkout with all of your
     items.
     @return true or false
     **************************************************/
    public boolean gameOver(){
        if((curRoom.getDesc().equals("Checkout") && inventory.size() == 4 )){
            msg = "You have got everything on your list. You won!";
            return true;
        }else{
            return false;
        }
    }
    /**************************************************
     Picks up a desired item and stores in inventory.
     If there is no item in that room or the item is too
     heavy, an error message will be prompted
     **************************************************/
    public void take(){
        if(curRoom.getItem() == null){
            msg = "There is no item in this room.";
        }else{
            if(curRoom.getItem().getWeight()==50){
                msg = "This item is too heavy!";
            }else{
                if(curRoom.getItem() != null){
                    msg = "You're now holding the " + curRoom.getItem().getName() + ".";
                    inventory.add(curRoom.getItem());
                    curRoom.removeItem();
                }
            }
        }
    }
    /**************************************************
     Consumes edible item by removing that item from
     inventory and updating message
     **************************************************/
    public void eat (){
        if(inventory.size() == 0){
            msg = "You don't have any items.";
        }else{
            for(int i=0; i<inventory.size();i++){
                if(inventory.get(i).getName().contains("Steak Strips")){
                    inventory.remove(i);
                    msg = "You have successfully eaten an item.";
                }else{
                    msg = "You don't have any edible items";
                }
            }
        }
    }
    /**************************************************
     Removes deisred item from inventory and leaves it in
     the current room.
     @param name is the name of the item you plan to drop
     **************************************************/
    public void drop (String name){
        if(inventory.size() == 0){
            msg = "You don't have any items.";
        }else{
            for(int i=0; i<inventory.size();i++){
                if(curRoom.getItem() == null && inventory.get(i).getName().contains(name)){
                    curRoom.setItem(inventory.get(i));
                    inventory.remove(i);
                    msg = "You have successfully dropped an item.";
                }else{
                    msg = "This room has too many items already in here.";
                }
            }
        }
    }
    /**************************************************
     Shows all items in inventory
     **************************************************/
    public void show(){
        msg = "";
        if(inventory.get(0) == null){
            msg = "You don't have any items.";
        }else{
            for(int i=0; i<inventory.size();i++){
                msg = "Inventory: \n" + msg + inventory.get(i).getName() + "\n";
            }
        }
    }
}
