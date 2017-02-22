package ustc.sse.water.docsearcher.service.solve.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

//需要的apache下的lucene包
public class CreateIndex {
	public static boolean createDocumentIndex(String dirPath, String indexPath) {
		System.out.println("正在生成中。。。。");
		boolean bool = false;
		try {
			// 需要索引的目录
			File dirpath = new File(dirPath);
			// 将索引文件存放在index目录下
			File indexpath = new File(indexPath);
			if (!indexpath.exists()) {
				System.out.println("创建文件夹" + indexpath.mkdirs());

			}
			// 创建分词器
			Analyzer analyzer = new SmartChineseAnalyzer();
			Directory dir = FSDirectory.open(indexpath);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
			IndexWriter indexWriter = new IndexWriter(dir, iwc);
			// IndexWriter index = new IndexWriter(indexpath, analyzer, true);
			File[] txtfiles = dirpath.listFiles();
			long starttime = new Date().getTime();
			System.out.println("文件个数" + txtfiles.length);
			for (int i = 0; i < txtfiles.length; i++) {
				System.out.println(txtfiles[i].getName());
				if (txtfiles[i].isFile()) {
					System.out.println("文件" + i + txtfiles[i].getCanonicalPath() + "正在建立索引中...");
					/* 读入TXT文件 */
					String pathname = txtfiles[i].getCanonicalPath(); // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
					File filename = new File(pathname); // 要读取以上路径的input。txt文件
					InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
					BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
					String line = "";
					Document doc = new Document();
					while (line != null) {
						line = br.readLine(); // 一次读入一行数据
						if (line != null) {
							doc.add(new Field("content", line, Field.Store.YES, Field.Index.ANALYZED));
							String filepath = txtfiles[i].getName().split("\\.")[0];// pageModel
							doc.add(new Field("path", filepath, Field.Store.YES, Field.Index.NO));
						}
					}
					br.close();
					indexWriter.addDocument(doc);
				}
				System.out.println("没有异常" + i);
			}
			// indexWriter.optimize();
			indexWriter.close();
			long endTime = new Date().getTime();
			System.out.println("这花费了" + (endTime - starttime) + " 毫秒来把文档增加到索引里面去!" + dirpath.getPath());
			bool = true;
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}
}