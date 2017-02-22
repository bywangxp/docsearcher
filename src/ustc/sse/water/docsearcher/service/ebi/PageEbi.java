package ustc.sse.water.docsearcher.service.ebi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.PageModel;

@Transactional
public interface PageEbi {
	// public List<PageModel> getPageListByUserId11(Long userId);

	PageModel getPageByPageId(long pageid);

	public List<PageModel> getPageListByDocId(Long docId);

	String composePDF(HttpServletRequest request, long[] pagesId, String filename);

	int whetherAddFav(HttpServletRequest request, Long pageId);

	void saveCollection(int flag, Long pageId, HttpServletRequest request);

	List<PageModel> getPageByKeyWord(String keyword);

	PageModel getPageModelBySaveKey(String string);
}
