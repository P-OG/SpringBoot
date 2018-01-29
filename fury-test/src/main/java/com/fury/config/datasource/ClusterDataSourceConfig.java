package com.fury.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: pxp
 * @Description: 数据源配置类
 * @CreateDate: 2018/1/16 22:54
 */
// 注册到springboot容器中
@Configuration
// 扫描Mapper接口并容器管理
@MapperScan(basePackages = {"com.fury.dao.cluster"}, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    @Value("${spring.datasource.cluster.clusterMapperLocations}")
    private String clusterMapperLocations;
    @Value("${spring.datasource.cluster.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DruidDataSource masterDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql", "true");
        properties.setProperty("druid.stat.slowSqlMillis", "10000");
        druidDataSource.setConnectProperties(properties);
        // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        try {
            druidDataSource.setFilters("stat,wall,log4j2");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return druidDataSource;
    }

    /**
     * 配置会话工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(
            @Qualifier("clusterDataSource") DataSource dataSource
    ) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(clusterMapperLocations));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        //配置分页插件
        PageHelper pageHelper = new PageHelper();
//        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        //reasonable如果设为true，则pageNum<=0时查询第一页,pageNum>总页数，查询最后一页数据； 设为false严格按照参数查询
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
//        pageInterceptor.setProperties(properties);
        pageHelper.setProperties(properties);
        //设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @param clusterDataSource
     * @return
     */
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(
            @Qualifier("clusterDataSource") DruidDataSource clusterDataSource
    ) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(clusterDataSource);
        return dataSourceTransactionManager;
    }

}