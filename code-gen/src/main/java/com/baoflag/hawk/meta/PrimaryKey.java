package com.baoflag.hawk.meta;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKey {
	
	private List<Column> columns = new ArrayList<Column>();

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
