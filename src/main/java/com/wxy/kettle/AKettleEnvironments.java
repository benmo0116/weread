package com.wxy.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;

/**
 * @author wxy
 * @create 2018-03-15 17:25
 * @desc A—Java中创建transformation/job
 **/
public class AKettleEnvironments {
    public static KettleDatabaseRepository repository;
    public static DatabaseMeta databaseMeta;
    public static KettleDatabaseRepositoryMeta kettleDatabaseMeta;
    public static RepositoryDirectoryInterface directory;

    /*
    * KETTLE初始化*/
    public static String KettleEnvironments() {
        try {
            KettleEnvironment.init();
            repository = new KettleDatabaseRepository();
            databaseMeta = new DatabaseMeta("ETL", "MYSQL", "Native",
                    "127.0.0.1", "weread", "1521",
                    "ETL", "xin");//资源库数据库地址，我这里采用oracle数据库
            kettleDatabaseMeta = new KettleDatabaseRepositoryMeta("ETL", "ERP",
                    "Transformation description", databaseMeta);
            repository.init(kettleDatabaseMeta);
            repository.connect("adm", "adm");//资源库用户名和密码
            directory = repository.loadRepositoryDirectoryTree();
        } catch (KettleException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }
}
