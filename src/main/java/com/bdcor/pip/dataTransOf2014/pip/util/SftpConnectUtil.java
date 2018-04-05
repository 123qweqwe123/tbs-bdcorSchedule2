package com.bdcor.pip.dataTransOf2014.pip.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpConnectUtil {

	private final static Logger logger = Logger.getLogger(SftpConnectUtil.class);
	
	ChannelSftp sftp = new ChannelSftp();
	
	/** 用户名  */
	private String username;
	/** 密码  */
	private String password;
	/** 主机  */
	private String host;
	/** 端口号  */
	private int port;
	
	/** 
	 * 构造函数 
	 * 
	 * @param host  主机 
	 * @param port  端口 
	 * @param username  用户名 
	 * @param password  密码 
	 */ 
	public SftpConnectUtil(String username, String password, String host,
			int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}
	
	/** 
	 * 连接sftp服务器 
	 * 
	 * @throws JSchException 
	 */ 
	public void connect() throws JSchException { 
		JSch jsch = new JSch(); 
		Session session = jsch.getSession(this.username, this.host, this.port);
		logger.debug(SftpConnectUtil.class + "Session created.");
		
		session.setPassword(password);
		Properties props = new Properties();
		props.put("StrictHostKeyChecking", "no");
		session.setConfig(props);
		session.connect(5000); // 毫秒
		logger.debug(SftpConnectUtil.class + "Session connected.");
		
		logger.debug(SftpConnectUtil.class + "Opening Channel.");
		sftp = (ChannelSftp) session.openChannel("sftp");
		sftp.connect(5000);
		this.sftp = (ChannelSftp) sftp;
		logger.debug(SftpConnectUtil.class + " Connected to " + this.host + "."); 
	}
	
	/** 
	 * 断开sftp服务器 
	 */ 
	public void disconnect() { 
		 if(this.sftp != null) {
			 if(this.sftp.isConnected()) {
				 this.sftp.disconnect();
			 } else if(this.sftp.isClosed()) {
				 logger.debug(SftpConnectUtil.class + " sftp is closed already."); 
			 }
		 }
	}
	
	/** 
	 * 下载单个文件
	 * 
	 * @param directory  下载目录
	 * @param downloadFile  下载的文件
	 * @param saveDirectory  存在本地的路径
	 * @return
	 */ 
	public void download(String directory, String downloadFile, String saveDir) {
		String saveFile = saveDir + "//" + downloadFile; 
		try {
			this.sftp.cd(directory);
			File file = new File(saveFile);
			this.sftp.get(downloadFile, new FileOutputStream(file)); 
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 下载单个文件
	 * 
	 * @param directory  下载目录
	 * @param downloadFile  下载的文件
	 * @param saveDirectory  存在本地的路径
	 * @return
	 */ 
	public byte[] download(String directory, String downloadFile) {
		ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
		try {
			this.sftp.cd(directory);
			
			this.sftp.get(downloadFile, outByteArray); 
		} catch (SftpException e) {
			e.printStackTrace();
		}
		
		return outByteArray.toByteArray();
	}
	
	public List<String> fileInDir(String dir) throws SftpException{
		List<String> rList = new ArrayList<String>();
		
		Vector v = this.sftp.ls(dir);
		for(Object o:v){
			LsEntry entry = (LsEntry)o;
			String fileName = entry.getFilename();
			if(!".".equals(fileName) && !"..".equals(fileName)){
				rList.add(fileName);
			}
			
			
		}
		
		return rList;
	}
	
	

	
	public static void main(String[] a) throws JSchException, SftpException{
		SftpConnectUtil util = new SftpConnectUtil("root","genertech","10.24.10.123",22);
		util.connect();
		
		
		util.disconnect();
	}
	
}
