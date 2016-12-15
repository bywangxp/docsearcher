package ustc.sse.water.docsearcher.model;

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
 * 修改历史 2016年10月31日 下午5:57:01 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午5:57:01
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Page")
public class PageModel {
	private Long pageId;// 页面编号
	private Long docId;// 所属文档
	private String pageDescription;// 页面描述
	private String pagePreview;// 页面预览图
	private String pageSaveKey;// 存储特征值
	private Integer pageNo;// 所属文档内页码

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "page_id", unique = true, nullable = false)
	public Long getPageId() {
		return pageId;
	}

	@Column(name = "doc_id", nullable = false)
	public Long getDocId() {
		return docId;
	}

	@Column(name = "page_description")
	public String getPageDescription() {
		return pageDescription;
	}

	@Column(name = "page_preview")
	public String getPagePreview() {
		return pagePreview;
	}

	@Column(name = "page_save_key")
	public String getPageSaveKey() {
		return pageSaveKey;
	}

	@Column(name = "page_no")
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public void setPagePreview(String pagePreview) {
		this.pagePreview = pagePreview;
	}

	public void setPageSaveKey(String pageSaveKey) {
		this.pageSaveKey = pageSaveKey;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
