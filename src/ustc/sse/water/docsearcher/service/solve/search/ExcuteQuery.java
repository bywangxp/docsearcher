package ustc.sse.water.docsearcher.service.solve.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class ExcuteQuery {
	public static ArrayList<String> query(String keyword) {
		String index = "c:\\index";// 搜索的索引路径
		IndexReader reader = null;
		try {
			reader = DirectoryReader.open(FSDirectory.open(new File(index)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexSearcher searcher = new IndexSearcher(reader);// 检索工具
		ScoreDoc[] hits = null;
		String queryString = "吃饭"; // 搜索的索引名称
		Query query = null;
		Analyzer luceneAnalyzer = new StandardAnalyzer();
		// QueryParser qp=new
		// QueryParser(Version.LUCENE_3_6_0,"body",analyzer);//用于解析用户输入的工具
		// v3.6.0
		QueryParser qp = new QueryParser(Version.LUCENE_CURRENT, "content", luceneAnalyzer);// 用于解析用户输入的工具
		try {
			query = qp.parse(keyword);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("分词情况:" + query.toString());
		if (searcher != null) {
			TopDocs results = null;
			try {
				results = searcher.search(query, null, 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 只取排名前十的搜索结果
			hits = results.scoreDocs;
			Document document = null;
			System.out.println(hits.length);
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < hits.length; i++) {
				try {
					document = searcher.doc(hits[i].doc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String body = document.get("content");
				String path = document.get("path");
				list.add(path);
				String modifiedtime = document.get("modifiField");
				System.out.println("BODY---" + body);
				System.out.println("PATH--" + path);
				System.out.println("modifiedtime--" + modifiedtime);
			}
			if (hits.length > 0) {
				System.out.println("输入关键字为：" + queryString + "，" + "找到" + hits.length + "条结果!");
			}
			// searcher.close();
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		return null;
	}
}
