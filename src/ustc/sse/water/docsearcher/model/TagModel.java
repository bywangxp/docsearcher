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
 * 修改历史 2016年10月31日 下午5:11:31 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午5:11:31
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Tag")
public class TagModel {
	private Long tagId;// 分类编号
	private String tagName;// 分类名称
	private String tagDescription;// 分类描述
	private Date createTime;// 创建时间
	private Long upperTagId;// 上一级分类

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id", unique = true, nullable = false)
	public Long getTagId() {
		return tagId;
	}

	@Column(name = "tag_name")
	public String getTagName() {
		return tagName;
	}

	@Column(name = "tag_description")
	public String getTagDescription() {
		return tagDescription;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	@Column(name = "upper_tag_id", nullable = false)
	public Long getUpperTagId() {
		return upperTagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpperTagId(Long upperTagId) {
		this.upperTagId = upperTagId;
	}

}
