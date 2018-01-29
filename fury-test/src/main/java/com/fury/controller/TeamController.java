package com.fury.controller;

import com.fury.domain.TeamVo;
import com.fury.service.ITeamService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AB058114 on 2018/1/15.
 */
//@Api(tags = "团队操作类")
@RequestMapping("/team")
@RestController
public class TeamController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ITeamService teamService;

    //    @ApiOperation("按照团队长id，查询团队信息")
    @RequestMapping(value = "/selectTeam", method = {RequestMethod.GET, RequestMethod.POST})
//    public List<TeamVo> selectTeam(@ApiParam("token") @RequestHeader("token") String token, @ApiParam("id") @RequestHeader("id") String id) {
    public List<TeamVo> selectTeam(String id) {
        List<TeamVo> resultList = new ArrayList<TeamVo>();
//        jdbcTemplate.query();
        resultList.add(teamService.selectTeamByTeamHeaderId(id));
        return resultList;
    }

    @RequestMapping("/selectTeam2")
    public List<TeamVo> selectTeam2(HttpServletRequest request) {
        List<TeamVo> resultList = new ArrayList<TeamVo>();
        String id = request.getParameter("id");
        resultList.add(teamService.selectTeamByTeamHeaderId(id));
        return resultList;
    }
}
