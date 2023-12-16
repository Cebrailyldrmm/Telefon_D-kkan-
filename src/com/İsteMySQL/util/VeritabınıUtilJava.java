package com.İsteMySQL.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.math.*;

public class VeritabınıUtilJava {

	
	static Connection conn=null;
	
	public static Connection Baglan() 
	{
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/telefon","root", null);
			return conn;
		}
	catch (Exception e) 
		{
		System.out.println(e.getMessage().toString());
		return null;
		}
	} 
	
	public static String MD5Sifrele(String içerik)
	{
		
		try
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] sifrelenmis=md.digest(içerik.getBytes());
			BigInteger no=new BigInteger(1,sifrelenmis);
			String hashIcerik=no.toString(16);
			while(hashIcerik.length()<32)
			{
				hashIcerik="0"+hashIcerik;
			}
			return hashIcerik;
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		
		
	}
}
