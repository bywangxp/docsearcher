package ustc.sse.water.docsearcher.service.ebo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.dao.dao.DocumentDao;
import ustc.sse.water.docsearcher.dao.dao.PageDao;
import ustc.sse.water.docsearcher.dao.dao.TagDao;
import ustc.sse.water.docsearcher.dao.dao.UserDao;
import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.TagModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;
import ustc.sse.water.docsearcher.util.PPTUtils;

@Service("userEbi")
public class UserEbo implements UserEbi {
	@Resource
	private UserDao userDao;
	@Resource
	private DocumentDao documentDao;
	@Resource
	private PageDao pageDao;
	@Resource
	private TagDao tagDao;

	@Override
	public UserModel find(UserModel userModel) {

		return userDao.find(userModel);
	}

	@Override
	public Boolean upload(MultipartFile[] myfiles, String absolutePath, UserModel userModel) throws Exception {
		Boolean flag = false;// 文件转换成功的标志，默认是失败的

		// PageModel pageModel = new PageModel();
		int i = 0;
		for (MultipartFile myfile : myfiles) {
			DocumentModel documentModel = new DocumentModel();// 存入数据库的文档Model
			++i;
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
				System.out.println("i:" + i);
				return false;
			} else {

				int size = (int) myfile.getSize();
				// 此处调用dao插入文档大小，以KB为单位
				size = size / 1000;

				System.out.println("文件大小：" + size);
				String soureceName = myfile.getOriginalFilename();// 此文件名是带后缀的文件名（无路径）
				// 对用户上传的文件名字进行调整,去掉文字
				System.out.println("调整前的文件名" + soureceName);

				String adjustName = PPTUtils.adjustName(soureceName);
				adjustName = PPTUtils.getFilename(adjustName);
				// 不带文件后缀
				System.out.println("调整后的文件名" + adjustName);
				System.out.println("========================================");
				InputStream inputStream = myfile.getInputStream();
				if (PPTUtils.isPPT(soureceName) || PPTUtils.isPPTX(soureceName)) {
					// 下面的操作是再确定文档无误的情况下进行，后期添加异常处理，

					documentModel.setDocTitle(soureceName);
					String docDescription = "用户自定义描述信息";
					documentModel.setDocDescription(docDescription);
					// 文档对应图标，如果用户未指定则默认。默认地址是：
					String logoPath = "UserFiles/defalutPic/defalutPic.png";
					documentModel.setDocLogo(logoPath);
					String fileType = null;
					if (PPTUtils.isPPT(soureceName)) {
						fileType = "ppt";
					} else {
						fileType = "pptx";
						System.out.println("filetype" + fileType);
					}
					documentModel.setFileType(fileType);
					// 初始化文档页数为0，在ppt处理过程中，获取到对应的document获取到真是的页面数后写入
					documentModel.setSumPage(0);
					documentModel.setSumCollection(0);
					documentModel.setSumDownload(0);
					documentModel.setFileSize(size);
					documentModel.setDocSaveKey(adjustName);// 文件存储特征值:指向文档存储地址的特征值,通过该地址拼出文件路径
					documentModel.setUploadReady((short) 1);// 文件上传完成标志
					documentModel.setUserId(userModel.getUserId());
					documentModel.setCreateTime(new Date());
					int tagId = 1;// 文件所属分类，需要与分类表同步
					documentModel.setTagId((long) tagId);
					String docPrecView = adjustName + "_1.png";
					// documentModel.setDocPreview(docPrecView);//
					String docState = "public";// 默认为公开
					documentModel.setDocState(docState);// 文档状态，如是否公开 public
					// 文件积分，默认分数是2
					int docValue = 2;
					documentModel.setDocValue(docValue);
					// 文件评分，1-10分，初始化为0分，由其他用户评分，后求平均值后获得
					int docRating = 0;
					documentModel.setDocRating(docRating);
					documentModel.setSumRatingUser(0); // 评分人数，初始为0
					// 文档的关键词，由上传者设定，以英文符号分隔

					// 文档标签，由管理员设定
					String docLabel = "文档标签，管理员设定";
					documentModel.setDocLabel(docLabel);
					documentModel.setTagId((long) 8);
					String docKeyword = "用户定义的文档关键字，以英文符号分隔";
					// documentModel.setDocKeyword(docKeyword);
					// 待完善，此处应该统计转换失败和失败的文件信息给前台

					// 在文档处理前保存文档信息，返回id号
					Long documentId = documentDao.save(documentModel);

					// 调用文档处理方法
					getFile(inputStream, adjustName, fileType, absolutePath, documentId, documentModel, documentDao,
							pageDao);
					// 处理成功后对Dao做的操作
					System.out.println("存储文档信息成功" + documentId);

				}

			}

		}
		System.out.println("i" + i);
		return flag;
	}

	/**
	 * 
	 * 调用PPT分解前的准备工作 <br>
	 * <p>
	 * 修改历史: 2016年10月31日 下午9:46:17 修改人 修改说明 <br>
	 * 
	 * @param absolutePath2
	 * 
	 * @param documentId
	 * @param documentDao2
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	// 还需要完善，如果转换失败，给方法返回一个参数

	public static void getFile(InputStream inputStream, String adjustname, String fileType, String absolutePath,
			Long documentId, DocumentModel documentModel, DocumentDao documentDao, PageDao pageDao) throws Exception {
		Long currentTime = System.currentTimeMillis();
		String name_with_suffix = null;
		// 此处还需要进一步判断文件是否是改了后缀后的文件
		if ("ppt".equals(fileType)) {
			name_with_suffix = currentTime + "_" + "PPT_" + adjustname + ".ppt";
		} else if ("pptx".equals(fileType)) {
			name_with_suffix = currentTime + "_" + "PPTX_" + adjustname + ".pptx";
		}

		// 生成用户对应的文件夹。此步骤可以放入用户注册的阶段
		PPTUtils.createDir(absolutePath + "UserFiles");
		FileOutputStream out = new FileOutputStream(absolutePath + "UserFiles\\" + "\\" + name_with_suffix);
		System.out.println(absolutePath + "UserFiles\\" + name_with_suffix);
		// 文件写
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		out.close();
		System.out.println("文件输出成功");
		// 生成该文件的目录结构
		String name_no_suffix = PPTUtils.getFilename(name_with_suffix);
		// 将路径存储进去
		documentModel.setDocPreview(name_no_suffix + "_1.png");
		documentModel.setDocSaveKey(name_no_suffix);
		documentDao.update(documentModel);
		PPTUtils.createDir(absolutePath + "UserFiles\\" + "\\" + name_no_suffix + "\\images");
		PPTUtils.createDir(absolutePath + "UserFiles\\" + "\\" + name_no_suffix + "\\pictures");
		PPTUtils.createDir(absolutePath + "UserFiles\\" + "\\" + name_no_suffix + "\\texts");
		PPTUtils.createDir(absolutePath + "UserFiles\\" + "\\" + name_no_suffix + "\\pdf");
		System.out.println("文件录入，创建文件夹成功");
		// 用同一个输入流操作
		int size = 0;
		if ("ppt".equals(fileType)) {
			System.out.println("ppt操作。。。");
			// HSLFSlideShowImpl hslfSlideShowImpl = new
			// HSLFSlideShowImpl(inputStream);
			ExtractPPT.get_Texts_PPT(name_no_suffix, absolutePath);
			ExtractPPT.get_Pictures_PPT(name_no_suffix, absolutePath);
			size = ExtractPPT.get_Images_PPT(name_no_suffix, absolutePath, documentId, documentDao, pageDao);
		} else {
			System.out.println("pptx操作。。。");
			ExtractPPT.get_Texts_PPTX(name_no_suffix, absolutePath);
			ExtractPPT.get_Pictures_PPTX(name_no_suffix, absolutePath);
			size = ExtractPPT.get_Images_PPTX(name_no_suffix, absolutePath, documentId, documentDao, pageDao);
		}
		// 转pdf
		ConvertToPdf ptf = new ConvertToPdf();
		ptf.convetToPdf(absolutePath, name_no_suffix, size);
		// ptf.t("1477383417932_PPTX_test1",50);
		System.out.println("缩列图转pdf成功");
	}

	@Override
	public DocumentModel getDocument(Long id) {

		DocumentModel documentModel = userDao.getDocument(id);
		return documentModel;

	}

	@Override
	public List<PageModel> getPage(Long userId) {

		List<PageModel> list = userDao.getPage(userId);
		return list;
	}

	@Override
	public List<TagModel> getAllTags() {

		return tagDao.getAllTags();
	}

	@Override
	public Long getDocumentsByTags(Long tagId) {
		Long documentNum = documentDao.getDocumentsByTags(tagId);
		return documentNum;
	}

	@Override
	public PageModel getPageByPageId(Integer pageid) {
		PageModel pageMode = pageDao.getPageByPageId(pageid);
		return pageMode;
	}

	@Override
	public DocumentModel getDocumentsByDocId(Long docId) {
		// TODO Auto-generated method stub
		return documentDao.getDocumentsByDocId(docId);
	}

	@Override
	public UserModel getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	@Override
	public List<DocumentModel> searchSlides(String keyword) {
		// TODO Auto-generated method stub
		return documentDao.searchSlides(keyword);
	}

}
