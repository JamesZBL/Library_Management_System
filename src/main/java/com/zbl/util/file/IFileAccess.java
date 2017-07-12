/**
 * @since 2008-10-10,下午04:31:07
 */
package com.zbl.util.file;

import java.io.InputStream;

/**
 * 文件保存接口
 * 
 * @author 
 */
public interface IFileAccess {
	
	/**
	 * 保存文件
	 * 
	 * @param fileInputStream
	 *            文件输入流
	 * 
	 * @param savingName
	 *            保存名称
	 * @return 保存后返回文件相关信息，包括文件存储目录、大小、文件名称等
	 * @throws Exception
	 */
	public FileSaveResult saveFile(InputStream fileInputStream,
                                   String savingName) throws Exception;

	/**
	 * 保存图片并生成预览图片
	 * 
	 * @param fileInputStream
	 *            文件输入流
	 * 
	 * @param savingName
	 *            保存名称
	 * @param hasPreview
	 *            是否生成预览图片
	 * @return
	 * @throws Exception
	  */
	public FileSaveResult saveFile(InputStream fileInputStream,
                                   String savingName, boolean hasPreview) throws Exception;
	
	/**
	 * 保存图片并生成指定大小的预览图片
	 * @param fileInputStream 文件输入流
	 * @param width	预览图片宽
	 * @param height	预览图片高
	 * @param savingName	文件保存名称
	 * @param hasPreview	是否生成预览
	 * @return
	 * @throws Exception
	
	public FileSaveResult saveFile(InputStream fileInputStream,int width,int height,
			String savingName, boolean hasPreview) throws Exception;
 */

	/**
	 * 删除文件
	 * 
	 * @param savingName
	 *            文件相对路径
	 * @return 如果成功删除则返回true,否则返回 false
	 * @throws Exception
	 */
	public boolean deleteFile(String savingName) throws Exception;

	/**
	 * 打开文件输入流
	 * 
	 * @param savingName
	 *            文件相对路径
	 * @return
	 * @throws Exception
	 */
	public InputStream openFileInputStream(String savingName) throws Exception;

	/**
	 * 关闭文件流
	 * 
	 * @throws Exception
	 */
	public void closeFile() throws Exception;
	
	/**
	 * 修改上传文件的最大大小（单位：M）
	 * 
	 * @param maxLength
	 */
	public void setMaxFileLength(float maxLength);
	/**
	 * 保存视频文件,视频文件格式转换,视频文件截屏图片
	 * 
	 * @param fileInputStream
	 *            文件输入流
	 * 
	 * @param savingName
	 *            保存名称
	 * @return 保存后返回文件相关信息，包括文件存储目录、大小、文件名称等
	 * @throws Exception
	 */
	public FileSaveResult saveVideoFile(InputStream fileInputStream, String savingName) throws Exception;
	
	
	/**
	 * 复制文件到指定目录
	 * @param fileInputStream
	 * @param savingName
	 * @return
	 * @throws Exception
	 */
	public String copyFileTo(String bpath, String savingName) throws Exception;

	
	
	/**
	 * 关闭操作文件的FTP
	 * 
	 * @throws Exception
	 */
	public void closeFileFtp() throws Exception;
	
}
