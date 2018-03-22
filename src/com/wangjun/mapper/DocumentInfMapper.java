package com.wangjun.mapper;

import com.wangjun.pojo.DocumentInf;
import com.wangjun.pojo.DocumentInfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentInfMapper {
    int countByExample(DocumentInfExample example);

    int deleteByExample(DocumentInfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentInf record);

    int insertSelective(DocumentInf record);

    List<DocumentInf> selectByExample(DocumentInfExample example);

    DocumentInf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentInf record, @Param("example") DocumentInfExample example);

    int updateByExample(@Param("record") DocumentInf record, @Param("example") DocumentInfExample example);

    int updateByPrimaryKeySelective(DocumentInf record);

    int updateByPrimaryKey(DocumentInf record);
}