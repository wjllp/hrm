package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.DeptInfMapper;
import com.wangjun.pojo.DeptInf;
import com.wangjun.pojo.DeptInfExample;
import com.wangjun.service.DeptService;
import com.wangjun.util.tag.PageModel;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptInfMapper deptMapper;

	@Override
	public List<DeptInf> findDept(DeptInf dept, PageModel pageModel) {
		// TODO Auto-generated method stub
		DeptInfExample example = new DeptInfExample();
		List<DeptInf> list = deptMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<DeptInf> depts = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < recordCount) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					depts.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < recordCount; i++) {
					depts.add(list.get(i));
				}
			}
			return depts;
		} else {
			return null;
		}
	}

	@Override
	public List<DeptInf> findAllDept() {
		// TODO Auto-generated method stub
		DeptInfExample example = new DeptInfExample();
		List<DeptInf> list = deptMapper.selectByExample(example);
		return list;
	}

	@Override
	public void removeDeptById(Integer id) {
		// TODO Auto-generated method stub
		deptMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addDept(DeptInf dept) {
		// TODO Auto-generated method stub
		deptMapper.insert(dept);
	}

	@Override
	public DeptInf findDeptById(Integer id) {
		// TODO Auto-generated method stub
		return deptMapper.selectByPrimaryKey(id);
	}

	@Override
	public void modifyDept(DeptInf dept) {
		// TODO Auto-generated method stub
		deptMapper.updateByPrimaryKeySelective(dept);
	}

}
