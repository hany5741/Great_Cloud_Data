package com.access.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库的基类 抽象类
 * Created by 11195 on 2017/12/24.
 */
public abstract class BaseDb {

    protected String hostName;//主机名或者IP
    protected String dbName;//数据库名
    protected String userName;//用户名
    protected String passWord;//密码
    protected String port;//端口号
    protected String dataBaseId;//数据库唯一ID


    public void parser(String connStr,String dbType){
        //数据库唯一标识
        setDataBaseId(connStr+dbType);
        List<Pattern> patterns = new ArrayList<Pattern>();
        patterns.add(Pattern.compile("(.*)/(.*)@(.*):([0-9]*)/(.*)"));
        patterns.add(Pattern.compile("(.*)/(.*)@(.*)/(.*)"));

        List<ConFormat> formats = new ArrayList<ConFormat>();
        formats.add(ConFormat.U_P_H_P_D);
        formats.add(ConFormat.U_P_H_D);
        ConFormat result = null;
        for (int i = 0;i < patterns.size();i++){
            Matcher matcher = patterns.get(i).matcher(connStr);

            if (matcher.find()){
                result = formats.get(i);
                switch (result){
                    case U_P_H_P_D:
                        setUserName(matcher.group(1));
                        setPassWord(matcher.group(2));
                        setHostName(matcher.group(3));
                        setPort(matcher.group(4));
                        setDbName(matcher.group(5));
                        break;
                    case U_P_H_D:
                        setUserName(matcher.group(1));
                        setPassWord(matcher.group(2));
                        setHostName(matcher.group(3));
                        setPort("");
                        setDbName(matcher.group(4));
                        break;
                }
            }
        }


    }


    /**
     * 抽象方法 每个种类的数据库都不一样 后续如果添加方法可在这里添加   在子类实现
     * @return
     */
    public abstract String getCurrentTime(Connection conn);
    public abstract String getJdbcUrl();
    public abstract String getJdbcDriver();
    public abstract String getTestSql();//获取TestSql 创建连接池时有用






    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(String dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    enum ConFormat{
        U_P_H_P_D, // user/pwd@hostname:port/databasename
        U_P_H_D // user/pwd@hostname/databasename
    }

}
