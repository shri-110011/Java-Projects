package com.basicsstrong.practice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.basicsstrong.annotation.Column;
import com.basicsstrong.annotation.PrimaryKey;

public class Hibernate<T> {
	
	Connection con;
	
	AtomicLong id = new AtomicLong(0L);
	
	public static <T> Hibernate<T> getConnection() throws SQLException {
		return new Hibernate<T>();
	}
	
	private Hibernate() throws SQLException {
		this.con = DriverManager.getConnection("jdbc:h2:D:\\Java EE Workspace\\ReflectionAndAnnotations\\database\\practice1", "sa", "");
	}

	public void write(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Class <? extends Object> clss =  t.getClass();
		
		Field[] fields = clss.getDeclaredFields();
		
		Field pKey = null;
		ArrayList<Field> columns = new ArrayList<>();
		
		StringJoiner joiner = new StringJoiner(",");
		
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				System.out.println("The Primary Ley is: "+field.getName());
				pKey = field;
			}
			else if(field.isAnnotationPresent(Column.class)) {
				System.out.println(field.getName()+" value: "+field.get(t));
				columns.add(field);
				joiner.add(field.getName());
			}
		}
		
		int number = columns.size() + 1;
		
		String qMarks = IntStream.range(0, number)
		.mapToObj(e-> "?")
		.collect(Collectors.joining(","));
		
		String sql = "insert into "+clss.getSimpleName()+" (" + pKey.getName()+ "," + joiner.toString() + ")"+" values ("+qMarks+");";
	
		System.out.println(sql);
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		System.out.println(stmt);
		
		if(pKey.getType()==long.class) {
			stmt.setLong(1,  id.addAndGet(number));
		}
		
		int index = 2;
		for(Field field : columns) {
			if(field.getType()==int.class) {
				stmt.setInt(index++, field.getInt(t));
			}
			if(field.getType()==String.class) {
				stmt.setString(index++, ""+field.get(t));
			}
			if(field.getType()==double.class) {
				stmt.setDouble(index++, field.getDouble(t));
			}
			System.out.println(index+" "+field.getType());
		}
		
		stmt.executeUpdate();
		
	}

	public T read(Class<T> clss, long l) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		
		Field[] fields = clss.getDeclaredFields();
		
		Field pKey = null;
		
		for(Field field:fields) {
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				pKey = field;
				break;
			}
		}
		
		String sql = "select * from "+clss.getSimpleName()+" where "+pKey.getName()+" = "+l+";";
		
		System.out.println(sql);
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		T t = clss.getConstructor().newInstance();
		
		long transactionId = rs.getLong(pKey.getName());
		pKey.setAccessible(true);
		pKey.set(t, transactionId);
		
		for(Field field: fields) {
			if(field.isAnnotationPresent(Column.class)) {
				field.setAccessible(true);
				if(field.getType() == int.class) {
					field.set(t, rs.getObject(field.getName()));
				}
				if(field.getType() == String.class) {
					field.set(t,  rs.getObject(field.getName()));
				}
				if(field.getType() == double.class) {
					field.set(t,  rs.getObject(field.getName()));
				}
			}
		}
		return t;
	}
}
