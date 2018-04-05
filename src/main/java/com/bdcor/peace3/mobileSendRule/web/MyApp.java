package com.bdcor.peace3.mobileSendRule.web;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;

public class MyApp {
  public static void startWeb() throws InterruptedException, ExecutionException {
    WebServer webServer = WebServers.createWebServer(9996)
    		
      .add("/log",new LogHandler())
      .add("/rule",new RuleHandler())
      .add("/content",new ContentHandler())
      .add("/send",new SendHandler())
      .add(new RootHandler());
    webServer.start().get(); // start() returns a Future.
  }
  static class RootHandler implements HttpHandler {
	  @Override
	  public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		  String dateStr1 = DateTime.now().minusDays(1).toString("yyyy-MM-dd");
		  
		  String dateStr = DateTime.now().toString("yyyy-MM-dd");
		  response.header("Content-type", "text/html;charset=UTF-8")
	    		//.header("", "")
	            .content("<html><body>" +
	            		"<li><a href='/log?d="+dateStr+"'>日志</a></li>" +
	            		"<li><a href='/rule?d="+dateStr+"'>发送规则</a></li>" +
	            		"<li><a href='/content?d="+dateStr+"'>特殊字符</a></li>" +
	            		"<li><a href='/send?d="+dateStr1+"'>发送回执</a></li>" +
	            				"</body></html>")
	            .end();
	  }
	}
  static class LogHandler implements HttpHandler {
	  @Override
	  public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		  String dateStr = request.queryParam("d");
		  if(StringUtils.isBlank(dateStr)){
			  dateStr = DateTime.now().toString("yyyy-MM-dd");
		  }
		  String content = "";
		  try {
			  content = FileUtils.readFileToString(new File("../logs/bdcor-schedule-peace3."+dateStr+".log"));
		} catch (IOException e) {
			content = e.getMessage();
		}
		  
	    response.header("Content-type", "text/text;charset=UTF-8")
	    		//.header("", "")
	            .content(dateStr+"============>"+System.lineSeparator()+content+"")
	            .end();
	  }
	}
  
  
  static class RuleHandler implements HttpHandler {
	  @Override
	  public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		  String dateStr = request.queryParam("d");
		  if(StringUtils.isBlank(dateStr)){
			  dateStr = DateTime.now().toString("yyyy-MM-dd");
		  }
		  dateStr = dateStr.replaceAll("-","");
		  String content = "";
		  try {
			  content = FileUtils.readFileToString(new File("peace3_mobile_send/ruleCheck-"+dateStr+".txt"));
		} catch (IOException e) {
			content = e.getMessage();
		}
		  
	    response.header("Content-type", "text/text;charset=UTF-8")
	    		//.header("", "")
	             .content(dateStr+"============>"+System.lineSeparator()+content+"")
	            .end();
	  }
	}
  

  static class ContentHandler implements HttpHandler {
	  @Override
	  public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		  String dateStr = request.queryParam("d");
		  if(StringUtils.isBlank(dateStr)){
			  dateStr = DateTime.now().toString("yyyy-MM-dd");
		  }
		  dateStr = dateStr.replaceAll("-","");
		  String content = "";
		  try {
			  content = FileUtils.readFileToString(new File("peace3_mobile_send/mobileContentCheck-"+dateStr+".txt"));
		} catch (IOException e) {
			content = e.getMessage();
		}
		  
	    response.header("Content-type", "text/text;charset=UTF-8")
	    		//.header("", "")
	             .content(dateStr+"============>"+System.lineSeparator()+content+"")
	            .end();
	  }
	}
  

  static class SendHandler implements HttpHandler {
	  @Override
	  public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		  String dateStr = request.queryParam("d");
		  if(StringUtils.isBlank(dateStr)){
			  dateStr = DateTime.now().toString("yyyy-MM-dd");
		  }
		  dateStr = dateStr.replaceAll("-","");
		  String content = "";
		  try {
			  content = FileUtils.readFileToString(new File("peace3_mobile_send/mobileSendStatusCheck-"+dateStr+".txt"));
		} catch (IOException e) {
			content = e.getMessage();
		}
		  
	    response.header("Content-type", "text/text;charset=UTF-8")
	    		//.header("", "")
	            .content(dateStr+"============>"+System.lineSeparator()+content+"")
	            .end();
	  }
	}
}