package com.bdcor.pip.dataTransOf2014.pip.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;







import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.tools.ant.filters.StringInputStream;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
//import org.hibernate.mapping.Array;







import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
//import sun.net.ftp.FtpClient;
//import sun.net.ftp.FtpProtocolException;

public class FtpClientUtil {

	public FTPClient ftp = new FTPClient();

	private String ip; // "192.168.1.13"
	private int port;
	private String user; // "Anonymous"
	private String psw; // "IEUSER@"


	/**
	 * 连接Ftp
	 * 
	 * @param ip
	 * @param port
	 * @param user
	 * @param psw
	 */
	public void connectServer(String ip, int port, String user, String psw) {
		try {
			/*ftp.openServer(ip, port);
			ftp.login(user, psw);
			ftp.binary();*/
			/*
			ftp.connect(new InetSocketAddress(ip, port));
			ftp.login(user, null, psw);
			ftp.setBinaryType();*/
			ftp.connect(ip, port);
			
			//System.out.println(ftp.getReplyCode());
			boolean ret = ftp.login(user, psw);
			ftp.setDataTimeout(6000*10);
			ftp.setDefaultTimeout(6000*10);
			ftp.setSoTimeout(50000);
			//System.out.println(ret);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//ftp.enterLocalPassiveMode(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * closeServer 断开与ftp服务器的链接
	 */
	public void closeServer() {
		try {
			if (ftp != null) {
				/*ftp.closeServer();*/
				//ftp.close();
				try{
					//System.out.println(ftp.isRemoteVerificationEnabled());
					ftp.logout();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if ( ftp.isConnected())
					ftp.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public InputStream download(String path){
		InputStream is = null;
		try{
			ftp.setBufferSize(1024);  
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
			is = ftp.retrieveFileStream(path);// .getFileStream(path);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return is;
	}
	public byte[] downloadOfByte(String path) throws IOException{
		InputStream is = download(path);
		byte[] b = new byte[is.available()];
		is.read(b);
		return b;
	}



	/**
	 * 进入目录
	 * 
	 * @param dir
	 * @return
	 */
	public boolean cd(String dir) {
		boolean f = false;
		try {
			ftp.changeWorkingDirectory(dir);
		} catch (Exception e) {
			e.printStackTrace();
			return f;
		}
		return true;
	}

	public boolean isDirector(String director) {
		boolean f = true;
		try {
			ftp.changeWorkingDirectory(director);
		} catch (Exception e) {
			f = false;
		}
		return f;
	}


	/**
	 * 返回FTP目录下的文件列表
	 * 
	 * @param ftpDirectory
	 * @return
	 */
	public List<String> getFileNameList(String ftpDirectory) {
		List<String> list = new ArrayList<String>();
		try {
			FTPFile[] files = ftp.listFiles(ftpDirectory);
			
			for (FTPFile f : files ){
				if(f.isFile()){
					System.out.println("file");
				}
				
				if(f.isDirectory()){
					System.out.println("directory");
				}
				
				list.add(f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] a){
		String rootDir = "/001/";
		
		//String ip, int port, String user, String psw
		FtpClientUtil util = new FtpClientUtil();
		
		try {
			util.connectServer("10.24.10.123",21,"put","genertech");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		List<String> s = util.getFileNameList("/001/21/2102");
		for(String s1:s){
			System.out.println(s1);
		}
		util.closeServer();
		
	}
	
	/**
	 * 返回FTP目录下的文件列表MAP
	 * 
	 * @param ftpDirectory
	 * @return
	 */
	public Map<String, String> getFileNameMap(String ftpDirectory) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			FTPFile[] files = ftp.listFiles(ftpDirectory);
			for (FTPFile f : files ){
				map.put(f.getName(), f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

}
