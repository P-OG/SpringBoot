package com.fury.service;

import com.fury.domain.TeamVo;

import java.util.List;

/**
 * @Author: pxp
 * @Description: 团队服务接口类
 * @CreateDate: 2018/1/19 9:40
 */
public interface ITeamService {

    TeamVo selectTeamByTeamHeaderId(Integer id);

    List<TeamVo> selectAllTeam();
}
