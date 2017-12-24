package com.access.db;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11195 on 2017/12/24.
 */
public class DbPools {
    //<数据库唯一标识，连接池>
    public static Map<String,DruidDataSource> pools = new HashMap<String, DruidDataSource>();

    public static Connection getConnection(BaseDb baseDb) throws Exception {
        //第一次
        DruidDataSource pool1 = pools.get(baseDb.getDataBaseId());
        if(pool1 == null){
          createDataSource(baseDb);
        }else {
            return pool1.getConnection(60*1000);
        }

        DruidDataSource pool2 = pools.get(baseDb.getDataBaseId());
        if (pool2 == null){
            throw new Exception("get .... is failed");
        }else {
            return pool2.getConnection(60*1000);
        }
    }

    public static  void createDataSource(BaseDb baseDb){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(baseDb.getJdbcDriver());
        dataSource.setUsername(baseDb.getUserName());
        dataSource.setPassword(baseDb.getPassWord());
        dataSource.setUrl(baseDb.getJdbcUrl());
        dataSource.setMinIdle(1);//设置最小空闲连接数 后期做成可配置
        dataSource.setMaxActive(10);//设置最大连接数
        dataSource.setInitialSize(2);//设置最小连接数
        dataSource.setValidationQuery(baseDb.getTestSql());
        pools.put(baseDb.getDataBaseId(),dataSource);
    }
}
