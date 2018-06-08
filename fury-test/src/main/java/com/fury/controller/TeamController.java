package com.fury.controller;

import com.fury.domain.TeamVo;
import com.fury.service.ITeamService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AB058114 on 2018/1/15.
 */
//@Api(tags = "团队操作类")
@RequestMapping("/team")
@RestController
public class TeamController {

    private static Logger logger = LogManager.getLogger(TeamController.class);

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ITeamService teamService;

    //    @ApiOperation("按照团队长id，查询团队信息")
    @RequestMapping(value = "/selectTeam", method = {RequestMethod.GET, RequestMethod.POST})
//    public List<TeamVo> selectTeam(@ApiParam("token") @RequestHeader("token") String token, @ApiParam("id") @RequestHeader("id") String id) {
    public List<TeamVo> selectTeam(int id) {
        List<TeamVo> resultList = new ArrayList<TeamVo>();
//        jdbcTemplate.query();
        resultList.add(teamService.selectTeamByTeamHeaderId(id));
        return resultList;
    }

    @RequestMapping("/selectTeam2")
    public List<TeamVo> selectTeam2(HttpServletRequest request) {
        logger.info("------->查询团队信息：id={}", new Object[]{request.getParameter("id")});
        List<TeamVo> resultList = new ArrayList<TeamVo>();
        int id = Integer.parseInt(request.getParameter("id"));
        resultList.add(teamService.selectTeamByTeamHeaderId(id));
        return resultList;
    }

    @RequestMapping("/selectTeamVo")
    public TeamVo selectTeamVo(@Valid TeamVo teamVo) {
        logger.info("------->查询团队信息：id={}", new Object[]{teamVo.toString()});
        logger.info("JSR303数据校验");
        return teamVo;
    }

    @RequestMapping("/selectAll")
    public List<TeamVo> selectAll(HttpServletRequest request) {
        logger.info("------->查询全部团队入参：pageNum={}，pageSize={}", new Object[]{request.getParameter("pageNum"), request.getParameter("pageSize")});
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<TeamVo> resultList = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            resultList = teamService.selectAllTeam();
            logger.info("=======>查询全部团队结果：resultList={}", new Object[]{resultList});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
