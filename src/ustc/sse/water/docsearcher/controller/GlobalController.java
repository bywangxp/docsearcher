package ustc.sse.water.docsearcher.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.TagModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;
import ustc.sse.water.docsearcher.service.ebi.DocumentEbi;
import ustc.sse.water.docsearcher.service.ebi.GlobalEbi;
import ustc.sse.water.docsearcher.service.ebi.TagEbi;
import ustc.sse.water.docsearcher.util.Constant;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月16日 上午11:33:14 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月16日 上午11:33:14
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Controller
@RequestMapping("/global")
public class GlobalController {
	@Resource
	private GlobalEbi globalEbi;
	@Resource
	private DocumentEbi documentEbi;
	@Resource
	private TagEbi tagEbi;

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年12月16日 上午11:39:44 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	@ResponseBody
	@RequestMapping(value = "/get_rank_top5", method = { RequestMethod.POST })
	public Map<String, Object> getRankTop5(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<DownloadRankModel> list = globalEbi.getDownloadRankModel();
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		List<Map<String, Object>> pagesList = new ArrayList<Map<String, Object>>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("pic", Constant.DEFAULT_RANK_PNG);
		map2.put("name", "下载排行");
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

		for (DownloadRankModel downloadRankModel : list) {

			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", downloadRankModel.getDocId());
			temp.put("rank", downloadRankModel.getDownRank());
			// 根据id，获取文件名
			DocumentModel documentModel = documentEbi.getDocumentByDocId(downloadRankModel.getDocId());
			temp.put("name", documentModel.getDocTitle());
			temp.put("pic", Constant.DEFAULT_PPT_PNG);
			list2.add(temp);
		}
		map2.put("data", list2);
		pagesList.add(map2);
		totalmap.put("rank_top5", pagesList);
		return totalmap;
	}

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年12月16日 下午12:34:19 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	@ResponseBody
	@RequestMapping(value = "/get_inf", method = { RequestMethod.POST })
	public Map<String, Object> getInf(HttpServletRequest request, HttpServletResponse response) {

		return null;

	}

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年12月16日 下午12:38:19 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	@ResponseBody
	@RequestMapping(value = "/get_chart1", method = { RequestMethod.POST })
	public Map<String, Object> getChart1(HttpServletRequest request, HttpServletResponse response) {
		List<TagRecordModel> tagRecordModel = globalEbi.getTagRecordModel();
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		// List<Map<String, Object>> list2 = new ArrayList<Map<String,
		// Object>>();
		String[] data = new String[] { "课件", "演示", "科技", "文学", "情感", "教程", "其他" };
		totalmap.put("data", data);
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
		Map<String, Object> seriesMap = new HashMap<String, Object>();
		int sum = 0;// 计算总库存
		Date recordTime = null;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (TagRecordModel tempTagRecodModel : tagRecordModel) {
			// 为了记录统计日期
			recordTime = tempTagRecodModel.getRecordTime();
			Map<String, Object> temp = new HashMap<String, Object>();
			Integer sumDocTotal = tempTagRecodModel.getSumDocTotal();
			temp.put("value", sumDocTotal);
			sum += tempTagRecodModel.getSumDocTotal();
			// 根据tagId来查找标签名称
			TagModel tagModel = tagEbi.getTagsByTagId(tempTagRecodModel.getTagId());
			temp.put("name", tagModel.getTagName());
			dataList.add(temp);
		}
		seriesMap.put("data", dataList);
		seriesMap.put("type", "pie");
		int[] redies = { 20, 140 };
		seriesMap.put("roseType", "area");
		seriesMap.put("redius", redies);
		seriesMap.put("name", "总库存：" + sum);
		series.add(seriesMap);
		totalmap.put("series", series);
		Map<String, Object> titleMap = new HashMap<String, Object>();
		titleMap.put("text", "PPT文档分类统计");
		String subText = null;
		// 日期,测试用当前日期，后期修改
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String format = simpleDateFormat.format(recordTime);
		System.out.println("统计日期:" + format);
		titleMap.put("subtext", "统计日期:" + format);
		titleMap.put("x", "center");
		totalmap.put("title", titleMap);
		return totalmap;

	}

	@ResponseBody
	@RequestMapping(value = "/get_chart2", method = { RequestMethod.POST })
	public Map<String, Object> getChart2(HttpServletRequest request, HttpServletResponse response) {
		// List<TagRecordModel> tagRecordModel = userEbi.getChart2();
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		List<Map<String, Object>> pagesList = new ArrayList<Map<String, Object>>();

		return null;

	}

}
