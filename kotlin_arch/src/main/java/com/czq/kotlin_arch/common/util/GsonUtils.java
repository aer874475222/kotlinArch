package com.czq.kotlin_arch.common.util;

import com.google.gson.Gson;

public final class GsonUtils {

	private static class GsonHolder{
		private static final Gson INSTANCE = new Gson();
	}

	/**
	 * 获取Gson实例，由于Gson是线程安全的，这里共同使用同一个Gson实例
	 */
	public static Gson getGson() {
		return GsonHolder.INSTANCE;
	}

	public static <T> T parseJSON(String json, Class<T> clazz) {
		T info = getGson().fromJson(json, clazz);
		return info;
	}


	public static <T> String toJson(@androidx.annotation.NonNull T t) {
		String jsonStr = getGson().toJson(t);
		return  jsonStr;
	}

	public static <T> String toJson(@androidx.annotation.NonNull T t, java.lang.reflect.Type typeOfSrc) {
		String jsonStr = getGson().toJson(t,typeOfSrc);
		return  jsonStr;
	}

	/**
	 * Type type = new 
			TypeToken&lt;ArrayList&lt;TypeInfo>>(){}.getType();
	   <br>Typeava.lang.reflect
	   <br>Tcom.google.gson.reflect.TypeToken
	 * @param //json
	 * @param type
	 * @return
	 */
	public static <T> T parseJSONArray(String jsonArr, java.lang.reflect.Type type) {
		T infos = getGson().fromJson(jsonArr, type);
		return infos;
	}

	public static <T> java.util.List<T> parseString2List(String json, Class clazz) {
		java.lang.reflect.Type type = new ParameterizedTypeImpl(clazz);
		java.util.List<T> list =  getGson().fromJson(json, type);
		return list;
	}

	private static class ParameterizedTypeImpl implements java.lang.reflect.ParameterizedType {
		Class clazz;

		public ParameterizedTypeImpl(Class clz) {
			clazz = clz;
		}

		@Override
		public java.lang.reflect.Type[] getActualTypeArguments() {
			return new java.lang.reflect.Type[]{clazz};
		}

		@Override
		public java.lang.reflect.Type getRawType() {
			return java.util.List.class;
		}

		@Override
		public java.lang.reflect.Type getOwnerType() {
			return null;
		}
	}


}
