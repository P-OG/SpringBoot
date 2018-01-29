package com.fury.dao.cluster;

import com.fury.domain.TeamVo;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by AB058114 on 2018/1/15.
 */
public interface ITeam2Mapper extends Mapper<TeamVo> {

    TeamVo selectTeamByTeamHeaderId(String id);

}
