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
 * 修改历史 2016年11月2日 下午9:53:59 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:53:59
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Entity
@Table(name = "TagRecord")
public class TagRecordModel {

	private Long tagRecordId;// 编号
	private Date recordTime;// 统计时间
	private Long tagId;// 分类编号
	private Integer sumDocToday;// 此分类当前文档数
	private Integer sumDocTotal;// 此分类当前总文档数

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_record_id", unique = true, nullable = false)
	public Long getTagRecordId() {
		return tagRecordId;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	@Column(name = "tag_id", nullable = false)
	public Long getTagId() {
		return tagId;
	}

	@Column(name = "sum_doc_today")
	public Integer getSumDocToday() {
		return sumDocToday;
	}

	@Column(name = ")sum_doc_total")
	public Integer getSumDocTotal() {
		return sumDocTotal;
	}

	public void setTagRecordId(Long tagRecordId) {
		this.tagRecordId = tagRecordId;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public void setSumDocToday(Integer sumDocToday) {
		this.sumDocToday = sumDocToday;
	}

	public void setSumDocTotal(Integer sumDocTotal) {
		this.sumDocTotal = sumDocTotal;
	}
}
