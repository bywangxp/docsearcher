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
 * 修改历史 2016年11月2日 下午9:47:55 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:47:55
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "UserRecord")
public class UserRecordModel {

	private Long userRecordId;// 用户编号
	private Date recordTime;// 统计日期
	private Integer sumUserToday;// 当日新用户数
	private Integer sumUserTotal;// 当前总用户数

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_record_id", unique = true, nullable = false)
	public Long getUserRecordId() {
		return userRecordId;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	@Column(name = "sum_user_today")
	public Integer getSumUserToday() {
		return sumUserToday;
	}

	@Column(name = "sum_user_total")
	public Integer getSumUserTotal() {
		return sumUserTotal;
	}

	public void setUserRecordId(Long userRecordId) {
		this.userRecordId = userRecordId;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public void setSumUserToday(Integer sumUserToday) {
		this.sumUserToday = sumUserToday;
	}

	public void setSumUserTotal(Integer sumUserTotal) {
		this.sumUserTotal = sumUserTotal;
	}

}
