/**
 * @since 
 */
package com.zbl.util.file;

import java.util.EventListener;

/**
 * @author 
 */
public interface IStreamCopyListener extends EventListener{
	/**
	 * 流拷贝了多少字节数据
	 * @param totalBytesTransferred 总共拷贝的字节数
	 * @param bytesTransferred 本次传输字节数

	 * @return 本次拷贝后是否继续拷贝其他字节流：true-否，false-继续
	 */
	boolean streamCopied(long totalBytesTransferred, int bytesTransferred) throws Exception;
}
