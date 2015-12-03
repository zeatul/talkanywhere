package com.hawk.pub.web;

import java.util.Collection;
import java.util.List;

import com.hawk.utility.JsonTools;

public class SuccessResponse implements IResponse{
	
	public Object getResult() {
		return result;
	}

	public final static SuccessResponse SUCCESS_RESPONSE = new SuccessResponse(null);

	private Object result;
	
	protected SuccessResponse(Object value){
		this.result = value;
	}
	
	public static class OutPut{
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		private String code;
		private Object data;
	}
	
	public String toJson(){

		OutPut rtn = new OutPut();
		rtn.setCode("1");
		rtn.setData(result);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}
	

	/**
	 * 构建单实体对象
	 * @param value
	 * @return
	 */
	public static SuccessResponse build(Object value){
		SuccessResponse response = new SuccessResponse(value);
		return response;
	}
	
	public static  class CollectData<K> {
		
		public Collection<K> getValues() {
			return values;
		}
		public void setValues(Collection<K> values) {
			this.values = values;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		private Collection<K> values;
		private int size;
	}
	
	/**
	 * 构建集合对象 ,size = 集合size ，这个说明是查询的全数据的大小
	 * @param values
	 * @return
	 */
	public static <K> SuccessResponse build(Collection<K> values){
		CollectData<K> c = new CollectData<K>();
		c.setValues(values);
		c.setSize(values.size());
		SuccessResponse response = new SuccessResponse(c);
		return response;
		
	}
	
	/**
	 * 构建集合对象 ，分页查询时，返回部分结果，和总数据量大小
	 * @param values 数据集合
	 * @param size 数据库内的符合查询条件的数据大小
	 * @return
	 */
	public static <K> SuccessResponse build(Collection<K> values ,int size){
		CollectData<K> c = new CollectData<K>();
		c.setValues(values);
		c.setSize(size);
		SuccessResponse response = new SuccessResponse(c);
		return response;
		
	} 
	
}
