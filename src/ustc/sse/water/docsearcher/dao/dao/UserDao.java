package ustc.sse.water.docsearcher.dao.dao;

import ustc.sse.water.docsearcher.model.UserModel;

/**
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月30日 下午10:50:27 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月30日 下午10:50:27
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public interface UserDao {

	UserModel findUser(UserModel userModel);

	UserModel getUserById(Long userId);

	Integer changeUserInfo(UserModel user);

}
