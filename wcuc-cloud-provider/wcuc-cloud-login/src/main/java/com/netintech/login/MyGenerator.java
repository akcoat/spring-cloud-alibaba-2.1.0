package com.netintech.login;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyGenerator {

    private static String packageName="com.netintech.login";  //模块名称
    private static String modelName="test";    //模块名称
    private static String authorName="zjw";     //作者
    private static String[] includeTableNames=new String[] { "users" }; //需要生成的表名字
    //    private static String[] excludeTableNames=new String[] { "test_users" }; //需要排除生成的表名字
    //private static String[] prefixs=new String[] { "t_"}; //table前缀
   private static String path = MyGenerator.class.getResource("/").getPath().replace("/target/classes/","");




    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path+"/src/main/java");//输出目录
        gc.setFileOverride(true);// 是否覆盖文件
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(false);//生成后打开文件夹
        gc.setSwagger2(true);//开启 swagger2 模式 默认值：false
        // .setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor(authorName);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        //#主键类型  AUTO:"数据库ID自增", INPUT:"用户输入ID", ID_WORKER:"全局唯一ID，内容为空自动填充（默认配置）", UUID:"全局唯一ID，内容为空自动填充";
        gc.setIdType(IdType.UUID);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

//        //mysql================
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            public IColumnType processTypeConvert(GlobalConfig globalConfig,String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                if ( fieldType.toLowerCase().contains( "date" ) ) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig,fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://192.168.56.5:3307/zhu?characterEncoding=utf8&useSSL=false");

//        oracle=======
//        dsc.setDbType(DbType.ORACLE);
//        dsc.setTypeConvert(new OracleTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public IColumnType processTypeConvert(GlobalConfig globalConfig,String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                if ( fieldType.toLowerCase().contains( "number" ) ) {
//                    return DbColumnType.INTEGER;
//                }
//                if ( fieldType.toLowerCase().contains( "date" ) ) {
//                    return DbColumnType.DATE;
//                }
//                return super.processTypeConvert(globalConfig,fieldType);
//            }
//        });
//        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        dsc.setUrl("jdbc:oracle:thin:@192.168.168.115:1521:orcl");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
       // strategy.setTablePrefix(prefixs);// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(includeTableNames); // 需要生成的表
        strategy.setRestControllerStyle(true);//restController 风格
//         strategy.setExclude(excludeTableNames); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类.
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        // Boolean类型字段是否移除is前缀处理
        // .setEntityBooleanColumnRemoveIsPrefix(true)
        // .setControllerMappingHyphenStyle(true)
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);// 自定义包路径
        pc.setModuleName(modelName);//模块名称
        pc.setController("controller");// 这里是控制器包名，默认 web
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义 xxList.jsp 生成
//        focList.add(new FileOutConfig("/template/list.jsp.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return "D://my_" + tableInfo.getEntityName() + ".jsp";
//            }
//        });
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path+"/src/main/resources/mapper/"+ modelName+"/"+ tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 调整生成目录
//        focList.add(new FileOutConfig("/templates/entity.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return "src/main/java/com/netintech/demo/"+ modelName+"/vo/"+ tableInfo.getEntityName()+".java";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setEntity("templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
//      tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}
