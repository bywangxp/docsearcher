package ustc.sse.water.docsearcher.service.ebi;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.TagModel;
import ustc.sse.water.docsearcher.model.UserModel;

@Transactional
public interface UserEbi {
	/**
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年10月30日 下午10:59:44 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */

	public UserModel find(UserModel userQueryModel);

	public Boolean upload(MultipartFile[] myfiles, String absolutePath, UserModel userModel)
			throws IOException, Exception;

	public DocumentModel getDocument(Long id);

	public List<PageModel> getPage(Long docId);

	public List<TagModel> getAllTags();

	public Long getDocumentsByTags(Long tagId);

	PageModel getPageByPageId(Integer pageid);

	public DocumentModel getDocumentsByDocId(Long docId);

	public UserModel getUserById(Long userId);

	public List<DocumentModel> searchSlides(String keyword);

}
