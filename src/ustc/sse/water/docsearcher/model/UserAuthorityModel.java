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
 * 修改历史 2016年10月31日 下午4:59:23 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午4:59:23
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Entity
@Table(name = "UserAuthority")
public class UserAuthorityModel {
	private Long authId;// 编号
	private Long userTypeId;// 用户类型
	private Long opId;// 可用操作
	private Long userId;// 添加人
	private Long createTime;// 添加时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_id", unique = true, nullable = false)
	public Long getAuthId() {
		return authId;
	}

	@Column(name = "user_type_id", nullable = false)
	public Long getUserTypeId() {
		return userTypeId;
	}

	@Column(name = "op_id", nullable = false)
	public Long getOpId() {
		return opId;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "createTime")
	public Long getCreateTime() {
		return createTime;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
