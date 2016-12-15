package ustc.sse.water.docsearcher.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月2日 下午9:12:49 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:12:49
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "HotKeyword")

public class HotKeywordModel {
	private Long hotKeyId;// 编号
	private String hotKeyword;// 关键词
	private Integer sumSearch;// 搜索次数
	private Integer keyRank;// 当前排名
	private Date recordTime;// 最近统计时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hot_key_id", unique = true, nullable = false)
	public Long getHotKeyId() {
		return hotKeyId;
	}

	@Column(name = "hot_keyword")
	public String getHotKeyword() {
		return hotKeyword;
	}

	@Column(name = "sum_search")
	public Integer getSumSearch() {
		return sumSearch;
	}

	@Column(name = "key_rank")
	public Integer getKeyRank() {
		return keyRank;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	public void setHotKeyId(Long hotKeyId) {
		this.hotKeyId = hotKeyId;
	}

	public void setHotKeyword(String hotKeyword) {
		this.hotKeyword = hotKeyword;
	}

	public void setSumSearch(Integer sumSearch) {
		this.sumSearch = sumSearch;
	}

	public void setKeyRank(Integer keyRank) {
		this.keyRank = keyRank;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

}
