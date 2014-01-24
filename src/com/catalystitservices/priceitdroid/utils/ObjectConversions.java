package com.catalystitservices.priceitdroid.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



public class ObjectConversions {
	public static byte[] serializeToByte(Object o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.flush();
		oos.close();
		return baos.toByteArray();
	}
	
	
	
}
