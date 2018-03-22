package com.wangjun.service;

import java.util.List;

import com.wangjun.pojo.NoticeInf;
import com.wangjun.util.tag.PageModel;


public interface NoticeService {
	/**
	 * 获得所有公告
	 * @return Notice对象的List集合
	 * */
	List<NoticeInf> findNotice(NoticeInf notice,PageModel pageModel);
	
	/**
	 * 根据id查询公告
	 * @param id
	 * @return 公告对象
	 * */
	NoticeInf findNoticeById(Integer id);
	
	/**
	 * 根据id删除公告
	 * @param id
	 * */
	public void removeNoticeById(Integer id);
	
	/**
	 * 添加公告
	 * @param Notice 公告对象
	 * */
	void addNotice(NoticeInf notice);
	
	/**
	 * 修改公告
	 * @param Notice 公告对象
	 * */
	void modifyNotice(NoticeInf notice);
}
