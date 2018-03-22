package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.EmployeeInfMapper;
import com.wangjun.pojo.EmployeeInf;
import com.wangjun.pojo.EmployeeInfExample;
import com.wangjun.service.EmployeeService;
import com.wangjun.util.tag.PageModel;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeInfMapper employeeMapper;

	@Override
	public List<EmployeeInf> findEmployee(EmployeeInf employee, PageModel pageModel) {
		// TODO Auto-generated method stub
		EmployeeInfExample example = new EmployeeInfExample();
		List<EmployeeInf> list = employeeMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<EmployeeInf> employees = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < list.size()) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					employees.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < list.size(); i++) {
					employees.add(list.get(i));
				}
			}
			return employees;
		} else {
			return null;
		}
	}

	@Override
	public void removeEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public EmployeeInf findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addEmployee(EmployeeInf employee) {
		// TODO Auto-generated method stub
		System.out.println("添加员工"+employee.toString());
		employeeMapper.insert(employee);
	}

	@Override
	public void modifyEmployee(EmployeeInf employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

}
