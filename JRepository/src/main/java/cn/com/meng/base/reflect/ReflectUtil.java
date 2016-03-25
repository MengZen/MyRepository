package cn.com.meng.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import cn.com.meng.base.example.bean.User;

/**
 * 反射工具类
 * 
 * @author meng
 *
 */
public class ReflectUtil {

	/**
	 * 利用反射，获取并打印指定对象的所有属性值
	 * 
	 * @param clz
	 */
	public static void printBean(Object clz) {
		Field fields[] = clz.getClass().getDeclaredFields();// 获得对象所有属性
		for (Field field : fields) {
			field.setAccessible(true);// 修改访问权限
			try {
				System.out.println(field.getName() + ":" + field.get(clz) + ":" + field.getType().getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 利用反射机制根据字符串创建实例对象
	 * 
	 * @param className
	 * @return
	 */
	public static Object newInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * 利用反射机制根据对象类型创建实例对象
	 * 
	 * @param clz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clz) {
		try {
			return (T) clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * 利用反射机制根据对象类型创建实例对象
	 * 
	 * @param clz
	 * @return
	 */
	public static Object newInstance2(Class<?> clz) {
		try {
			return clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		User user = (User) ReflectUtil.newInstance("cn.com.meng.base.example.bean.User");
		User user = (User) ReflectUtil.newInstance(User.class);
		user.setName("小明");
		user.setAge(12);
		ReflectUtil.printBean(user);
		//获取包
		Package package1 = User.class.getPackage();
		System.out.println("Package＝" + package1.getName());
		//获取修饰符
		int mod = User.class.getModifiers();
		String modifier = Modifier.toString(mod);
		System.out.println("modifier=" + modifier);
		//获取指定类的父类
		Class<?> superClazz = User.class.getSuperclass();
		String superClazzName = superClazz.getName();
		System.out.println("superClass=" + superClazzName);
		//获取实现的接口
		Class<?>[] interfaces = User.class.getInterfaces();
		for (int i = 0 ; i < interfaces.length; i++) {
			System.out.println("interfaces" + (i+1) + "=" + interfaces[i].getName());
		}
		//获取构造方法
		System.out.println();
		Constructor<?>[] constructors = User.class.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			String name = constructor.getName(); //构造方法名
			Class<?>[] paramTypes = constructor.getParameterTypes();
			System.out.print("Constructor=" + name + "(");
			for(Class<?> param : paramTypes){
				System.out.print(param.getName()+",");
			}
			System.out.println(")");
		}
		//获取方法
		Method[] methods = User.class.getDeclaredMethods();
		System.out.println();
		for (Method method: methods) {
			System.out.print(method.getName()+":"+Modifier.toString(method.getModifiers()));
			//获取方法的返回类型
			Class<?> returnType = method.getReturnType(); 
			System.out.println(":"+returnType.getName());
			//反射调用方法
			if("setName".equals(method.getName())){
				method.invoke(user, "小红");
			}
		}
		System.out.println(user.getName());
	}
}
