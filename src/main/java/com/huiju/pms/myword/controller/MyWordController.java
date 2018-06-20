package com.huiju.pms.myword.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.huiju.pms.myword.common.Constant;
import com.huiju.pms.myword.common.FileUrlUtil;
import com.huiju.pms.myword.common.ResponseModal;
import com.huiju.pms.myword.common.StringUtil;
import com.huiju.pms.myword.common.SysStatus;
import com.huiju.pms.myword.service.MyWordService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/myword")
public class MyWordController {
	private static final Log log = LogFactory.getLog(MyWordController.class);

	@Autowired
	MyWordService myWordService;

	@RequestMapping(value = "/compare", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModal compare(@RequestBody final JSONObject json) {
		final ResponseModal rm = new ResponseModal();
		rm.setSysStatus(SysStatus.PARAM_NOTNULL);
		if (json != null) {
			String url1 = json.getString("doc1");
			String url2 = json.getString("doc2");

			try {
				if (StringUtil.checkStr(url1) && StringUtil.checkStr(url2)) {

					String path = Constant.TEMP_DIR;
					FileUrlUtil.downLoadFromUrl(url1, UUID.randomUUID().toString(), path);
					File file1 = new File(url1);

					FileUrlUtil.downLoadFromUrl(url2, UUID.randomUUID().toString(), path);
					File file2 = new File(url2);

					if (file1.exists() && file2.exists()) {

						String outname = UUID.randomUUID().toString();
						String outpath = myWordService.compare(file1.getAbsolutePath(), file2.getAbsolutePath(),
								Constant.TEMP_DIR + File.pathSeparator + outname);
						if (StringUtil.checkStr(outpath)) {
							rm.setSysStatus(SysStatus.SUCCESS);
							JSONObject obj = new JSONObject();
							obj.put("url", Constant.URL_DOWNLOAD + FileUrlUtil.getExtName(outname) + "/" + outname);
							rm.setResult(obj);
							rm.setSysStatus(SysStatus.SUCCESS);
						}

					}
				}
			} catch (Exception ex) {
				rm.setSysStatus(SysStatus.ERROR);
				log.info("内部错误：" + ex.getMessage());
			}

		}
		return rm;
	}

	@RequestMapping(value = "/download/{ext}/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModal list(@PathVariable("ext") final String ext, @PathVariable("id") final String id,
			HttpServletResponse response) throws UnsupportedEncodingException {
		final ResponseModal rm = new ResponseModal();
		rm.setSysStatus(SysStatus.PARAM_NOTNULL);
		if (StringUtil.checkStr(id)) {

			try {
				if (StringUtil.checkStr(ext) && StringUtil.checkStr(id)) {

					String path = Constant.TEMP_DIR + File.pathSeparator + id + "." + ext;

					File file = new File(path);

					response.setContentType("text/html; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					Boolean isOnLine = true; // 是否在线浏览

					FileInputStream fis = null;
					BufferedInputStream buff = null;
					OutputStream myout = null;
					try {
						if (!file.exists()) {
							// ServletActionContext.getResponse().sendError(404,
							// "File not found!");
							rm.setSysStatus(SysStatus.PARAM_NOFOUND);
							return rm;
						}
						response.reset();
						if (isOnLine) { // 在线打开方式
							URL u = new URL("file:///" + path.toString());
							response.setContentType(u.openConnection().getContentType());
							response.setHeader("Content-Disposition",
									"inline; filename=" + new String(file.getName().getBytes("gbk"), "iso-8859-1"));
						} else { // 纯下载方式
							// 设置response的编码方式
							response.setContentType("application/x-msdownload");
							// 写明要下载的文件的大小
							response.setContentLength((int) file.length());
							// 设置附加文件名(解决中文乱码)
							response.setHeader("Content-Disposition",
									"attachment;filename=" + new String(file.getName().getBytes("gbk"), "iso-8859-1"));
						}

						fis = new FileInputStream(file);
						buff = new BufferedInputStream(fis);
						byte[] b = new byte[10240];// 相当于我们的缓存
						long k = 0;// 该值用于计算当前实际下载了多少字节
						// 从response对象中得到输出流,准备下载
						myout = response.getOutputStream();
						while (k < file.length()) {
							int j = buff.read(b, 0, 10240);
							k += j;
							// 将b中的数据写到客户端的内存
							myout.write(b, 0, j);
						}
						// 将写入到客户端的内存的数据,刷新到磁盘
						myout.flush();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							if (fis != null) {
								fis.close();
							}
							if (buff != null)
								buff.close();
							if (myout != null)
								myout.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					return null;

				}

			} catch (Exception ex) {
				rm.setSysStatus(SysStatus.ERROR);
				log.info("内部错误：" + ex.getMessage());
			}

		}
		return rm;
	}
}
