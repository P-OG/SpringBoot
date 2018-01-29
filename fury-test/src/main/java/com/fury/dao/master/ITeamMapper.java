package com.fury.dao.master;

import com.fury.domain.TeamVo;
//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by AB058114 on 2018/1/15.
 */
//@Mapper
public interface ITeamMapper extends Mapper<TeamVo> {

//    @Select("select * from t_t_team where id = #{id}")
    TeamVo selectTeamByTeamHeaderId(String id);

}
