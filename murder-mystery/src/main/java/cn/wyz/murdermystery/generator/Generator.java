package cn.wyz.murdermystery.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://43.143.236.141:5432/ju", "postgres", "Xxzs990416")
                .globalConfig(builder -> {
                    builder.author("wyzZzz") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("C://ju--java/murder-mystery/src/main/java") // 指定输出目录
                            .commentDate("yyyy-MM-dd hh:mm:ss") //注释日期
                            .dateType(DateType.TIME_PACK); // 定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                })
                .packageConfig(builder -> {
                    builder.parent("cn.wyz") // 设置父包名
                            .moduleName("murdermystery") // 设置父包模块名
                            .entity("bean")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .mapper("mapper")
                            .xml("mapper")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C://ju--java/murder-mystery/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("blemish_detail", "ju_info", "user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            // Mapper 策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class) // 设置父类
                            .formatMapperFileName("%sMapper") //格式化mapper文件名称
                            .enableMapperAnnotation() // 开启 @Mapper 注解
                            .formatMapperFileName("%sMapper") // 格式化Xml文件名称

                            // service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%Service")
                            .formatServiceImplFileName("%ServiceImpl")

                            // 实体类策略配置
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            //.disableSerialVersionUID()  不实现 Serializable 接口，不生产SerivalVersionUID
                            //.logicDeleteColumnName("deleted") 逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略：下划线转驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略：下划线转驼峰命名
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE)
                            ) // 添加表字段填充，"create_time"字段填充为插入，"update_time"字段填充为插入and修改
                            .enableTableFieldAnnotation() // 开启生成实体时生成表字段注解

                            .controllerBuilder()
                            .formatFileName("%sController") // 格式化Controller类文件
                            .enableRestStyle(); // 开启生成@RestController 控制器

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
