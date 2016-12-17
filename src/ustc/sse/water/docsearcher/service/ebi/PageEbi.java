package ustc.sse.water.docsearcher.service.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.PageModel;

@Transactional
public interface PageEbi {
	// public List<PageModel> getPageListByUserId11(Long userId);

	PageModel getPageByPageId(long pageid);

	public List<PageModel> getPageListByDocId(Long docId);
}
