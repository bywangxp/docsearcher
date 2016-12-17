package ustc.sse.water.docsearcher.service.ebi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;

@Transactional
public interface GlobalEbi {

	public ArrayList<DownloadRankModel> getDownloadRankModel();

	public List<TagRecordModel> getTagRecordModel();
}
