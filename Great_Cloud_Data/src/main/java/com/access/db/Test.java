package com.access.db;

/**
 * Created by 11195 on 2017/12/24.
 */
public class Test {
    public static void main(String[] args) {
        String connStr = "root/root@127.0.0.1:3306/hive";
        String dbType = "mysql";
        //初始化工类 返回对应数据库的basedb
        DbFactory dbFactory = new DbFactory(connStr,dbType);
        BaseDb baseDb = dbFactory.getBaseDb();
        //解析连接串 获取username password
        baseDb.parser(connStr,dbType); //后期考虑做成返回值为boolean
        //初始化数据库处理类
        DbHandle dbHandle = new DbHandle();
        dbHandle.connect(baseDb);//后期考虑做成返回值为boolean

        dbHandle.getAllColummnNames();




    }
}
