package com.bdcor.pip.dataTransOf2014.pip.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据与byte[]的相互转换，用于zookeeper数据队列
 * 适用于转换小量的数据，数据大有OutOfMemory的风险
 * */
public class DataUtils {
	
	private static final byte byteLength = 8;
	private static final int intLength = 4;
	private static final String defaultChartSet = "UTF-8";
	/** 
	 * 用于校验数据的头信息，防止非法数据注入
	 *  */
	private static final int headers = 0;
	
	/**
	 * 
	 * */
	public static byte[] toByteArray(Object obj) {
		
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 
	 * */
	public static Object toObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 将数据转换为byte[]
	 * @throws UnsupportedEncodingException 
	 * */
	public static byte[] map2byte(Map<String,String> map) throws UnsupportedEncodingException{
		byte[] ret = new byte[0];
		ret = byteMerger(ret , int2byteArray(headers));
		if ( map != null ){
			for ( String key : map.keySet()){
				byte[] kl = int2byteArray(key.length());
				byte[] kk = key.getBytes();
				byte[] vl = int2byteArray(map.get(key).length());
				byte[] vv  = map.get(key).getBytes(defaultChartSet);
				ret = byteMerger(ret , kl);
				ret = byteMerger(ret , kk );
				ret = byteMerger(ret , vl);
				ret = byteMerger(ret , vv );
			}
		}
		return ret;
	}
	
	/**
	 * 将byte[]还原城数据
	 * @throws UnsupportedEncodingException 
	 * */
	public static Map<String,String> byte2map(byte[] data) throws UnsupportedEncodingException{
		Map<String,String> ret = new HashMap<String,String>();
		if ( checkHeader(data) ){
			int curcor = intLength ;
			while ( curcor < data.length ){
				byte[] kl = new byte[intLength]; 
				System.arraycopy(data, curcor, kl , 0 , intLength);
				int klength = byteArray2int(kl);
				curcor += intLength;
				byte[] kk = new byte[klength];
				System.arraycopy(data, curcor, kk , 0 , klength);
				curcor += klength;
				byte[] vl = new byte[intLength]; 
				System.arraycopy(data, curcor, vl , 0 , intLength);
				int vlength = byteArray2int(vl);
				curcor += intLength;
				byte[] vv = new byte[vlength];
				System.arraycopy(data, curcor, vv , 0 , vlength);
				curcor += vlength;
				ret.put(new String(kk,defaultChartSet), new String(vv,defaultChartSet));
			}
		}
		return ret;
	}
	
	/**
	 * check header
	 * */
	private static boolean checkHeader(byte[] data){
		if ( data != null && data.length > intLength){
			byte[] tb = new byte[4];
			System.arraycopy(data, 0, tb , 0 , intLength);
			int h = byteArray2int(tb);
			if ( h == headers ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * byte[]合并
	 * */
	public static byte[] byteMerger(byte[] b1, byte[] b2) {
		byte[] ret = new byte[b1.length + b2.length];
		System.arraycopy(b1, 0, ret, 0, b1.length);
		System.arraycopy(b2, 0, ret, b1.length, b2.length);
		return ret;
	}
	
	/**
	 * 将4字节的byte转化为int
	 * */
	public static int byteArray2int(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (byte b : data) {
			String hex = Integer.toHexString(b & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		BigInteger bi = new BigInteger(sb.toString(), 16);
		return bi.intValue();
	}
	
	/**
	 * 将int转化为4字节的byte
	 * */
	public static byte[] int2byteArray(int number)  
    {  
        byte[] byteArray = new byte[intLength];   
  
        int shiftNum = 0;   // 移位数  
        for(int i=0; i<intLength; i++)  
        {  
            shiftNum = (intLength-i-1)*byteLength;  
            byteArray[i] = (byte)((number >> shiftNum) & 0xFF);  
        }  
        return byteArray;  
    }  
	
	public static final byte[] input2byte(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	public static final String input2String(InputStream inStream) throws UnsupportedEncodingException, IOException{
		return new String(input2byte(inStream),defaultChartSet);
	}
	
}
