package com.bdcor.pip.dataTransOf2014.handlerUqs2014;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.pip.dataTransOf2014.DbOption;
import com.bdcor.pip.dataTransOf2014.pip.data.domain.PipUqsHandleLog2014;
import com.bdcor.pip.dataTransOf2014.pip.util.CryptoUtil;
import com.bdcor.pip.dataTransOf2014.pip.util.DataUtils;
import com.bdcor.pip.dataTransOf2014.pip.util.LocalFileClientUtil;
import com.kszit.util.ssh2upload.SSH2UploadFileUtil;
import com.jcraft.jsch.JSchException;


public class FindLostBinding4 {
	private static Logger log = LoggerFactory.getLogger(FindLostBinding4.class);
	
	/**
	 * 
	 * 参数：开始时�?  结束时间  lcc编号
	 * 
	 * 查找已经上传的pation数据，id绑定初筛或�?�初筛绑定高�? 未绑定上的数�?
	 * 
	 * 
	 * @param args  
	 * @throws Exception 
	 */
	public static void find(String startdate,String enddate,String lcc){

		DbOption db = new DbOption();
		
		String rootDir = "/var/put/001/";
		//String rootDir = "var/put/001/";
		
		//String ip, int port, String user, String psw
		LocalFileClientUtil util = new LocalFileClientUtil();
	
		
		

		//省列�?
		List<String> provinceDirList = new ArrayList<String>();
		if(StringUtils.isBlank(lcc)){
			try {
				for(String provinceCode:util.getFileNameList(rootDir)){
					log.info("添加"+rootDir+"下的文件�?");
					provinceDirList.add(rootDir+provinceCode+"/");
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}else{
			//省编�?
			String provinceCode = lcc.substring(0,2);
			provinceDirList.add(rootDir+provinceCode+"/");
			log.info("添加文件�?:"+rootDir+provinceCode);
		}
		
		
		//lcc 列表
		List<String> lccDirList = new ArrayList<String>();
		for(String lccRootDir:provinceDirList){
			
			
			if(StringUtils.isBlank(lcc)||lcc.length()==2){//lcc为空或�?�lcc为两位时，添加所�?
				try {
					for(String lccCode:util.getFileNameList(lccRootDir)){
						lccDirList.add(lccRootDir+lccCode+"/");
						log.info("添加文件夹："+lccRootDir+lccCode+"/");
					}
				} catch (Exception e) {
					log.error(e.getMessage(),e);
				}
			}else if(lcc.length()==4){
				try {
					for(String lccCode:util.getFileNameList(lccRootDir)){
						if(lccCode.startsWith(lcc)){
							lccDirList.add(lccRootDir+lccCode+"/");
							log.info("添加文件夹："+lccRootDir+lccCode+"/");
						}
					}
				} catch (Exception e) {
					log.error(e.getMessage(),e);
				}
			}else{
				lccDirList.add(lccRootDir+lcc+"/");
				log.info("添加文件夹："+lccRootDir+lcc+"/");
			}
		}
		
		
		//日期列表
		List<String> dateAndLogDirList = new ArrayList<String>();
		for(String dateRootDir:lccDirList){
			try {
				for(String date:util.getFileNameList(dateRootDir)){
					Date dateD = DateUtils.parseDate(date, "yyyyMMdd");
					
					if(StringUtils.isNotBlank(startdate) && StringUtils.isNotBlank(enddate)){
						try{
							Date startD = DateUtils.parseDate(startdate, "yyyyMMdd");
							Date endD = DateUtils.parseDate(enddate, "yyyyMMdd");
							if(dateD.getTime()>=startD.getTime() && dateD.getTime()<=endD.getTime()){
								dateAndLogDirList.add(dateRootDir+date+"/UQS/");
								log.info("添加文件�?:"+dateRootDir+date+"/UQS/");
							}
						}catch(Exception e){
							log.error(e.getMessage(),e);
						}
					}else{
						dateAndLogDirList.add(dateRootDir+date+"/UQS/");
						log.info("添加文件�?:"+dateRootDir+date+"/UQS/");
					}
				}
			} catch (ParseException e) {
				log.error(e.getMessage(),e);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		
		//日志压缩包列�?
		List<String> zipLogFiles = new ArrayList<String>();
		for(String logDir:dateAndLogDirList){
			log.info("查看"+logDir+"下的文件====>");
			try {
				for(String logZipFile:util.getFileNameList(logDir)){
					if(logZipFile.endsWith(".zip")){
						zipLogFiles.add(logDir+logZipFile);
						log.info("***添加文件:"+logDir+logZipFile);
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}

		
		
		for(String logZipFile:zipLogFiles){
			log.info(Thread.currentThread().getName()+":::压缩:"+logZipFile+"=======>");
			//sb.append(":::压缩�?:"+logZipFile+"=======>");
			byte[] byteData = null;
			try {
				
				byteData = util.downloadOfByte(logZipFile);
				
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			
			if(byteData==null){
				log.info("zip 包存�?");
				continue;
			}
			
			
		
			//===============================================================================执行数据查找
			
			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(byteData));
			try{
				ZipEntry entry = zis.getNextEntry();;
				while ( entry != null){
					String logFileName = entry.getName();
					String lccCode = logZipFile.split("/")[5];
					//String lccCode = "2203";
					//log.info("xml文件:" + logFileName);
					if(logFileName.contains("IDCORDS")){
						entry = zis.getNextEntry();
						continue;
					}
					//sb.append("zip包名�?:"+logZipFile+",xml文件:" + logFileName+"<br>");
					
					SAXReader saxReader = new SAXReader();
			    	Document document = null;
			    	try{
			    		byte[] enput = null;
			    		String s  = new String(DataUtils.input2byte(zis),"UTF-8");
			    		if(s!=null && s.startsWith("<?xml ")){
			    			enput = s.getBytes("UTF-8");
			    		}else{
			    			
			    			enput = CryptoUtil.decrypt(lccCode,s).getBytes("UTF-8");
			    		}
			    		
			    		document = saxReader.read(new ByteArrayInputStream(enput));
			    	}catch(Exception ex){
			    		log.error("文件解密错误",ex);
			    		
			    		try{
							entry = zis.getNextEntry();
						}catch(Exception e){
							log.error(e.getMessage(),e);
							//entry = zis.getNextEntry();
							entry = null;
						}
			    		
			    		continue;
			    	}
			    	if(document.getRootElement()!=null){
			    		Element doctype = document.getRootElement().element("doctype");
			    		/**
			    		 * <doctype ProjectID="001" ProjectName="心血管病高危人群早期筛查与综合干预项目" 
			    		 * UQSID="001006"  UQSName="随访调查表" 
			    		 * UQSVersion="001.006.001" VersionCreateDate="2014-08-15 14:07:01" 
							  UQSBeginTime="2015-07-13 16:23:42" UQSEndDate="2015-07-13 16:25:27" 
							  UQSTimeZone="" OperaterID="US0000000001159" 
							  OperaterName="luoting" HospitalCode="" 
							  HospitalName="" PatientID="" 
							  PersonID="" 
							  PatientName="" PatientCode="G330241456" 
							  UQSIsHold="2" UQSRemark=""/>
			    		 */
			    		String ProjectID = doctype.attributeValue("ProjectID");
			    		String ProjectName = doctype.attributeValue("ProjectName");
			    		String UQSID = doctype.attributeValue("UQSID");
			    		String UQSName = doctype.attributeValue("UQSName");
			    		String UQSVersion = doctype.attributeValue("UQSVersion");
			    		String VersionCreateDate = doctype.attributeValue("VersionCreateDate");
			    		String uqsBeginTime = doctype.attributeValue("UQSBeginTime");
						String uqsEndDate = doctype.attributeValue("UQSEndDate");
						String uqsTimeZone = doctype.attributeValue("UQSTimeZone");
						String OperaterID = doctype.attributeValue("OperaterID");
						String OperaterName = doctype.attributeValue("OperaterName");
						String HospitalCode = doctype.attributeValue("HospitalCode");
						String PatientID = doctype.attributeValue("PatientID");
						String PersonID = doctype.attributeValue("PersonID");
						String PatientName = doctype.attributeValue("PatientName");
						String PatientCode = doctype.attributeValue("PatientCode");
						String UQSIsHold = doctype.attributeValue("UQSIsHold");
						String UQSRemark = doctype.attributeValue("UQSRemark");
						
						PipUqsHandleLog2014 record = new PipUqsHandleLog2014();
						record.setProjectId(ProjectID);
						record.setUqsid(UQSID);
						record.setUqsname(UQSName);
						record.setUqsversion(UQSVersion);
						record.setVersioncreatedate(StringUtils.isNotBlank(VersionCreateDate)?com.bdcor.pip.dataTransOf2014.pip.util.DateUtils.parseLong(VersionCreateDate):null);
						record.setQuestionStartDate(StringUtils.isNotBlank(uqsBeginTime)?com.bdcor.pip.dataTransOf2014.pip.util.DateUtils.parseLong(uqsBeginTime):null);
						record.setQuestionEndDate(StringUtils.isNotBlank(uqsEndDate)?com.bdcor.pip.dataTransOf2014.pip.util.DateUtils.parseLong(uqsEndDate):null);
						record.setUqstimezone(uqsTimeZone);
						record.setOperaterid(OperaterID);
						record.setOperatername(OperaterName);
						record.setHospitalcode(HospitalCode);
						record.setPatientId(PatientID);
						record.setPersonid(PersonID);
						record.setPatientname(PatientName);
						record.setPatientcode(PatientCode);
						record.setUqsishold(UQSIsHold);
						record.setUqsremark(UQSRemark);
						
						record.setLccCode(lccCode);
						record.setCreateDate(new Date());
						record.setFileName(logZipFile);
						record.setZipfileName(logFileName);
						
						try{
							db.insert(record);
						}catch(Exception e){
							log.error(e.getMessage(),e);
						}
			    	
			    	}
			    	
						
						
					try{
						entry = zis.getNextEntry();
					}catch(Exception e){
						log.error(e.getMessage(),e);
						//entry = zis.getNextEntry();
						entry = null;
					}
					
			
				}//压缩包遍历结�?
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}finally{
				if(zis!=null){
					try {
						zis.close();
					} catch (IOException e) {
						log.error(e.getMessage(),e);
					}
				}
			}
		}
		
		
		
		
		util.closeServer();
		db.close();
		
		
	}
	
	
	
	
	public static void main(String[] a){
		for(int i=0;i<10;i++){
			new Thread(){
				public void run(){
					long start = System.currentTimeMillis();
					FindLostBinding4.find("20150101", "20151231", "22");
				
					long end = System.currentTimeMillis();
					
					System.out.println((end-start)/1000);
				}
			}.start();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	


}
