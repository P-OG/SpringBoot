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
 * 数据源配置类
 *
 * @author pxp
 * @date 2018/1/16 22:54
 */
// 注册到springboot容器中
@Configuration
// 扫描Mapper接口并容器管理
@MapperScan(basePackages = {"com.fury.dao.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Value("${datasource.master.masterMapperLocations}")
    private String masterMapperLocations;
    @Value("${datasource.master.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * 配置数据源
     *
     * @return DruidDataSource
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.master")
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
     * @param dataSource 数据源
     * @return SqlSessionFactory
     * @throws Exception 异常处理
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        // 配置数据源
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 添加XML目录
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(masterMapperLocations));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

//        // 添加XML目录
//        ResourcePatternResolver resolver2 = new PathMatchingResourcePatternResolver();
//        try {
//            //读取多个文件下的xml文件
//            List<Resource> resources = new ArrayList<Resource>(8);
//            Resource[] resources1 = resolver2.getResources("classpath*:mapper/*.xml");
//            Resource[] resources2 = resolver2.getResources("classpath*:sqlmapperext/*.xml");
//            resources.addAll(Arrays.asList(resources1));
//            resources.addAll(Arrays.asList(resources2));
//            sqlSessionFactoryBean.setMapperLocations(resources.toArray(new Resource[]{}));
//            return sqlSessionFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

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
        // 设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     *
     * @param masterDataSource 数据源
     * @return DataSourceTransactionManager
     */
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DruidDataSource masterDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(masterDataSource);
        return dataSourceTransactionManager;
    }

}