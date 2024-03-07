package com.xz.library.admin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * mybatis-plus配置文件
 *
 * @author xz
 */
@Configuration
@EnableTransactionManagement
@MapperScans(
        @MapperScan({"com.xz.library.admin.mapper"})
)
@Slf4j
public class MybatisPlusConfig {

    /**
     * 乐观锁插件
     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        log.debug("mybatis-plus 注入 乐观锁插件[OptimisticLockerInterceptor]");
//        return new OptimisticLockerInterceptor();
//    }

    /**
     * 分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        log.debug("mybatis-plus 注入 分页插件[PaginationInterceptor]");
////        paginationInterceptor.setOverflow(false);
//        return paginationInterceptor;
//    }

//    @Bean
//    public SqlSessionFactory primarySqlSessionFactory() {
//        SqlSessionFactory factory = null;
//        try {
//            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            sqlSessionFactoryBean.setDataSource(new DruidDataSource());
//            sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatisConfig.xml.xml"));
//            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/*.xml"));
//            sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PageInterceptor()});
//            factory = sqlSessionFactoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return factory;
//    }

    /*解决失效冲突*/
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "true");
//        p.setProperty("rowBoundsWithCount", "true");
//        p.setProperty("reasonable", "true");
//        pageHelper.setProperties(p);
//        return pageHelper;
//    }
//
//
//    @Bean
//
//
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }

}
