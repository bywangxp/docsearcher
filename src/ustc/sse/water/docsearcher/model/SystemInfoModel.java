package ustc.sse.water.docsearcher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SystemInfo")
public class SystemInfoModel {

	private Long sysInfoId;// 编号
	private String websiteName;// 网站名称
	private String domainName;// 网站域名
	private String websiteLogo;// 网站logo
	private String address;// 网站地址
	private String phone;// 联系电话
	private String email;// 电子邮箱
	private String icpRecord;// 备案号
	private String introduction;// 基本介绍
	private String techSupport;// 技术支持

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sys_info_id", unique = true, nullable = false)
	public Long getSysInfoId() {
		return sysInfoId;
	}

	@Column(name = "website_name")
	public String getWebsiteName() {
		return websiteName;
	}

	@Column(name = "domain_name")
	public String getDomainName() {
		return domainName;
	}

	@Column(name = "website_logo")
	public String getWebsiteLogo() {
		return websiteLogo;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "icp_record")
	public String getIcpRecord() {
		return icpRecord;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}

	@Column(name = "tech_support")
	public String getTechSupport() {
		return techSupport;
	}

	public void setSysInfoId(Long sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public void setWebsiteLogo(String websiteLogo) {
		this.websiteLogo = websiteLogo;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIcpRecord(String icpRecord) {
		this.icpRecord = icpRecord;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}

}
