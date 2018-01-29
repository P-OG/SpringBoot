package com.fury.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by AB058114 on 2018/1/15.
 */
@Table(name = "t_t_team")
public class TeamVo implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 团ID
     */
    @Column(name = "team_id")
    private String teamId;

    /**
     * 团长ID
     */
    @Column(name = "team_header_id")
    private String teamHeaderId;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 头像
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 是否可用：0不可用；1可用(默认)
     */
    @Column(name = "is_aval")
    private String isAval;
    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private String modifyTime;
    /**
     * 团长名字
     */
    @Column(name = "team_header_id")
    private String teamheaderName;


    public String getTeamheaderName() {
        return teamheaderName;
    }

    public void setTeamheaderName(String teamheaderName) {
        this.teamheaderName = teamheaderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamHeaderId() {
        return teamHeaderId;
    }

    public void setTeamHeaderId(String teamHeaderId) {
        this.teamHeaderId = teamHeaderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsAval() {
        return isAval;
    }

    public void setIsAval(String isAval) {
        this.isAval = isAval;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "TeamVo{" +
                "id=" + id +
                ", teamId='" + teamId + '\'' +
                ", teamHeaderId='" + teamHeaderId + '\'' +
                ", name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                ", createTime=" + createTime +
                ", isAval='" + isAval + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}
