package com.huiju.pms.myword.service;

import java.io.File;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.huiju.pms.myword.common.Constant;
import com.huiju.pms.myword.common.FileUrlUtil;
 

@Service
public class MyWordService {

	private static final Log log = LogFactory.getLog(MyWordService.class);

	public String compare(String path1, String path2, String outfileName) {

		int result = MyWordComparer.compare(path1, path2, Constant.TEMP_DIR + File.separator + outfileName);
		if (result == 0) {
			return Constant.TEMP_DIR + File.separator + outfileName;
		} else {
			return "";
		}
	}

	public String compare(String path1, String path2) {
		String outpath = Constant.TEMP_DIR + File.separator + UUID.randomUUID().toString() + FileUrlUtil.getExtName(path1);
		int result = MyWordComparer.compare(path1, path2, outpath);
		if (result == 0) {
			return outpath;
		} else {
			return "";
		}
	}


    
	public void removeExpireFile() {
		 File files=new File(Constant.TEMP_DIR);
		 if(files.exists()&&files.isDirectory()){
			 for(File file:files.listFiles()){
				 if(file.lastModified()>System.currentTimeMillis()-Constant.EXPIRE_IN_MINUTE*60*1000){
					 file.delete();
				 }
			 }
			 
			 
		 }
	}
   
	  
}
