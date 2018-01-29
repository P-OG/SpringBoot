package com.fury.service;

import com.fury.domain.TeamVo;

/**
 * @Author: pxp
 * @Description: 团队服务接口类
 * @CreateDate: 2018/1/19 9:40
 */
public interface ITeamService {

    public TeamVo selectTeamByTeamHeaderId(String id);
}
