package com.hawk.webapi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import com.hawk.utility.JsonTools;



public abstract class ApiExecutor<T extends ApiRequest> {

	private Class<?> getSuperClassGenericType(Class<?> clazz) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
		} else if (type instanceof Class) {
			return getSuperClassGenericType((Class<?>) type);
		} else {
			return Object.class;
		}
	}

	@SuppressWarnings("hiding")
	protected abstract <T extends ApiRequest>  Object executeApi( T  apiRequest);

	
	public IResponse execute(RequestParmeter requestParmeter) {
		
		Map<String,Object> map  = requestParmeter.getRequestParam();

		String paramStr = null;
		
		if (map != null && map.size() > 0)
			paramStr = JsonTools.toJsonString(map);

		Class<?> clazz = getSuperClassGenericType(this.getClass());

		ApiRequest apiRequest = null;

		if (paramStr != null)
			apiRequest = JsonTools.toObject(paramStr, clazz);
		else
			apiRequest = new ApiRequest();

		

		apiRequest.setAppid(requestParmeter.getAppid());
		apiRequest.setOpenid(requestParmeter.getOpenid());
		apiRequest.setPassport(requestParmeter.getPassport());

		Object o = executeApi(apiRequest);
		SuccessResponse successResponse = SuccessResponse.buildResponse(o);
		return successResponse;
	}

}
