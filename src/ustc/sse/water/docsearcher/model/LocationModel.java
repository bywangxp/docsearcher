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
 * 修改历史 2016年11月2日 下午10:12:23 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午10:12:23
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "Location")
public class LocationModel {

	private Long locId;// 地区编号
	private Integer locLevel;// 地区级别
	private String locName;// 地区名称
	private Long upperLocId;// 上一级地区

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loc_id", unique = true, nullable = false)

	public Long getLocId() {
		return locId;
	}

	@Column(name = "loc_level")
	public Integer getLocLevel() {
		return locLevel;
	}

	@Column(name = "loc_name")
	public String getLocName() {
		return locName;
	}

	@Column(name = "upper_loc_id")
	public Long getUpperLocId() {
		return upperLocId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public void setLocLevel(Integer locLevel) {
		this.locLevel = locLevel;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public void setUpperLocId(Long upperLocId) {
		this.upperLocId = upperLocId;
	}

}
