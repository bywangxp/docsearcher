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
 * 修改历史 2016年10月31日 下午5:25:11 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午5:25:11
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Document")
public class DocumentModel {

	private Long docId;// 文档编号
	private String docTitle;// 文档标题
	private String docDescription;// 文档描述
	private String docLogo;// 文档logo
	private String fileType;// 文件类型
	private Integer sumPage;// 总页数
	private Integer sumCollection;// 总收藏量
	private Integer sumDownload;// 总下载数
	private Integer fileSize;// 文件大小
	private String docSaveKey;// 存储特征值
	private Short uploadReady;// 存储完成标志
	private Long userId;// 文档提供者
	private Date createTime;// 上传时间
	private Long tagId;// 所属分类
	private String docPreview;// 封面预览图
	private String docState;// 文档状态
	private Integer docValue;// 下载积分
	private Integer docRating;// 评分
	private Integer sumRatingUser;// 评分人数
	private String docLabel;// 主题标签
	private String docKeyword;// 主题关键字

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id", unique = true, nullable = false)
	public Long getDocId() {
		return docId;
	}

	@Column(name = "doc_title")
	public String getDocTitle() {
		return docTitle;
	}

	@Column(name = "doc_description")
	public String getDocDescription() {
		return docDescription;
	}

	@Column(name = "doc_logo")
	public String getDocLogo() {
		return docLogo;
	}

	@Column(name = "file_type")
	public String getFileType() {
		return fileType;
	}

	@Column(name = "sum_page")
	public Integer getSumPage() {
		return sumPage;
	}

	@Column(name = "sum_collection")
	public Integer getSumCollection() {
		return sumCollection;
	}

	@Column(name = "sum_download")
	public Integer getSumDownload() {
		return sumDownload;
	}

	@Column(name = "file_size")
	public Integer getFileSize() {
		return fileSize;
	}

	@Column(name = "doc_save_key")
	public String getDocSaveKey() {
		return docSaveKey;
	}

	@Column(name = "upload_ready")
	public Short getUploadReady() {
		return uploadReady;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	@Column(name = "tag_id", nullable = false)
	public Long getTagId() {
		return tagId;
	}

	@Column(name = "doc_preview")
	public String getDocPreview() {
		return docPreview;
	}

	@Column(name = "doc_state")
	public String getDocState() {
		return docState;

	}

	@Column(name = "doc_value")
	public Integer getDocValue() {
		return docValue;
	}

	@Column(name = "doc_rating")
	public Integer getDocRating() {
		return docRating;
	}

	@Column(name = "sum_rating_user")
	public Integer getSumRatingUser() {
		return sumRatingUser;
	}

	@Column(name = "doc_label")
	public String getDocLabel() {
		return docLabel;
	}

	@Column(name = "doc_keyword")
	public String getDocKeyword() {
		return docKeyword;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}

	public void setDocLogo(String docLogo) {
		this.docLogo = docLogo;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setSumPage(Integer sumPage) {
		this.sumPage = sumPage;
	}

	public void setSumCollection(Integer sumCollection) {
		this.sumCollection = sumCollection;
	}

	public void setSumDownload(Integer sumDownload) {
		this.sumDownload = sumDownload;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public void setDocSaveKey(String docSaveKey) {
		this.docSaveKey = docSaveKey;
	}

	public void setUploadReady(Short uploadReady) {
		this.uploadReady = uploadReady;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public void setDocPreview(String docPreview) {
		this.docPreview = docPreview;
	}

	public void setDocState(String docState) {
		this.docState = docState;
	}

	public void setDocValue(Integer docValue) {
		this.docValue = docValue;
	}

	public void setDocRating(Integer docRating) {
		this.docRating = docRating;
	}

	public void setSumRatingUser(Integer sumRatingUser) {
		this.sumRatingUser = sumRatingUser;
	}

	public void setDocLabel(String docLabel) {
		this.docLabel = docLabel;
	}

	public void setDocKeyword(String docKeyword) {
		this.docKeyword = docKeyword;
	}

}
