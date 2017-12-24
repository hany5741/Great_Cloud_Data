package com.access.db;

/**
 * Created by 11195 on 2017/12/24.
 */
public class DbFactory {

    public BaseDb baseDb;
    public String connStr;
    public String dbType;

    public DbFactory(String connStr,String dbType){
         this.connStr = connStr;
         this.dbType = dbType;
    }

    public BaseDb  getBaseDb(){
        if (DbType.ORACLE.getName().equalsIgnoreCase(dbType)){

        }else if (DbType.MYSQL.getName().equalsIgnoreCase(dbType)){
            baseDb = new MysqlDb();
        }else if (DbType.HIVE.getName().equalsIgnoreCase(dbType)){

        }else if (DbType.PG.getName().equalsIgnoreCase(dbType)){

        }else if (DbType.SQLSERVER.getName().equalsIgnoreCase(dbType)){

        }else {
            //TODO
        }

        return this.baseDb;
    }



    public enum DbType{

        ORACLE("oracle"),MYSQL("mysql"),HIVE("hive"),PG("postgresql"),SQLSERVER("sqlserver");

       private String name;

        private DbType(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
