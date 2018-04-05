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

public class LocalFileClientUtil {



	/**
	 * 连接Ftp
	 * 
	 * @param ip
	 * @param port
	 * @param user
	 * @param psw
	 */
	public void connectServer() {
		
	}

	/**
	 * closeServer 断开与ftp服务器的链接
	 */
	public void closeServer() {
		
	}




	public byte[] downloadOfByte(String path) throws IOException{
		return FileUtils.readFileToByteArray(new File(path));
	}






	/**
	 * 返回FTP目录下的文件列表
	 * 
	 * @param ftpDirectory
	 * @return
	 */
	public String[] getFileNameList(String ftpDirectory) {
		File f = new File(ftpDirectory);
		return f.list();
	}

	public static void main(String[] a){
		
	}
	


}
