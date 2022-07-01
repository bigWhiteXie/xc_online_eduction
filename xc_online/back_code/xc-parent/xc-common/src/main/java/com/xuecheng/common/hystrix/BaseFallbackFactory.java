package com.xuecheng.common.hystrix;

import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.lang.reflect.*;

public class BaseFallbackFactory<T, N extends T, E extends T> implements FallbackFactory<T> {

	@Autowired
	private Environment environment;

	@Override
	public T create(Throwable throwable) {
		try {
			Type superClass = getClass().getGenericSuperclass();
			String fallbackProfile = environment.getProperty("feign.fallback.profile");
			if (StringUtils.isNotBlank(fallbackProfile) && fallbackProfile.equals("dev")) {
				Type type = ((ParameterizedType) superClass).getActualTypeArguments()[2];
				Class<?> clazz = getRawType(type);
				if (clazz.getConstructors().length == 0) {
					return (E) clazz.newInstance();
				} else {
					Constructor ct = clazz.getDeclaredConstructor(Throwable.class);
					return (E) ct.newInstance(throwable);
				}
			} else {
				Type type = ((ParameterizedType) superClass).getActualTypeArguments()[1];
				Class<?> clazz = getRawType(type);
				Constructor ct = clazz.getDeclaredConstructor(Throwable.class);
				return (N) ct.newInstance(throwable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * type不能直接实例化对象，通过type获取class的类型，然后实例化对象
	 * @param type
	 * @return
	 */
	private Class<?> getRawType(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type rawType = parameterizedType.getRawType();
			return (Class) rawType;
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			return Array.newInstance(getRawType(componentType), 0).getClass();
		} else if (type instanceof TypeVariable) {
			return Object.class;
		} else if (type instanceof WildcardType) {
			return getRawType(((WildcardType) type).getUpperBounds()[0]);
		} else {
			String className = type == null ? "null" : type.getClass().getName();
			throw new IllegalArgumentException(
					"Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type "
							+ className);
		}
	}
}



