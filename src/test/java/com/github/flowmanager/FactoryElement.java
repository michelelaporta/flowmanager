package com.github.flowmanager;

/**
 * This is a generic interface that you can use to create any Factory Chain. 
 */
public interface FactoryElement {

	/**
	 * This method will always return a sub class of the generic definition 
	 * @return
	 */
    public Class<? extends AbstractUtensil> getNextElement();
}
