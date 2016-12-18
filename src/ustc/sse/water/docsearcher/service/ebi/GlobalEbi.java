package ustc.sse.water.docsearcher.service.ebi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;

@Transactional
public interface GlobalEbi {

	public ArrayList<DownloadRankModel> getDownloadRankModel();

	public List<TagRecordModel> getTagRecordModel();

	public void saveDownLoadRecord(HttpServletRequest request, long[] pagesId);

	public void saveSearchRecord(HttpServletRequest request, String keyword);
}
