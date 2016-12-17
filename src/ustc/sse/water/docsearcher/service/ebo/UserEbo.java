package ustc.sse.water.docsearcher.service.ebo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.UserDao;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:24 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:24
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("userEbi")
public class UserEbo implements UserEbi {
	@Resource
	private UserDao userDao;

	@Override
	public UserModel findUser(UserModel userModel) {

		return userDao.findUser(userModel);
	}

	@Override
	public UserModel getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

}
