package ustc.sse.water.docsearcher.service.solve;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:31 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:31
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public class ConvertToPdf {

	public void convetToPdf(String absolutePath, String name_no_suffix, int size) {
		// 创建一个文档对象
		Document doc = new Document(PageSize.A4, 50, 50, 80, 0);
		try {
			// 定义输出文件的位置
			PdfWriter.getInstance(doc, new FileOutputStream(
					absolutePath + "UserFiles\\" + name_no_suffix + "\\pdf\\" + name_no_suffix + ".pdf"));
			// 开启文档
			doc.open();
			// 向文档中加入图片
			Image jpg1 = null;
			System.out.println("ppt个数" + size);
			for (int i = 1; i <= size; i++) {
				// 获取图片来源：
				jpg1 = Image.getInstance(absolutePath + "UserFiles\\" + name_no_suffix + "\\images\\" + name_no_suffix
						+ "_" + i + ".png"); // 原来的图片的路径
				// 获得图片的高度
				float heigth = jpg1.height();
				float width = jpg1.width();
				// 合理压缩，h>w，按w压缩，否则按w压缩
				// int percent=getPercent(heigth, width);
				// 统一按照宽度压缩
				int percent = getPercent2(heigth, width);
				// 设置图片居中显示
				jpg1.setAlignment(Image.ALIGN_CENTER);

				/* jpg1.scalePercent(50, 100); */
				jpg1.scalePercent(percent);// 表示是原来图像的比例;
				doc.add(jpg1);
				/* System.out.println(i+"转成pdf"); */
				doc.newPage();
			}
			// 在此处多插入任意一个图片，此图片不展示，作用是使得最后一张图片可以正常转换。 后期修复此功能点
			/*
			 * jpg1 = Image.getInstance("UserFiles/"+name_no_suffix+"/images/"+
			 * name_no_suffix+1+".png"); doc.add(jpg1);
			 */
			// 关闭文档并释放资源
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
	 * 
	 * @param h
	 * @param w
	 * @return
	 */

	public int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	/**
	 * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的
	 * 
	 * @param args
	 */
	public int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}

	public boolean composePdf(String path, String absolutePath, long[] pagesId, String[] pageSaveKey, int[] num) {
		System.out.println(path);
		int size = pagesId.length;
		// 创建一个文档对象
		Document doc = new Document(PageSize.A4, 50, 50, 80, 0);
		try {
			File file = new File(path);
			if (file.getParent() != null && !new File(file.getParent()).exists()) {
				System.out.println("mkdirs");
				new File(file.getParent()).mkdirs();
			}
			boolean createNewFile = file.createNewFile();
			System.out.println("创建文件成功？" + createNewFile);
			if (!file.isFile()) {
				System.out.println("文检创建失败");
			}
			// 定义输出文件的位置
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			// 开启文档
			doc.open();
			// 向文档中加入图片
			Image jpg1 = null;
			System.out.println("ppt个数" + pagesId.length);
			for (int i = 1; i <= size; i++) {
				System.out.println("pageSaveKey" + pageSaveKey[i - 1]);
				System.out.println("num" + num[i - 1]);
				String pageSaveKeyTemp = pageSaveKey[i - 1];
				int numTemp = num[i - 1];// 获取在当前文件中的页码值
				// 根据pid获取图片文件的来源：
				jpg1 = Image.getInstance(absolutePath + "UserFiles\\" + pageSaveKeyTemp + "\\images\\" + pageSaveKeyTemp
						+ "_" + numTemp + ".png"); // 原来的图片的路径
				// 获得图片的高度
				float heigth = jpg1.height();
				float width = jpg1.width();
				// 合理压缩，h>w，按w压缩，否则按w压缩
				// int percent=getPercent(heigth, width);
				// 统一按照宽度压缩
				int percent = getPercent2(heigth, width);
				// 设置图片居中显示
				jpg1.setAlignment(Image.ALIGN_CENTER);

				/* jpg1.scalePercent(50, 100); */
				jpg1.scalePercent(percent);// 表示是原来图像的比例;
				doc.add(jpg1);
				/* System.out.println(i+"转成pdf"); */
				doc.newPage();
			}
			// 在此处多插入任意一个图片，此图片不展示，作用是使得最后一张图片可以正常转换。 后期修复此功能点
			/*
			 * jpg1 = Image.getInstance("UserFiles/"+name_no_suffix+"/images/"+
			 * name_no_suffix+1+".png"); doc.add(jpg1);
			 */
			// 关闭文档并释放资源
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
