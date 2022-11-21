
package smartfarm;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.Scanner;



/**
 *
 * @author Emin
 */

 class SmartFarm {
   
   ArrayList<Cow> cowList = new ArrayList(); 
   Cow firstCow;
   Cow cow;
   int counter = 0;
     
   
   public void addFirstCow(Cow firstCow) {
       
       this.firstCow = firstCow;
       cowList.add(firstCow);
    }
   
   
    public void addnewCow(Cow cow) {
   
       this.cow = cow;
       cowList.add(cow);
   }
           
    
       
    public void start() {
       
        Scanner inputCowDetails = new Scanner(System.in);
        String cowName, cowID, calfName, calfID;
        Cow matchingCow;
        
        //Determine if the farm has any cows in it
        if (!cowList.isEmpty()) {
        
         // if there are any cows in the farm, determine the first cow of the farm
               System.out.print("Enter Cow ID: ");
               cowID = inputCowDetails.nextLine();
               
             
              
             for (Iterator<Cow> iterator = cowList.iterator(); iterator.hasNext(); ) {
                     
                        matchingCow = iterator.next();
                   
                        
                        if (matchingCow.getID().equals(cowID) && matchingCow.getID().equals(firstCow.getID())) {
                            
                            
                            cowName = matchingCow.getName();
                            System.out.print("Enter the ID of the calf which has been born to " + cowName + ": ");
                            calfID = inputCowDetails.nextLine();
                            System.out.print("Enter the NAME of the calf which has been born to " + cowName + ": ");
                            calfName = inputCowDetails.nextLine(); 
                           
                            cow = new Cow(cowID, cowName, calfID, calfName);
                            
                            addnewCow(cow);
                            counter++;
                            System.out.println("Succesfully added the first cow's newborn calf, ID: " + calfID + " NAME: " + calfName + " to the list");
                            firstCow.giveBirth(cowID, calfID, calfName);
                            nextStage();
                           
                        } 
                        
                        else if (matchingCow.getID().equals(cowID))  {
                            
                            
                            cowName = matchingCow.getName();
                            System.out.print("Enter the ID of the calf which has been born to " + cowName + ": ");
                            calfID = inputCowDetails.nextLine();
                            System.out.print("Enter the NAME of the calf which has been born to " + cowName + ": ");
                            calfName = inputCowDetails.nextLine(); 
                            
                            cow = new Cow(cowID, cowName, calfID, calfName);
                            addnewCow(cow);
                            counter++;
                            System.out.println("Succesfully added " + cowName + "'s newborn cow ID: " + calfID + " NAME: " + calfName + " to the list");
                            cow.giveBirth(cowID, calfID, calfName);
                            nextStage();
                         } 
                
                   } 
        
        } else {
                          // If the cow hasn't got parent ID, it means it is the first cow in the farm, without parents 
                           System.out.println("There are no cows in the farm, adding the first cow");

                           System.out.print("Enter Cow ID: ");
                           cowID = inputCowDetails.nextLine();
                           System.out.print("Enter Cow Name: ");
                           cowName = inputCowDetails.nextLine();
                           System.out.print("Enter the ID of the calf which has been born to " + cowName + ": ");
                           calfID = inputCowDetails.nextLine();
                           System.out.print("Enter the NAME of the calf which has been born to " + cowName + ": ");
                           calfName = inputCowDetails.nextLine(); 

                           firstCow = new Cow(cowID, cowName);
                           addFirstCow(firstCow);
                           counter++;
                           
                           cow = new Cow(cowID, cowName, calfID, calfName);
                           addnewCow(cow);
                           System.out.println("Succesfully added " + cowName + "'s newborn cow ID: " + calfID + " NAME: " + calfName + " to the farm");
                           firstCow.giveBirth(cowID, calfID, calfName);
                           nextStage();
      
        }     
       
}   
 
    
    
    void nextStage() {
        //Clear the screen
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n\n");
        
        Scanner inputChoice = new Scanner(System.in);
        String userInput;
        
        System.out.println("\nTo continue, type one of the options below: \n"
                         + "1 Add more cows\n"
                         + "2 Remove a cow from the farm\n"
                         + "3 Print entire farm data\n"
                         + "4 Exit\n");
        System.out.print("Enter your choice: ");
        userInput = inputChoice.nextLine();
        
        try {
            
            switch (Integer.parseInt(userInput)){
        
            case 1:
              start();
              break; 
            case 2:
        
              endLifeSpan();
              break;
            case 3:
                
              printFarmData();  
              break;  
            case 4:  
               
              System.exit(0);
              break;
    }
            
            
        } catch (Throwable ex) {
            
            System.out.println("Please check your entry and try again");
            nextStage();
        }
      
        
  } 
    
    void endLifeSpan() {
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n");
        
        Scanner inputChoice = new Scanner(System.in);
        String removeCowID;
        System.out.println("\nEnter the ID of the cow to be removed from the farm's database: \n");
        removeCowID = inputChoice.nextLine();
        System.out.println("Farm's cow list before the removal");
        for (Cow cow : cowList ) {

            System.out.println("Cow ID: " + cow.getID() +  " Cow Name: " + cow.getName());

        }
        
        for (Cow cow : cowList){
            
            
            if (cow.getID().equals(removeCowID)) {
                
                cowList.remove(cow);
                cow.removed(true);
                break;
            }
            
        }
       System.out.println("\nFarm's cow list after the removal "); 
      for (Cow cow : cowList ) {

            System.out.println("Cow ID: " + cow.getID() +  " Cow Name: " + cow.getName());

        }
      nextStage();  
    }
    
    
    void printFarmData() {
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n\n\n");
        
         for (Cow cow : cowList) {
           
           System.out.println("Cow ID: " + cow.getID() +  " Cow Name: " + cow.getName() + " ");
           
       }
         
       for (Cow cow : cowList)   {
           
           cow.retrieveCalves();
       }
        
        nextStage();
       
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       
       SmartFarm farm = new SmartFarm();
       // Clear the screen
       System.out.print("\033[H\033[2J");  
       System.out.flush();  
       farm.start();
        
        
         
        
        
        
    }
    
}
