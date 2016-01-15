package com.hawk.utility;

import java.util.Collection;

public class CollectionTools {
	
	public static  boolean isNullOrEmpty(Collection<?> collection){
		if (collection == null)
			return true;
		
		if (collection.size() == 0)
			return true;
		
		return false;
	}
	
	public static  boolean isNotNullOrEmpty(Collection<?> collection){
		return ! isNullOrEmpty(collection);
	}

}
