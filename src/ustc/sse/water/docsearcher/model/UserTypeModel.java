package ustc.sse.water.docsearcher.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月31日 下午4:52:39 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午4:52:39
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Entity
@Table(name = "UserType")
public class UserTypeModel {

	private Long UserTypeId;// 用戶类型编号
	private String UserTypeName;// y用户类型名称

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_type_id", unique = true, nullable = false)
	public Long getUserTypeId() {
		return UserTypeId;
	}

	@Column(name = "user_type_name")
	public String getUserTypeName() {
		return UserTypeName;
	}

	public void setUserTypeId(Long userTypeId) {
		UserTypeId = userTypeId;
	}

	public void setUserTypeName(String userTypeName) {
		UserTypeName = userTypeName;
	}

}
