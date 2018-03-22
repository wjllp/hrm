package com.wangjun.service;

import java.util.List;


import com.wangjun.pojo.DeptInf;
import com.wangjun.util.tag.PageModel;

public interface DeptService {
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<DeptInf> findDept(DeptInf dept,PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<DeptInf> findAllDept();
	
	/**
	 * 根据id删除部门
	 * @param id
	 * */
	public void removeDeptById(Integer id);
	
	/**
	 * 添加部门
	 * @param dept 部门对象
	 * */
	void addDept(DeptInf dept);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return 部门对象
	 * */
	DeptInf findDeptById(Integer id);
	
	/**
	 * 修改部门
	 * @param dept 部门对象
	 * */
	void modifyDept(DeptInf dept);
}
