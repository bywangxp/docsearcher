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
 * 修改历史 2016年10月31日 下午6:14:57 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午6:14:57
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Download")
public class DownloadModel {
	private Long downloadId;// 下载编号
	private Long userId;// 下载者
	private Long pageId;// 下载页面
	private Date downloadTime;// 下载时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dowload_id", unique = true, nullable = false)
	public Long getDownloadId() {
		return downloadId;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "page_id", nullable = false)
	public Long getPageId() {
		return pageId;
	}

	@Column(name = "download_time")
	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadId(Long downloadId) {
		this.downloadId = downloadId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

}
