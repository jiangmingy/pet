package com.ttm.pet.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

public class MysqlGeneration {
    private static final String PACKAGE_NAME = "com.ttm.pet";
    private static final String OUT_PATH = "E:\\workspaceIdea\\pet";
    private static final String AUTHOR = "cx";

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pet?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
    private static final String USER_NAME = "root";
        private static final String PASSWORD = "cxcx";
    private static final String PREFIX="t_";                   //table前缀

    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig().setOutputDir(OUT_PATH+"/src/main/java")// 输出目录
                    .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setAuthor(AUTHOR)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setControllerName("%sController")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
        ).setDataSource(
            // 数据源配置
            new DataSourceConfig()
                .setDbType(DbType.MYSQL)// 数据库类型
                .setDriverName(DRIVER)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setUrl(URL)
        ).setStrategy(
            // 策略配置
            new StrategyConfig()
                .setDbColumnUnderline(true)// 全局下划线命名
                .setTablePrefix(PREFIX)// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setInclude(new String[] {"t_customer"}) // 需要生成的表
                .setTableFillList(tableFillList)
//                 .setSuperEntityClass("com.baomidou.demo.common.base.BsBaseEntity")
//                 .setSuperMapperClass("com.baomidou.demo.common.base.BsBaseMapper")
//                 .setSuperServiceClass("com.baomidou.demo.common.base.BsBaseService")
//                 .setSuperControllerClass("com.baomidou.demo.TestController")
//                 public static final String ID = "test_id";
        ).setPackageInfo(
            // 包配置
            new PackageConfig()
                .setParent(PACKAGE_NAME)
                .setController("controller.admin.v1")
                .setMapper("dao")
                .setEntity("model.dto")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper")
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(
                        Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                            // 自定义输出文件目录
                            @Override
                            public String outputFile(TableInfo tableInfo) {
                                return OUT_PATH+"/src/main/resources" + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                            }
                        })))
                .setTemplate(
                    // 关闭默认 xml 生成，调整生成 至 根目录
                    new TemplateConfig().setXml(null)
        );

        // 执行生成
        mpg.execute();
    }
}
