package com.huiju.pms.myword.service;

import java.io.IOException;


public class MyWordComparer {
	
	public static String vbs_path="e:\\compare.vbs";

	public static int compare(String word1, String word2, String outword) {
		try {
			
			//wscript compare.vbs "e:\\test1.docx", "e:\\test2.docx", "e:\\test3.docx"
			//compare.vbs test1.docx test2.docx  test3.docx
			//wine Z:\opt\wine64\compare.vbs  Z:\opt\wine64\test1.docx Z:\opt\wine64\test2.docx Z:\opt\wine64\test3.docx
			// wine /opt/wine64/compare.vbs  /opt/wine64/test1.docx /opt/wine64/test2.docx /opt/wine64/test3.docx
			String[] cpCmd = new String[] { "wscript", vbs_path, word1, word2, outword };

			Process process = Runtime.getRuntime().exec(cpCmd);
			int val = process.waitFor();// val 是返回值，如果返回0，那么正常执行。
			System.out.println(val);
			return val;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void main(String[] args) {

		MyWordComparer.compare("e:\\test1.docx", "e:\\test2.docx", "e:\\test3.docx");

	}
}
