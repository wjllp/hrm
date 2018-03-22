package com.wangjun.service;

import java.util.List;

import com.wangjun.pojo.DocumentInf;
import com.wangjun.util.tag.PageModel;


public interface DocumentService {
	/**
	 * 获得所有文档
	 * @return Document对象的List集合
	 * */
	List<DocumentInf> findDocument(DocumentInf document,PageModel pageModel);
	
	/**
	 * 添加文档
	 * @param Document 文件对象
	 * */
	void addDocument(DocumentInf document);
	
	/**
	 * 根据id查询文档
	 * @param id
	 * @return 文档对象
	 * */
	DocumentInf findDocumentById(Integer id);
	
	/**
	 * 根据id删除文档
	 * @param id
	 * */
	public void removeDocumentById(Integer id);
	
	/**
	 * 修改文档
	 * @param Document 公告对象
	 * */
	void modifyDocument(DocumentInf document);
}
