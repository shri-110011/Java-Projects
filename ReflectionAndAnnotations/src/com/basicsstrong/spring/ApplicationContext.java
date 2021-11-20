package com.basicsstrong.spring;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.basicsstrong.annotation.Autowired;
import com.basicsstrong.annotation.Component;
import com.basicsstrong.annotation.ComponentScan;
import com.basicsstrong.annotation.Configuration;

public class ApplicationContext {

	private static HashMap<Class<?>, Object> map = new HashMap<>();
	
	public ApplicationContext(Class<AppConfig> clss) {
		
		Spring.initializeSpringContext(clss);
		
	}
	
	private static class Spring {
		
		private static void initializeSpringContext(Class<?> clss)  {
			
			if(!clss.isAnnotationPresent(Configuration.class)) {
				
			}
			else {
				ComponentScan annotation = clss.getAnnotation(ComponentScan.class);
				String value = annotation.value();
				String packageStructure = "bin/"+value.replace(".", "/");
				
				System.out.println(packageStructure);
				
				File[] files = findClasses(new File(packageStructure));
				
				for(File file: files) {
					
					String className = value+"."+file.getName().replace(".class", "");
					try {
						Class<?> loadingClass = clss.forName(className);
						
						if(loadingClass.isAnnotationPresent(Component.class)) {
							
							Constructor<?> constructor = loadingClass.getConstructor();
							
							Object object = constructor.newInstance();
							
							map.put(loadingClass, object);
							
							
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
					
			}
		}

		private static File[] findClasses(File file) {
			
			if(!file.exists()) {
				throw new RuntimeException("The package "+file.getAbsolutePath()+" doesn't exist");
			}
			else {
				
				File[] files = file.listFiles(e-> e.getName().endsWith(".class"));
			
				return files;
			}
			
		}
				
	}

	public <T> T getBean(Class<T> clss) {
		
		T object = (T)map.get(clss);
		 
		Field[] declaredFields = clss.getDeclaredFields();
		
		injectBean(object, declaredFields);
		
		return object;
	}

	private <T> void injectBean(T object, Field[] declaredFields) {
	
		for(Field field: declaredFields) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(Autowired.class)) {
				
				Class<?> type = field.getType();
				Object innerObject = map.get(type);
				
				try {
					field.set(object, innerObject);
					
					Field[] declaredFields2 = type.getDeclaredFields();
					
					injectBean(innerObject, declaredFields2);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
		
}
