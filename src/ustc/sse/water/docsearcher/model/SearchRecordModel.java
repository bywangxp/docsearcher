package ustc.sse.water.docsearcher.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SearchRecord")
public class SearchRecordModel {

	private Long searcheId;// 检索编号
	private String searchKeyword;// 检索关键字
	private Long userId;// 检索用户
	private String searchLogo;// logo
	private Date searchTime;// 检索日期

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "search_id", unique = true, nullable = false)
	public Long getSearcheId() {
		return searcheId;
	}

	@Column(name = "search_keyword")
	public String getSearchKeyword() {
		return searchKeyword;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "search_logo")
	public String getSearchLogo() {
		return searchLogo;
	}

	@Column(name = "search_time")
	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearcheId(Long searcheId) {
		this.searcheId = searcheId;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setSearchLogo(String searchLogo) {
		this.searchLogo = searchLogo;
	}

	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

}
