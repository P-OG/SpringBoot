package com.fury.service.impl;

import com.fury.dao.master.ITeamMapper;
import com.fury.domain.TeamVo;
import com.fury.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: pxp
 * @Description: 团队服务实现类
 * @CreateDate: 2018/1/19 9:40
 */
@Service //声明成一个spring bean
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private ITeamMapper teamMapper;

    @Override
    @Transactional
    public TeamVo selectTeamByTeamHeaderId(String id) {
        return teamMapper.selectTeamByTeamHeaderId(id);
    }

}
