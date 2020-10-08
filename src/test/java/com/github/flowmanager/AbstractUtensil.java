package com.github.flowmanager;

/**
 * This is the class that defines the factory
 * and the generic utensil.
 * Look at how it implements FactoryElement
 * and passed Abstract utensil to the generic parameter.
 * @author michele
 *
 */
public abstract class AbstractUtensil implements FactoryElement{ 

	// The member variable that each child of this class should
    // overwrite with the next element in the factory
    protected Class<? extends AbstractUtensil> nextClass = null;
    
    // This will return the next element in the factory
    public Class<? extends AbstractUtensil> getNextElement() {
        return  nextClass;
    }
    
    // The method that the children have to implement
    public abstract String getUse();
    
    // Here we test the factory
    public static void main(String[] args) {
        try{
            // Fist we create the beginning element
            AbstractUtensil pot = new Pot();
            // From that we can get the next element
            Class<? extends AbstractUtensil> clazz = pot.getNextElement();
            while(clazz != null) {
                // We loop through all the elements of the factory
                System.out.println(clazz.getName());
                AbstractUtensil next = clazz.newInstance();
                clazz = next.getNextElement();
            }
        } catch(Exception e) {e.printStackTrace();}
    }
}
