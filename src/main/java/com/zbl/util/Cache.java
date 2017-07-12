package com.zbl.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 缓存模板类

 * 
 * @author 
 * @date 
 */
public abstract class Cache {
	protected static AppLog log = new AppLog(Cache.class);

	// 保存所有缓存实例的map
	private static Map instanceMap = new HashMap();

	private Map cacheMap = new HashMap();
	
	//是否打开缓存开关

	private boolean cacheEnabled;
	
	

	/**
	 * @return the cacheEnabled
	 */
	public boolean isCacheEnabled() {
		return cacheEnabled;
	}

	/**
	 * @param cacheEnabled the cacheEnabled to set
	 */
	public void setCacheEnabled(boolean cacheEnabled) {
		this.cacheEnabled = cacheEnabled;
	}

	/**
	 * 工厂方法，获得缓存实例

	 * 
	 * @param clazz
	 * @return
	 */
	public static Cache getInstance(Class clazz,boolean cacheEnable) {
		synchronized (instanceMap) {
			Cache instance = (Cache) instanceMap.get(clazz.getName());
			if (instance == null) {
				instance = (Cache) newInstanceWithPrivateConstructor(clazz);
				instanceMap.put(clazz.getName(), instance);
				log.debug("建立缓存实例：" + instance);
			}
			instance.setCacheEnabled(cacheEnable);
			return instance;
		}
	}
	public static Cache getInstance(Class clazz) {
		synchronized (instanceMap) {
			Cache instance = (Cache) instanceMap.get(clazz.getName());
			if (instance == null) {
				instance = (Cache) newInstanceWithPrivateConstructor(clazz);
				instanceMap.put(clazz.getName(), instance);
				log.debug("建立缓存实例：" + instance);
			}
			instance.setCacheEnabled(true);
			return instance;
		}
	}

	/**
	 * 从缓存中加载缓存的对象

	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		log.debug("从缓存实例[" + this + "]中加载 key = " + key);

		synchronized (cacheMap) {
			Object val = cacheMap.get(key);
			if ( !cacheEnabled || val == null) {
				// 加载
				log.debug("尚未缓存的键：" + key);
				val = load(key);

				// 放入缓存
				cacheMap.put(key, val);
			}
			return val;
		}

	}

	/**
	 * 清空缓存
	 * 
	 * @param clazz
	 */
	public static void clear(Class clazz) {
		Cache instance = (Cache) instanceMap.get(clazz.getName());
		if (instance != null) {
			instance.cacheMap.clear();
			log.debug("已经清空" + clazz.getName() + "缓存");
		} else {
			log.warn("尚未建立缓存：" + clazz.getName());
		}
	}

	/**
	 * 清空所有缓存

	 */
	public static void clearAll() {
		for (Iterator it = instanceMap.keySet().iterator(); it
				.hasNext();) {
			String className = (String) it.next();
			Cache instance = (Cache) instanceMap.get(className);
			instance.cacheMap.clear();
			log.debug("已经清空" + className + "缓存");
		}
	}

	/**
	 * 根据key，加载对应的value，用户缓存

	 * 
	 * @param key
	 * @return
	 */
	abstract protected Object load(Object key);

	private static Object newInstanceWithPrivateConstructor(Class clazz) {
		try {
			Constructor constructor = clazz.getDeclaredConstructor(new Class[]{});
			int modi = constructor.getModifiers();
			if (modi != Modifier.PRIVATE) {
				throw new Exception("缓存对象默认构造方法必须是私有的.");
			}
			constructor.setAccessible(true);
			Object o = constructor.newInstance(new Object[] {});
			if (o instanceof Cache) {
				return o;
			} else {
				throw new Exception("指定的class不是Cache的子类: " + clazz.getName());
			}
		} catch (Exception e) {
			log.error("生成新的缓存实例出错: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
