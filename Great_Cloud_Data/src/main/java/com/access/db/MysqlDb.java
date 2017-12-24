package com.access.db;


import com.access.common.Constants;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;

/**
 * mysql handle类
 * Created by 11195 on 2017/12/24.
 */
public class MysqlDb extends BaseDb {


    @Override
    public String getCurrentTime(Connection conn) {
        return null;
    }

    public String getJdbcUrl() {
        if (StringUtils.isEmpty(getPort())){
            return "jdbc:mysql://"+getHostName()+"/"+getDbName();
        }else {
            return "jdbc:mysql://"+getHostName()+":"+getPort()+"/"+getDbName();
        }
    }

    public String getJdbcDriver() {
        return Constants.MYSQL_DRIVER;
    }

    public String getTestSql() {
        return "select 1";
    }

    //获取当前时间 每种数据的获取方法不一样
    public String getCurrentTime() {
        return null;
    }


    public static void main(String[] args) {
        String connStr1 = "root/root@127.0.0.1:3306/test";
        String connStr2 = "root/root@127.0.0.1/test";
        String dbType = "mysql";
        BaseDb baseDb = new MysqlDb();
       // baseDb.parser(connStr1,dbType);
        baseDb.parser(connStr2,dbType);
        System.out.println(baseDb.getJdbcUrl());
    }

}
