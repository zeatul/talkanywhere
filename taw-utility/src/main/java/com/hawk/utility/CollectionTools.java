package com.hawk.utility;

import java.util.Collection;

public class CollectionTools {
	
	public static  boolean isNullOrEmpty(Collection<?> collections){
		if (collections == null)
			return true;
		
		if (collections.size() == 0)
			return true;
		
		return false;
	}

}
