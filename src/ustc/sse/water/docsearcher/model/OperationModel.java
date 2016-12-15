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
 * 修改历史 2016年10月31日 下午4:53:49 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月31日 下午4:53:49
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Entity
@Table(name = "Opertion")
public class OperationModel {
	private Long opId;// 操作编号
	private String opName;// 操作名称

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "op_id", unique = true, nullable = false)
	public Long getOpId() {
		return opId;
	}

	@Column(name = "op_name")
	public String getOpName() {
		return opName;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

}
