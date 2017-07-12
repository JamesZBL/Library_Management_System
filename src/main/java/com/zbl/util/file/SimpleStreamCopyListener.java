/**
 * @since 
 */
package com.zbl.util.file;


/**
 * 简单流拷贝监听器

 * @author 
 */
public class SimpleStreamCopyListener implements IStreamCopyListener {
	/* (non-Javadoc)
	 * @see cn.sh.ideal.framework.util.IStreamCopyListener#streamCopied(long, int)
	 */
	public boolean streamCopied(long totalBytesTransferred, int bytesTransferred)
			throws Exception {
		//log.debug(this + "已经复制:" + totalBytesTransferred + "; 本次复制:" + bytesTransferred);
		return true;
	}

}
