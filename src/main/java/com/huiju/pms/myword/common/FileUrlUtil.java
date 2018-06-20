package com.huiju.pms.myword.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUrlUtil {
	private static final Log log = LogFactory.getLog(FileUrlUtil.class);
	
	  public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
	        URL url = new URL(urlStr);    
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	                //设置超时间为3秒  
	        conn.setConnectTimeout(3*1000);  
	        //防止屏蔽程序抓取而返回403错误  
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
	  
	        //得到输入流  
	        InputStream inputStream = conn.getInputStream();    
	        //获取自己数组  
	        byte[] getData = readInputStream(inputStream);      
	  
	        //文件保存位置  
	        File saveDir = new File(savePath);  
	        if(!saveDir.exists()){  
	            saveDir.mkdir();  
	        }  
	        File file = new File(saveDir+File.separator+fileName);      
	        FileOutputStream fos = new FileOutputStream(file);       
	        fos.write(getData);   
	        if(fos!=null){  
	            fos.close();    
	        }  
	        if(inputStream!=null){  
	            inputStream.close();  
	        }  
	  
	  
	        log.info(url+" download success");   
	  
	    }  
	    /** 
	     * 从输入流中获取字节数组 
	     * @param inputStream 
	     * @return 
	     * @throws IOException 
	     */  
	    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
	        byte[] buffer = new byte[1024];    
	        int len = 0;    
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	        while((len = inputStream.read(buffer)) != -1) {    
	            bos.write(buffer, 0, len);    
	        }    
	        bos.close();    
	        return bos.toByteArray();    
	    }  
	    

	    public static String getExtName(String path) {
			if (StringUtil.checkStr(path) && path.indexOf(".") > 0) {
				return path.substring(path.lastIndexOf("."));
			}
			return "";
		}
	  
	    public static String getName(String path) {
			if (StringUtil.checkStr(path)) {
				int p1= path.lastIndexOf(File.separator) ;
				String temp=path.substring(p1);
				p1=temp.lastIndexOf(".") ;
				return temp.substring(0,p1-1);
			}
			return "";
		}
	    
	    
	    public static void main(String[] args) {  
	        try{  
	            downLoadFromUrl("http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png",  
	                    "百度.jpg","d:/resource/images/diaodiao/country/");  
	        }catch (Exception e) {  
	            // TODO: handle exception  
	        }  
	    }
}
