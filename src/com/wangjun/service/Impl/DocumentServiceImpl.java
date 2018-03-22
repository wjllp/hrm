package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.DocumentInfMapper;
import com.wangjun.pojo.DocumentInf;
import com.wangjun.pojo.DocumentInfExample;
import com.wangjun.service.DocumentService;
import com.wangjun.util.tag.PageModel;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentInfMapper documentMapper;

	@Override
	public List<DocumentInf> findDocument(DocumentInf document, PageModel pageModel) {
		// TODO Auto-generated method stub
		DocumentInfExample example = new DocumentInfExample();
		List<DocumentInf> list = documentMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<DocumentInf> documents = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < recordCount) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					documents.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < recordCount; i++) {
					documents.add(list.get(i));
				}
			}
			return documents;
		} else {
			return null;
		}
	}

	@Override
	public void addDocument(DocumentInf document) {
		// TODO Auto-generated method stub
		documentMapper.insert(document);
	}

	@Override
	public DocumentInf findDocumentById(Integer id) {
		// TODO Auto-generated method stub
		return documentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void removeDocumentById(Integer id) {
		// TODO Auto-generated method stub
		documentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyDocument(DocumentInf document) {
		// TODO Auto-generated method stub
		documentMapper.updateByPrimaryKeySelective(document);
	}

}
