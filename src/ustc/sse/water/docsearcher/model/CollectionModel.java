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
 * 修改历史 2016年10月31日 下午6:07:49 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午6:07:49
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Collection")
public class CollectionModel {
	private Long collectId;// 收藏ID
	private Long userId;// 收藏者
	private Long docId; // 收藏文档
	private Date collectTime;// 收藏时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collect_id", unique = true, nullable = false)
	public Long getCollectId() {
		return collectId;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "doc_id", nullable = false)
	public Long getDocId() {
		return docId;
	}

	@Column(name = "collect_time")
	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

}
