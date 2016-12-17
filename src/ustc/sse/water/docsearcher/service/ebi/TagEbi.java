package ustc.sse.water.docsearcher.service.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ustc.sse.water.docsearcher.model.TagModel;

@Transactional
public interface TagEbi {
	public List<TagModel> getAllTags();

	public TagModel getTagsByTagId(Long tagId);

}
