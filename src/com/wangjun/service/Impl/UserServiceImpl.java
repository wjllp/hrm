package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.UserInfMapper;
import com.wangjun.pojo.UserInf;
import com.wangjun.pojo.UserInfExample;
import com.wangjun.pojo.UserInfExample.Criteria;
import com.wangjun.service.UserService;
import com.wangjun.util.tag.PageModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfMapper userInfMapper;

	@Override
	public UserInf login(String loginname, String password) {
		// TODO Auto-generated method stub
		UserInfExample example = new UserInfExample();
		com.wangjun.pojo.UserInfExample.Criteria criteria = example.createCriteria();
		criteria.andLoginnameEqualTo(loginname);
		criteria.andPasswordEqualTo(password);
		List<UserInf> list = userInfMapper.selectByExample(example);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserInf findUserById(Integer id) {
		// TODO Auto-generated method stub
		UserInfExample example = new UserInfExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<UserInf> list = userInfMapper.selectByExample(example);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserInf> findUser(PageModel pageModel) {
		// TODO Auto-generated method stub
		UserInfExample example = new UserInfExample();
		List<UserInf> list = userInfMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<UserInf> users = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < recordCount) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					users.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < recordCount; i++) {
					users.add(list.get(i));
				}
			}
			return users;
		} else {
			return null;
		}
	}

	@Override
	public void removeUserById(Integer id) {
		// TODO Auto-generated method stub
		userInfMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyUser(UserInf user) {
		// TODO Auto-generated method stub
		userInfMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void addUser(UserInf user) {
		// TODO Auto-generated method stub
		userInfMapper.insert(user);
	}

}
