package ustc.sse.water.docsearcher.util;

import java.io.File;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月31日 下午3:30:02 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午3:30:02
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public class PPTUtils {
	// 根据路径获取文件名不带后缀
	public static String getFilename(String filepath) {
		String[] split = filepath.split("/");
		// 获取到带后缀的全文件名
		String fileFullname = split[split.length - 1];
		int lastIndexOf = fileFullname.lastIndexOf(".");
		return fileFullname.substring(0, lastIndexOf);
	}

	/*
	 * public static String getShortname(String filepath) { String filename =
	 * getFilename(filepath); System.out.println(filename); int indexOf =
	 * filename.lastIndexOf("."); System.out.println(indexOf); String name =
	 * filename.substring(0,2); System.out.println(name); return name;
	 * 
	 * }
	 */
	// 获取后缀
	// TODO后期要防止用户上传的文件名为空名
	public static String getSuffixname(String filepath) {
		String[] split = filepath.split("/");
		String fileFullname = split[split.length - 1];
		int lastIndexOf = fileFullname.lastIndexOf(".");
		return fileFullname.substring(lastIndexOf + 1, fileFullname.length());
	}

	// 判断是否是ppt 方法有待完善
	public static boolean isPPT(String filepath) throws Exception {
		String suffixname = getSuffixname(filepath);
		String name = getFilename(filepath);
		if ("ppt".equals(suffixname)) {
			return true;
		}
		return false;
	}

	public static boolean isPPTX(String filepath) throws Exception {
		String suffixname = getSuffixname(filepath);
		String name = getFilename(filepath);
		if ("pptx".equals(suffixname)) {
			return true;
		}
		return false;
	}

	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}

	}

	// 定义字符转换规范,后期可扩展

	public static String adjustName(String filename) {

		// 去掉空格，替换空白字符，制表符
		filename = filename.replaceAll("\\s*", "");
		// 去掉文件第一个字符中的特殊字符,替换A的原因是若替换空，第二字符作为首字符任然不符合规定
		filename = filename.replaceFirst("-", "A");
		filename = filename.replaceAll("/", "");
		// 去掉前缀中. 注意这里.在正则中代表任何字符
		// filename = filename.replaceAll("\\.", "");
		// 去掉\
		filename = filename.replaceAll("\\\\", "");
		if ("".equals(filename)) {
			filename = "nameEmpity";
		}
		return filename;
	}

}
