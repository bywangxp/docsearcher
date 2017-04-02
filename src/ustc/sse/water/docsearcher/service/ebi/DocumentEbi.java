package ustc.sse.water.docsearcher.service.ebi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.util.Pager;

@Transactional
public interface DocumentEbi {

	public Boolean uploadFiles(MultipartFile[] smyfiles, HttpServletRequest request) throws IOException, Exception;

	public DocumentModel getDocumentById(Long id);

	public Long getDocumentNumberByTag(Long tagId);

	public DocumentModel getDocumentByDocId(Long docId);

	public List<DocumentModel> searchDocumentListByKeyword(String keyword);

	public ArrayList<DocumentModel> getAllDocumentModel();

	public ArrayList<DocumentModel> getDocumentByUserId(Long userId,Pager pager);

}
