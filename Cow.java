
package smartfarm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;


/**
 *
 * @author Emin
 */
public class Cow {
   
    ArrayList<Map> allCowCalves = new  ArrayList();
    Map<String,String> calfDetails = new HashMap();
    MultiValuedMap<String, Map> cowDetails = new ArrayListValuedHashMap<>();
    Map<String, String> cowParent = new HashMap();
    Map<String, String> cowIDName = new HashMap();
    Map<String, String> calfList = new HashMap(); 
    Collection set;  
    Iterator itr;
    String cowID, cowName, calfID, calfName;
    String parentID, parentName;
    Map.Entry cowData;
    private boolean cowReomoved;
   
    public Cow(String parentID, String parentName, String cowID, String cowName, String calfID, String calfName) {
         
          
          this.setNameID(cowID, cowName);
          this.setCowsParent(parentID, parentName);
          
    }
     
    
   public Cow(String parentID, String parentName, String cowID, String cowName) {
         
          
          this.setNameID(cowID, cowName);
          this.setCowsParent(parentID, parentName);
          
    }
    
    
    
   public Cow(String cowID, String cowName) {
        
          this.setNameID(cowID, cowName);
          
    }
    
   public void removed(boolean removed) {
      
     this.cowReomoved = removed;
  }
   
   
  boolean getRemovedInfo() {
      
      return this.cowReomoved;
  }
  
 private void setNameID(String cowID, String cowName) {
    
     this.cowIDName.put(cowID, cowName);
  }
  
  public String getName() {
      
      retrieveCowData(cowIDName);
      return this.cowName;

  }
  
  
  public void setID(String cowID) {
    
      this.cowID = cowID;
 
  }
  
  public String getID() {
    
      retrieveCowData(cowIDName);
      return this.cowID;
 
  }
  
  public void giveBirth(String cowID, String calfID,  String calfName ) {
          
        calfList.put(calfID, calfName);
        cowDetails.put(cowID, calfList);
 }
    
    
  
    
 public void retrieveCowData(Map cowIDName) {
    
     set = cowIDName.entrySet();
     itr =  set.iterator();
     
     while (itr.hasNext()) {
         
         cowData = (Map.Entry) itr.next();
         this.cowID = (String) cowData.getKey();
         this.cowName = (String) cowData.getValue();
         
         
     }
  
 }
  
  
  public void retrieveCalves(){
        
        set = cowDetails.entries();
        itr = set.iterator();  
      
        if(itr.hasNext()) {   
            
            //Converting to Map.Entry so that we can get value separately  
            cowData  = (Map.Entry)itr.next();
            cowID = (String) cowData.getKey();
            
            calfDetails = (HashMap) cowData.getValue();
            calfDetails.remove("");
         }  
        
        allCowCalves.add(calfDetails);
        for (Map calfData : allCowCalves) {
           
            if (!calfData.isEmpty()) {
                
                 System.out.println("\nCOW WITH THE ID : " + cowID + " has given birth to following calves:" );
          
                 set = calfData.entrySet();
                 itr = set.iterator();  
      
                 while(itr.hasNext()) {   
                   
                      cowData  = (Map.Entry)itr.next();
                      cowID = (String) cowData.getKey();
                      calfName =
                              (String) cowData.getValue();
                      System.out.println("ID: " + cowID + " Name: " + calfName );
                 }
         
        }
       
   }
   
        allCowCalves.clear();
}
    
    final protected void setCowsParent(String parentID, String parentName) {
        
      cowParent.put(parentID,parentName);
        
    }

   public String getParentID() {
        
        retrieveParentData(cowParent);
        return this.parentID;
    }

    protected String getParentName() {
        
        retrieveParentData(cowParent);
        return this.parentName;
    }
    
    void retrieveParentData(Map map) {
        
        set = map.entrySet();
        itr = set.iterator();  
        //Converting to Map.Entry so that we can get key separately 
       try {
           
           cowData =(Map.Entry)itr.next();
           this.parentID = (String) cowData.getKey();
           this.parentName = (String) cowData.getValue();
           
       } catch (NoSuchElementException e) {
           
           this.parentID = null;
           
       }
        
        
    }
    
    
    
}
