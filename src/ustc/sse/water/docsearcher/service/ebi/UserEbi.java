package ustc.sse.water.docsearcher.service.ebi;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.UserModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:01 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:01
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Transactional
public interface UserEbi {
	public UserModel findUser(UserModel userQueryModel);

	public UserModel getUserById(Long userId);

	public Integer changeUserInfo(UserModel user);
}
