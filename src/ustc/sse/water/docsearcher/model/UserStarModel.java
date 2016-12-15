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
 * 修改历史 2016年11月2日 下午9:40:53 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:40:53
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
/**
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月2日 下午9:43:39 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月2日 下午9:43:39
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 sbywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Entity
@Table(name = "UserStar")
public class UserStarModel {
	private Long userStarId;// 编号
	private Long userId;// 用户编号
	private Integer userRating;// 用户评分
	private Integer userRank;// 当前排名
	private Date recordTime;// 最近统计时间

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_star_id", unique = true, nullable = false)
	public Long getUserStarId() {
		return userStarId;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "user_rating")
	public Integer getUserRating() {
		return userRating;
	}

	@Column(name = "user_rank")
	public Integer getUserRank() {
		return userRank;
	}

	@Column(name = "record_time")
	public Date getRecordTime() {
		return recordTime;
	}

	public void setUserStarId(Long userStarId) {
		this.userStarId = userStarId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserRating(Integer userRating) {
		this.userRating = userRating;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

}
