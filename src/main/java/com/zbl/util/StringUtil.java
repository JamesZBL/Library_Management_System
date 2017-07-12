/**
 * @since 
 */
package com.zbl.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 
 */
public abstract class StringUtil {
	private static String IMG_EXT = ".jpg,.gif,.bmp,.jpeg,.png,";
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	/**
	 * 得到文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		if (Empty.isEmpty(fileName)) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
	}

	/**
	 * 判断是否为图片类型文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImgFile(String fileName) {
		return IMG_EXT.indexOf(getFileExt(fileName) + ",") >= 0;
	}

	/**
	 * 从文件全路径中得到文件名
	 * 
	 * @param args
	 */
	public static String getFileName(String path) {
		// /abc/bcd/abc.txt
		if (Empty.isEmpty(path)) {
			return null;
		}
		String s = path.substring(path.lastIndexOf(File.separatorChar) + 1);
		return s;
	}

	/**
	 * 把文本转换成html 显示,静态化生成用到
	 * 
	 * @param text
	 * @return
	 */
	public static String toHtmlString(String text) {
		if (Empty.isEmpty(text)) {
			return "";
		}
		String tmp = text;

		String tmpS = null;
		try {
			String regex1 = "\n"; // 回车
			String regex2 = "^\\s"; // 首行为 tab 操作符
			tmpS = tmp.replaceAll(regex1, "<br/>&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll(regex2, "&nbsp;&nbsp;");
		} catch (Exception e) {
			e.printStackTrace();
			tmpS = text;
		}
		return tmpS;
	}

	public static String toHexString(String text) {
		if (text == null) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		final String start = "\\u";
		for (int i = 0; i < text.length(); i++) {
			int c = text.charAt(i);
			buf.append(start);
			String s = Integer.toHexString(c);

			if (s.length() == 1) {
				s = "000" + s;
			} else if (s.length() == 2) {
				s = "00" + s;
			} else if (s.length() == 3) {
				s = "0" + s;
			}
			buf.append(s);
		}
		return buf.toString();
	}

	/**
	 * 向文件名中追加字符，以修改文件名称
	 * 
	 * @param oldName
	 *            文件原有名称
	 * @param appendString
	 *            追加名称
	 * @return 处理后的文件名
	 */
	public static String appendFileName(String oldName, String appendString) {
		// /abc/bcd/abc.txt
		if (Empty.isEmpty(oldName)) {
			return null;
		}
		String name = oldName.substring(oldName.lastIndexOf("\\") + 1);
		name = name.substring(name.lastIndexOf("/") + 1);
		StringBuffer sb = new StringBuffer();
		sb.append(oldName.substring(0, oldName.lastIndexOf(name)));
		sb.append(name.substring(0, name.lastIndexOf(".")));
		sb.append(appendString);
		sb.append(name.substring(name.lastIndexOf(".")));

		return sb.toString();
	}

	/**
	 * 判断字符串是否在字符数组中
	 * 
	 * @param s
	 *            字符串数组
	 * @param str
	 *            需要比较的字符串
	 * @return 如果存在返回：true,如果不存在返回：false
	 */
	public static boolean contains(String[] s, String str) {
		if (s == null || str == null)
			return false;
		for (int i=0;i<s.length;i++) {
			if (str.equals(s[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 过滤字符中的HTML标签
	 * 
	 * @param element
	 *            含有HTML标签体的字符串
	 * @return
	 */
	public static String getTxtWithoutHTML(String element) {
		// String reg="<[^<|^>]+>";
		// return element.replaceAll(reg,"");

		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern.compile("&[a-z]{1,10}+;|<[^<|^>]*>");

		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();

		// 替换字符中HTML标签和特殊符号

		while (matcher.find()) {
			String group = matcher.group();

			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else if (group.matches("&nbsp;")) {
				matcher.appendReplacement(txt, " ");
			} else if (group.matches("&amp;")) {
				matcher.appendReplacement(txt, "&");
			} else if (group.matches("&lt;")) {
				matcher.appendReplacement(txt, "<");
			} else if (group.matches("&gt;")) {
				matcher.appendReplacement(txt, ">");
			} else if (group.matches("&quot;")) {
				matcher.appendReplacement(txt, "\"");
			} else if (group.matches("&apos;")) {
				matcher.appendReplacement(txt, "\'");
			} else {
				matcher.appendReplacement(txt, "");
			}

		}

		// 加裁最后字符

		matcher.appendTail(txt);
		/*
		 * txt.repaceEntities(txt,"&","&"); repaceEntities(txt,"<","<");
		 * repaceEntities(txt,">",">"); repaceEntities(txt,""","\"");
		 * repaceEntities(txt," ","");
		 */

		return txt.toString();

	}
	
	public static String replaceCont(String txt) {		
		StringBuilder sb = new StringBuilder(txt.length() + 16);
		for (int i = 0; i < txt.length(); i++) {  
			char c = txt.charAt(i);
			switch (c) {
			case '＞':
				sb.append('>');// 全角大于号
				break;
			case '＜':
				sb.append('<');// 全角小于号
				break;
			case '‘':
				sb.append('\'');// 全角单引号
				break;
			case '“':
				sb.append('\"');// 全角双引号
				break;
			case '＆':
				sb.append('&');// 全角
				break;
			case '＼':
				sb.append('\\');// 全角斜线
				break;
			case '＃':
				sb.append('#');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}  
      return sb.toString();
	}
	
	/**
	 * 通过表达式将参数与字符模板进行合并并返回。
	 * 
	 * @param template
	 * @param arguments
	 * @return
	 */
	public static String format(String template,Object[] arguments){
		 return MessageFormat.format(template, arguments);
	}
	
	public static String subString(String text, int length, String endWith) {        
        int textLength = text.length();  
        int byteLength = 0;  
        StringBuffer returnStr =  new StringBuffer();  
        for(int i = 0; i<textLength && byteLength < length*2; i++){  
            String str_i = text.substring(i, i+1);   
            if(str_i.getBytes().length == 1){//英文  
                byteLength++;  
            }else{//中文  
                byteLength += 2 ;  
            }  
            returnStr.append(str_i);  
        }  
        try {  
            if(byteLength<text.getBytes("GBK").length){//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3  
                returnStr.append(endWith);  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return returnStr.toString();  
    }

	public static void main(String[] org) {
		String text = "。发.。篇>所q阿srjgkgggdcdfrtyh似hf的f＊*发千万s";  
        
            String s = StringUtil.subString(text,30,"...");  
            System.out.println(s+"--------------------------"+(30));  
	}

}
