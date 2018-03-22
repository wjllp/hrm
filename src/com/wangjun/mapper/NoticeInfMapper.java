package com.wangjun.mapper;

import com.wangjun.pojo.NoticeInf;
import com.wangjun.pojo.NoticeInfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeInfMapper {
    int countByExample(NoticeInfExample example);

    int deleteByExample(NoticeInfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeInf record);

    int insertSelective(NoticeInf record);

    List<NoticeInf> selectByExampleWithBLOBs(NoticeInfExample example);

    List<NoticeInf> selectByExample(NoticeInfExample example);

    NoticeInf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticeInf record, @Param("example") NoticeInfExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticeInf record, @Param("example") NoticeInfExample example);

    int updateByExample(@Param("record") NoticeInf record, @Param("example") NoticeInfExample example);

    int updateByPrimaryKeySelective(NoticeInf record);

    int updateByPrimaryKeyWithBLOBs(NoticeInf record);

    int updateByPrimaryKey(NoticeInf record);
}