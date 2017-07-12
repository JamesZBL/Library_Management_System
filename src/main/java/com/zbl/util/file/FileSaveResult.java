/**
 * @since 2008-10-10,下午04:31:26
 */
package com.zbl.util.file;

/**
 * 文件保存结果
 * 
 * @author 
 */
public class FileSaveResult {
	private String fileName; // 文件相对路径
	private String previewUrl; // 图片预览相对路径
	private long savedSize; // 文件已保存大小

	/**
	 * @return 文件相对路径
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param 文件相对路径
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

	/**
	 * @return 文件已保存大小
	 */
	public long getSavedSize() {
		return savedSize;
	}

	/**
	 * @param 文件已保存大小
	 */
	public void setSavedSize(long savedSize) {
		this.savedSize = savedSize;
	}

	/**
	 * @return 图片预览相对路径
	 */
	public String getPreviewUrl() {
		return previewUrl;
	}

	/**
	 * @param 图片预览相对路径
	 */
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

}
