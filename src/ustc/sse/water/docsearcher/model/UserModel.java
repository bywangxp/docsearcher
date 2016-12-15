package ustc.sse.water.docsearcher.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月30日 下午7:26:05 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月30日 下午7:26:05
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Entity
@Table(name = "User")
public class UserModel {

	private Long userId;// 用户编号
	private String userName;// 用户名
	private String userPassword;// 用户密码
	private Integer userGender;// 性别 0-男，1-女
	private String userPhoto;// 头像
	private String userNickName;// 昵称
	private Date userBirthday;// 生日
	private String userLocation;// 所在地
	private String userQq;// 用户QQ
	private String userEmail;// 电子邮箱
	private String userPhone;// 联系电话
	private String userDescription;// 个性签名
	private String userHomePage;// 个人主页
	private Long userTypeId;// 用户类型
	private String userState;// 用户状态
	private Integer sumPublicDoc;// 公开文档数
	private Integer sumPrivateDoc;// 私有文档数
	private Integer userCredit;// 个人积分
	private Date createTime;// 创建时间

	public UserModel() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return userName;
	}

	@Column(name = "user_password", nullable = false)
	public String getUserPassword() {
		return userPassword;
	}

	@Column(name = "user_gender")
	public Integer getUserGender() {
		return userGender;
	}

	@Column(name = "user_photo")
	public String getUserPhoto() {
		return userPhoto;
	}

	@Column(name = "user_nickname")
	public String getUserNickName() {
		return userNickName;
	}

	@Column(name = "user_birthday")
	public Date getUserBirthday() {
		return userBirthday;
	}

	@Column(name = "user_location")
	public String getUserLocation() {
		return userLocation;
	}

	@Column(name = "user_qq")
	public String getUserQq() {
		return userQq;
	}

	@Column(name = "user_email")
	public String getUserEmail() {
		return userEmail;
	}

	@Column(name = "user_phone")
	public String getUserPhone() {
		return userPhone;
	}

	@Column(name = "user_description")
	public String getUserDescription() {
		return userDescription;
	}

	@Column(name = "user_homepage")
	public String getUserHomePage() {
		return userHomePage;
	}

	@Column(name = "user_type_id", nullable = false)
	public Long getUserTypeId() {
		return userTypeId;
	}

	@Column(name = "user_state")
	public String getUserState() {
		return userState;
	}

	@Column(name = "sum_public_doc")
	public Integer getSumPublicDoc() {
		return sumPublicDoc;
	}

	@Column(name = "sum_private_doc")
	public Integer getSumPrivateDoc() {
		return sumPrivateDoc;
	}

	@Column(name = "user_credit")
	public Integer getUserCredit() {
		return userCredit;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public void setUserHomePage(String userHomePage) {
		this.userHomePage = userHomePage;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public void setSumPublicDoc(Integer sumPublicDoc) {
		this.sumPublicDoc = sumPublicDoc;
	}

	public void setSumPrivateDoc(Integer sumPrivateDoc) {
		this.sumPrivateDoc = sumPrivateDoc;
	}

	public void setUserCredit(Integer userCredit) {
		this.userCredit = userCredit;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
