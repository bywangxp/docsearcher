package ustc.sse.water.docsearcher.util.constant;

public class PublicConstants {
	public static final String DEFAULT_PPT_PNG = "ppt的图片";
	public static final String DEFAULT_RANK_PNG = "排行榜的图片";
	public static final String SEARCH_RECORD_LOGO = "搜素记录的图片";
	public static final String IF_ADD_FAV = "收藏了页面，返回的图片";
	public static final String IF_NO_ADD_FAV = "没有收藏页面，返回的图片";

	public static final int GENDER_MALE = 0;// 值为0，代表男性
	public static final int GENDER_FEMALE = 1;// 值为1，代表女性
	public static final String ROLE_ADMIN = "管理员";// 代表管理员用户类型
	public static final String ROLE_COMMEN_USER = "注册用户";// "没有收藏页面，返回的图片"
	public static final String ROLE_GUEST = "游客";// "没有收藏页面，返回的图片"
	public static final String ACCOUNT_ON_USE = "注册用户";// 代表未登录的游客
	public static final String ACCOUNT_STOPPED = "暂时停用";// 代表被管理员停用的用户
	public static final String ACCOUNT_DELETED = "已删除";// 代表被管理员停用的用户

	public static final String FILE_PUBLISH = "正常发布";// 代表正常发布的PPT文档
	public static final String FILE_FOLD = "被折叠";// 代表被管理员隐藏，暂时无法公开的PPT文档
	public static final String FILE_DELETE = "被删除";// 代表被管理员或者上传者删除的PPT文档
	public static final String DOC_PUBLIC = "公开";// 代表可以向全网公开的文档
	public static final String DOC_PRIVATE = "私密";// 代表仅能由上传者查看的文档

}
