package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.NoticeInfMapper;
import com.wangjun.pojo.NoticeInf;
import com.wangjun.pojo.NoticeInfExample;
import com.wangjun.service.NoticeService;
import com.wangjun.util.tag.PageModel;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeInfMapper noticeMapper;

	@Override
	public List<NoticeInf> findNotice(NoticeInf notice, PageModel pageModel) {
		// TODO Auto-generated method stub
		NoticeInfExample example = new NoticeInfExample();
		List<NoticeInf> list = noticeMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<NoticeInf> notices = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < recordCount) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					notices.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < recordCount; i++) {
					System.out.println(list.get(i).toString());
					notices.add(list.get(i));
				}
			}
			return notices;
		} else {
			return null;
		}
	}

	@Override
	public NoticeInf findNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void removeNoticeById(Integer id) {
		// TODO Auto-generated method stub
		noticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addNotice(NoticeInf notice) {
		// TODO Auto-generated method stub
		noticeMapper.insert(notice);
	}

	@Override
	public void modifyNotice(NoticeInf notice) {
		// TODO Auto-generated method stub
		noticeMapper.updateByPrimaryKeySelective(notice);
	}

}
