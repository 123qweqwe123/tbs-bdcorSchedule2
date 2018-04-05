/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * $Id: EncodeUtils.java 1211 2010-09-10 16:20:45Z calvinxiu $
 */
package com.bdcor.pip.dataTransOf2014.pip.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Encoder;

/**
 * 各种格式的编码加码工具类.
 *
 * 集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.
 *
 * @author calvin 
 */
@SuppressWarnings("restriction")
public class EncodeUtils {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String input) {
		try {
			return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String input) {
		try {
			return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

    /**
     * MD5 加密
     * @param input
     * @return
     */
	public static String MD5Encode(String input) {
		//确定计算方法
		MessageDigest md5;
	    String newstr = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		    BASE64Encoder base64en = new BASE64Encoder();
		    //加密后的字符串
			newstr = base64en.encode(md5.digest(input.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return newstr;
    }
	
	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);  
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	public static String getMD5ByFileIO(File file){
		String ret = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			in = new FileInputStream(file);  
			out = new ByteArrayOutputStream((int)file.length());  
			byte[] cache = new byte[1048576];  
			for(int i = in.read(cache);i != -1;i = in.read(cache)){  
			  out.write(cache, 0, i);  
			} 
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(out.toByteArray());  
			ret = hexEncode(md5.digest());
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if ( in != null ){
				try{
					in.close();  
				}catch(Exception e){
					
				}
			}
			if ( out != null ){
				try{
					out.close();
				}catch(Exception e){
					
				}
			}	
		}
		return ret;
	}
}
