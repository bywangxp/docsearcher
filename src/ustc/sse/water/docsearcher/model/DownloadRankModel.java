package ustc.sse.water.docsearcher.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Table(name = "Collection")@Column(name = "user_id", nullable = false) 类型名
 *             <br>
 *             功能描述
 *             <p>
 *             修改历史 2016年11月2日 下午9:08:25 修改人 <br>
 *             修改说明 <br>
 *             <p>
 *             Copyright: Copyright (c) 2016年11月2日 下午9:08:25
 *             <p>
 *             Company: 中科大软件学院
 *             <p>
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "DownloadRank")
public class DownloadRankModel {
	private Long downRankId;// 编号
	private Long docId;// 文档编号
	private Integer sumDownload;// 总下载数
	private Integer downRank;// 当前排名
	private Date recordTime;// 最近统计时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "down_rank_id", unique = true, nullable = false)
	public Long getDownRankId() {
		return downRankId;
	}

	@Column(name = "doc_id", nullable = false)
	public Long getDocId() {
		return docId;
	}

	@Column(name = "sum_download")
	public Integer getSumDownload() {
		return sumDownload;
	}

	@Column(name = "down_rank")
	public Integer getDownRank() {
		return downRank;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	public void setDownRankId(Long downRankId) {
		this.downRankId = downRankId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public void setSumDownload(Integer sumDownload) {
		this.sumDownload = sumDownload;
	}

	public void setDownRank(Integer downRank) {
		this.downRank = downRank;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

}
