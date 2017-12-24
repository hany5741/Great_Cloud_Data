package com.access.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 继承公共处理类接口 实现方法
 * Created by 11195 on 2017/12/24.
 */
public class DbHandle implements  BaseDbHandle{

    public Connection conn;
    public BaseDb baseDb;

    public void connect(BaseDb baseDb){
        try {
            conn = DbPools.getConnection(baseDb);
        } catch (Exception e) {
            //TODO 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的列名
     *
     */
    public void getAllColummnNames() {
      System.out.println(conn);
    }

    public BaseDb getBaseDb() {
        return baseDb;
    }

    public void setBaseDb(BaseDb baseDb) {
        this.baseDb = baseDb;
    }

    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
