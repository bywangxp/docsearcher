package ustc.sse.water.docsearcher.service.ebi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.model.DocumentModel;

@Transactional
public interface DocumentEbi {

	public Boolean uploadFiles(MultipartFile[] myfiles, HttpServletRequest request) throws IOException, Exception;

	public DocumentModel getDocumentById(Long id);

	public Long getDocumentNumberByTag(Long tagId);

	public DocumentModel getDocumentByDocId(Long docId);

	public List<DocumentModel> searchDocumentListByKeyword(String keyword);

	public ArrayList<DocumentModel> getAllDocumentModel();

}
