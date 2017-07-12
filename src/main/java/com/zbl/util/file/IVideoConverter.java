/**
 * 
 */
package com.zbl.util.file;

import java.io.File;

/**
 * @author 
 *
 */
/**
 *
 * @author  
 */
public interface IVideoConverter {
	
	/**
	 * @param inputPath
	 * @param outputPath
	 * @throws Exception
	 * 指定路径转换,不推荐

	 */
	public boolean convert(String inputPath, String outputPath) throws Exception;
	
	/**
	 * @param source
	 * @param target
	 * @throws Exception
	 * 指定文件转换，转换为flv格式，转换时请先确定source的格式，默认不做截图，推荐使用

	 */
	public boolean convert(File source, File target) throws Exception;
	/**
	 * @param source 输入文件
	 * @param target 输出文件
	 * @param preview 是否截图
	 * @return
	 * @throws Exception
	 * 定文件转换，转换为flv格式，转换时请先确定source的格式，根据参数preview来判断截图操作

	 * 默认截图没有指定图片位置，则输出与target文件相同名称的jpg文件，推荐使用

	 */
	public boolean convert(File source, File target, boolean preview) throws Exception;
	
	/**
	 * @param source
	 * @param target
	 * @param format
	 * @return
	 * @throws Exception
	 * 指定文件格式转换
	 */
	public boolean convert(File source, File target, String format) throws Exception;
	
	/**
	 * @param source 源文件

	 * @param target 输出文件
	 * @param format 格式
	 * @param preview 是否截图
	 * @return
	 * @throws Exception
	 */
	public boolean convert(File source, File target, String format, boolean preview) throws Exception;
	/**
	 * @param source 输入文件
	 * @param target 输出文件
	 * @param format 格式
	 * @param preview 是否截图
	 * @param previewPath 截图文件
	 * @return
	 * @throws Exception
	 */
	public boolean convert(File source, File target, String format,
                           boolean preview, File previewPath) throws Exception;
}
