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
 * 修改历史 2016年11月2日 下午9:59:50 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:59:50
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "DocumentRecord")
public class DocumentRecordModel {
	private Long docRecordId;// 编号
	private Date recordTime;// 统计日期
	private Integer sumDocToday;// 当前新文档数
	private Integer sumDocTotal;// 当前总文档数

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_record_id", unique = true, nullable = false)

	public Long getDocRecordId() {
		return docRecordId;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	@Column(name = "sum_doc_today")
	public Integer getSumDocToday() {
		return sumDocToday;
	}

	@Column(name = "sum_doc_total")
	public Integer getSumDocTotal() {
		return sumDocTotal;
	}

	public void setDocRecordId(Long docRecordId) {
		this.docRecordId = docRecordId;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public void setSumDocToday(Integer sumDocToday) {
		this.sumDocToday = sumDocToday;
	}

	public void setSumDocTotal(Integer sumDocTotal) {
		this.sumDocTotal = sumDocTotal;
	}

}
