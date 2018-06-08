package com.fury.dao.master;

import com.fury.domain.TeamVo;
//import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by AB058114 on 2018/1/15.
 */
//@Mapper
public interface ITeamMapper extends Mapper<TeamVo> {

    //    @Select("select * from t_t_team where id = #{id}")
    TeamVo selectTeamByTeamHeaderId(Integer id);

    /**
     * 查询全部
     *
     * @return List<TeamVo>
     */
    List<TeamVo> selectAllTeam();
}
