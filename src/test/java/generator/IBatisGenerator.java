package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Description IBatis代码自动生成
 * Created by changpan@zuche.com on 2017/5/23.
 */
public class IBatisGenerator {

    public IBatisGenerator(IBatisGeneratorInfo info) {
        this.sysModuleName = info.getSysModuleName();
        this.packageName = info.getPackageName();
        this.moduleName = info.getModuleName();
        this.tableName = info.getTableName();
        this.tablePre=
        basePath = sysModuleName+"/src/main/java/";
        xmlBasePath = sysModuleName+"/src/main/resources/";
        bean_package = packageName+"/" + moduleName + ".model";
        mapper_package = packageName + moduleName + ".dao";
        xml_path = xmlBasePath + packageName+moduleName+"/sql/";
        bean_path = basePath + (bean_package.replace(".", "/"));
        mapper_path = basePath + (mapper_package.replace(".", "/"));

    }

    /**
     * 按不同系统的模块名称
     */
    private  String sysModuleName;
    /**
     * 使用相对路径，针对项目名称的根目录下面开始
     */
    private String basePath;

    /**
     * resources 的物理地址
     */
    private String xmlBasePath ;

    /**
     * 包名，从com具体到模块名称之前
     */
    private  String packageName;
    /**
     * 按系统中的不同子模块名称
     */
    private  String moduleName;

    /**
     * model 类所在的包
     */
    private String bean_package;

    /**
     * dao 类所在的包
     */
    private  String mapper_package;

    /**
     * xml 在 resources 下的包名
     */
    private  String xml_path;

    private  String bean_path;
    private  String mapper_path;


    private final String driverName = "com.mysql.jdbc.Driver";
    private final String user = "jeese";
    private final String password = "!Jeese0715";
    private final String url = "jdbc:mysql://180.76.106.207:3306/UCAR-SIX?characterEncoding=utf8";

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表明前缀
     */
    private String tablePre;

    private String beanName = null;

    private String mapperName = null;

    private Connection conn = null;

    private final static Map<String, String> dbTypes = new HashMap<String, String>();

    static {
        dbTypes.put("char", "String");
        dbTypes.put("varchar", "String");
        dbTypes.put("text", "String");
        dbTypes.put("bigint", "Long");
        dbTypes.put("double", "Double");
        dbTypes.put("int", "Integer");
        dbTypes.put("tinyint", "Integer");
        dbTypes.put("date", "java.util.Date");
        dbTypes.put("timestamp", "java.util.Date");
        dbTypes.put("datetime", "java.util.Date");
        dbTypes.put("bit", "Boolean");
        dbTypes.put("decimal", "java.math.BigDecimal");
        dbTypes.put("blob", "byte[]");
        dbTypes.put("float", "Float");
    }


    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
    }


    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        if (tableName != null) {
            tables.add(tableName);
            return tables;
        }
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            tables.add(tableName);
        }
        return tables;
    }

    /**
     * @Description 处理获取到的这张表名称，转为为驼峰命名
     */
    private void processTableName(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();//转换为小写
        if (tablePre != null && tablePre.length() > 0) {
            if (tableNew.startsWith(tablePre)) {
                tableNew = tableNew.substring(tablePre.length());
            }
        }

        String[] tables = tableNew.split("_");
        String temp = null;
        for (int i = 0; i < tables.length; i++) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        beanName = sb.toString();
        mapperName = beanName + "Dao";
    }

    /**
     * @Description 处理数据库字段信息
     */
    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb.toString();
    }

    /**
     * @Description 将实体类名首字母改为小写
     */
    private String processResultMapId(String beanName) {
        beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        return beanName;
    }


    /**
     * 构建类上面的注释
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" */");
        return bw;
    }


    /**
     * 构建方法上面的注释
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t */");
        return bw;
    }


    /**
     * @Description 根据表的字段名称、字段类型、字段注释、表注释生成实体类
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {

        File folder = new File(bean_path);
        //文件夹不存在则创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File beanFile = new File(bean_path, beanName + ".java");
        if(beanFile.exists()){
            System.out.println("实体类 "+beanName+" 已存在");
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        bw.write("\tprivate static final long serialVersionUID =  "+Math.abs(new Random().nextLong())+"L;");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            //处理数据类型							处理字段信息
            bw.write("\tprivate " + dbTypes.get(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = dbTypes.get(types.get(i));
            _tempField = processField(columns.get(i));
            //首字母转换为大写
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        System.out.println("实体类 "+beanName+" 生成的路径为："+beanFile.getPath());
        bw.close();
    }


    /**
     * @Description 构建Dao文件
     */
    private void buildMapper() throws IOException {
        File folder = new File(mapper_path);
        //文件夹不存在则创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(mapper_path, mapperName + ".java");
        if(mapperFile.exists()){
            System.out.println("dao接口 "+mapperName+" 已存在");
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + ";");
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw.write("import java.util.List;");
        bw = buildClassComment(bw, mapperName + "数据库操作接口类");
        bw.newLine();
        //      bw.write("public interface " + mapperName + " extends " + mapper_extends + "<" + beanName + "> {");
        bw.write("public interface " + mapperName + "{");
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------

        bw = buildMethodComment(bw, "通过主键来查找查找");
        bw.newLine();
        bw.write("\t public List<" + beanName + ">  get" + beanName + "ById(Long id);");
        bw.newLine();
        bw = buildMethodComment(bw, "查询列表");
        bw.newLine();
        bw.write("\t public List<" + beanName + ">  query" + beanName + "List(" + beanName + " queryModel);");
        bw.newLine();
        bw = buildMethodComment(bw, "查找数据总记录数");
        bw.newLine();
        bw.write("\t" + "public Integer query" + beanName + "Count(" + beanName + " queryModel);");
        bw.newLine();
        bw = buildMethodComment(bw, "保存");
        bw.newLine();
        bw.write("\t" + "public Long insert" + beanName + "( " + beanName + " record );");
        bw.newLine();
        bw = buildMethodComment(bw, "更新");
        bw.newLine();
        bw.write("\t" + "public int update" + beanName + "ById( " + beanName + " record );");
        bw.newLine();

        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
        System.out.println("dao接口 "+mapperName+" 生成的路径为："+mapperFile.getPath());
        buildMapperImpl();
    }

    /**
     * @Description 构建Dao的实现类impl文件
     */
    private void buildMapperImpl() throws IOException {
        File folder = new File(mapper_path + "/impl");
        //文件夹不存在则创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(mapper_path + "/impl", mapperName + "Impl.java");
        if(mapperFile.exists()){
            System.out.println("实体类 "+mapperName+" 已存在");
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + ".impl;");
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw.newLine();
        bw.write("import " + mapper_package + "." + beanName + "Dao;");
        bw.newLine();
        bw.write("import com.zuche.framework.dao.CarIsIbatisDaoImpl;");
        bw.newLine();
        bw.write("import org.springframework.stereotype.Repository;");
        bw.newLine();
        bw.write("import java.util.List;");
        bw = buildClassComment(bw, mapperName + "数据库操作接口类");
        bw.newLine();
        bw.write("@Repository");
        bw.newLine();
        bw.write("public class " + mapperName + "Impl extends CarIsIbatisDaoImpl implements " + mapperName + " {");
        bw.newLine();
        // ----------定义Mapper中的方法Begin----------

        bw = buildMethodComment(bw, "通过主键来查找查找");
        bw.newLine();
        bw.write("\t @Override");
        bw.newLine();
        bw.write("\t public List<" + beanName + "> get" + beanName + "ById(Long id){");
        bw.newLine();
        bw.write("\t\t  return (List<" + beanName + ">) this.queryForObject(\"" + mapper_package + "." + mapperName + ".get" + beanName + "ById\", id);");
        bw.newLine();
        bw.write("\t }");
        bw.newLine();
        bw = buildMethodComment(bw, "查询列表");
        bw.newLine();
        bw.write("\t @Override");
        bw.newLine();
        bw.write("\t public List<" + beanName + ">  query" + beanName + "List(" + beanName + " queryModel){");
        bw.newLine();
        bw.write("\t\t  return this.queryForList(\"" + mapper_package + "." + mapperName + ".query" + beanName + "List\", queryModel);");
        bw.newLine();
        bw.write("\t }");
        bw.newLine();
        bw = buildMethodComment(bw, "查找数据总记录数");
        bw.newLine();
        bw.write("\t @Override");
        bw.newLine();
        bw.write("\t" + "public Integer query" + beanName + "Count(" + beanName + " queryModel){");
        bw.newLine();
        bw.write("\t\t  return (Integer) this.queryForObject(\"" + mapper_package + "." + mapperName + ".query" + beanName + "Count\", queryModel);");
        bw.newLine();
        bw.write("\t }");
        bw.newLine();
        bw = buildMethodComment(bw, "保存");
        bw.newLine();
        bw.write("\t @Override");
        bw.newLine();
        bw.write("\t" + "public Long insert" + beanName + "( " + beanName + " record ){");

        bw.newLine();
        bw.write("\t\t  return (Long) this.insert(\"" + mapper_package + "." + mapperName + ".insert" + beanName + "\", record);");
        bw.newLine();
        bw.write("\t }");
        bw.newLine();
        bw = buildMethodComment(bw, "更新");
        bw.newLine();
        bw.write("\t @Override");
        bw.newLine();
        bw.write("\t" + "public int update" + beanName + "ById( " + beanName + " record ){");
        bw.newLine();
        bw.write("\t\t  return this.update(\"" + mapper_package + "." + mapperName + ".update" + beanName + "ById\", record);");
        bw.newLine();
        bw.write("\t }");
        bw.newLine();

        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        System.out.println("dao实现类 "+mapperName+" 生成的路径为："+mapperFile.getPath());
        bw.close();
    }


    /**
     * @Description 构建对应的Mybatis Xml 映射文件
     */
    private void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {
        File folder = new File(xml_path);
        //文件夹不存在则创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(xml_path, processField(tableName.replace(tablePre, "")) + "_sql.xml");
        if(mapperXmlFile.exists()){
            System.out.println("实体类 "+processField(tableName.replace(tablePre, "")) + "_sql.xml"+" 已存在");
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        //基础信息
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        bw.newLine();
        bw.write("<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\" >");
        bw.newLine();
        bw.write("<sqlMap namespace=\"" + mapper_package + "." + mapperName + "\">");
        bw.newLine();
        buildSQL(bw, columns, types);
        bw.newLine();
        bw.write("</sqlMap>");
        bw.flush();
        bw.close();
    }

    /**
     * @Description 对应的方法SQL编写
     */
    private void buildSQL(BufferedWriter bw, List<String> columns, List<String> types) throws IOException {
        int size = columns.size();
        bw.newLine();
        String resultClass = bean_package + "." + beanName;
        // 查询 根据对象的值不为null的进行查找
        bw.write("\t<!-- 根据对象的不为null的值作为条件进行查找 -->");
        bw.newLine();
        bw.write("\t<select id=\"get" + beanName + "ById\" resultClass=\"" + resultClass + "\" parameterClass=\"long\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        for (int i = 0; i < size; i++) {
            bw.newLine();
            bw.write("\t\t s." + columns.get(i) + " AS " + this.processField(columns.get(i)));
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.newLine();
        bw.write("\t\t FROM " + tableName + " s");
        bw.write("\t\t WHERE s." + columns.get(0) + "=#" + this.processField(columns.get(0)) + "#");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

        //where条件
        bw.newLine();
        bw.write("\t<sql id=\"where_query\">");
        bw.newLine();
        for (int i = 0; i < size; i++) {
            bw.newLine();
            bw.write("\t\t <isNotEmpty property=\"" + this.processField(columns.get(i)) + "\" prepend=\"and\">");
            bw.newLine();
            bw.write("\t\t\t s." + columns.get(i) + " = #" + this.processField(columns.get(i)) + "#");
            bw.newLine();
            bw.write("\t\t </isNotEmpty>");
        }
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        // 列表查询
        bw.write("\t<!-- 列表查询 -->");
        bw.newLine();
        bw.write("\t<select id=\"query" + beanName + "List\" resultClass=\"" + resultClass + "\" parameterClass=\"" + resultClass + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        for (int i = 1; i < size; i++) {
            bw.newLine();
            bw.write("\t\t s." + columns.get(i) + " AS " + this.processField(columns.get(i)));
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.newLine();
        bw.write("\t\t FROM " + tableName + " s");
        bw.newLine();
        bw.write("\t\t WHERE 1=1");
        bw.newLine();
        bw.write("\t\t <include refid=\"where_query\"/>");
        bw.newLine();
        bw.write("\t\t <!--  <isEmpty property=\"queryType\"> order by s.id DESC </isEmpty>  -->");
        bw.newLine();
        bw.write("\t\t <!--  <isNotEmpty property=\"rows\" prepend=\" \"> limit #start#,#rows#</isNotEmpty>/>  -->");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

        // 条数查询
        bw.write("\t<!-- 查找数据总记录数 -->");
        bw.newLine();
        bw.write("\t<select id=\"query" + beanName + "Count\" resultClass=\"java.lang.Integer\" parameterClass=\"" + resultClass + "\">");
        bw.newLine();
        bw.write("\t\t SELECT count(1)");
        bw.newLine();
        bw.write("\t\t FROM " + tableName + " s");
        bw.newLine();
        bw.write("\t\t WHERE 1=1");
        bw.newLine();
        bw.write("\t\t <include refid=\"where_query\"/>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

        bw.write("\t<!-- 插入 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert" + beanName + "\" parameterClass=\"" + resultClass + "\">");
        bw.newLine();
        bw.write("\t\t insert into " + tableName);
        bw.newLine();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                bw.write("\t\t\t (");
            }
            bw.write(columns.get(i));
            if (i != size - 1) {
                bw.write(", ");
            } else {
                bw.write(")");
            }
        }
        bw.write("\t\t values (");
        for (int i = 0; i < size; i++) {
            bw.write("#" + this.processField(columns.get(i)) + "#");
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(")");
        bw.newLine();
        bw.write("\t<selectKey keyProperty=\"" + this.processField(columns.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\tselect LAST_INSERT_ID() AS " + this.processField(columns.get(0)));
        bw.newLine();
        bw.write("\t</selectKey>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();


        // 通过主键更新
        bw.write("\t<!-- 通过主键更新 -->");
        bw.newLine();
        bw.write("\t<update id=\"update" + beanName + "ById\" parameterClass=\"" + resultClass + "\">");
        bw.newLine();
        bw.write("\t\t update " + tableName + " set");
        bw.newLine();
        bw.write("\t\t");
        for (int i = 0; i < size; i++) {
            bw.write(columns.get(i) + " = #" + this.processField(columns.get(i)) + "#");
            if (i != size - 1) {
                bw.write(", ");
            }
        }
        bw.newLine();
        bw.write("\t\twhere " + columns.get(0) + "=#" + this.processField(columns.get(0)) + "#");
        bw.newLine();
        bw.write("\t\t</update>");
        bw.newLine();


    }

    /**
     * @Description 处理数据类型，值留下类型   varchar(20) = varchar
     */
    private String processTypeNum(String type) {
        type = type.replaceAll("([0-9])", "").replace("(", "").replace(")", "").trim().toUpperCase();
        if (type.equalsIgnoreCase("int")) {
            type = "INTEGER";
        }
        if (type.equalsIgnoreCase("text")) {
            type = "VARCHAR";
        }
        return type;
    }

    /**
     * @Description 获取所有的数据库表注释
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }

    /**
     * @Description 生成，Bean文件、Dao文件、Xml文件
     */
    public void generateForDatabase() throws ClassNotFoundException, SQLException, IOException {
        init(); //初始化数据库连接
        String prefix = "show full fields from "; //查找某张表的所有字段信息，字段名称、字段类型、字段备注
        List<String> columns = null;//字段名称
        List<String> types = null;//字段类型
        List<String> comments = null;//字段备注
        PreparedStatement pstate = null;
        List<String> tables = getTables(); //获取到数据库的所有表
        Map<String, String> tableComments = getTableComment();//获取到所有表的注释
        for (String table : tables) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            //查找某张表的所有字段信息，字段名称、字段类型、字段备注
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE").replaceAll("\\(.*\\)", "").replace("unsigned", "").trim());
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTableName(table);//处理获取到的这张表名称，转为为驼峰命名
            //this.outputBaseBean();
            String tableComment = tableComments.get(tableName);
            //创建对应的EntityBean
            buildEntityBean(columns, types, comments, tableComment);
            buildMapper();//构建Mapper文件
            //构建对应的Mybatis Xml 映射文件
            buildMapperXml(columns, types, comments);
        }
        conn.close();
    }

    /**
     * @Description 只生成部分表的，Bean文件、Dao文件、Xml文件
     */
    public void generateForTable(List<String> tableNames) throws ClassNotFoundException, SQLException, IOException {
        init(); //初始化数据库连接
        String prefix = "show full fields from "; //查找某张表的所有字段信息，字段名称、字段类型、字段备注
        List<String> columns = null;//字段名称
        List<String> types = null;//字段类型
        List<String> comments = null;//字段备注
        PreparedStatement pstate = null;
        Map<String, String> tableComments = getTableComment();//获取到所有表的注释
        for (String table : tableNames) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            //查找某张表的所有字段信息，字段名称、字段类型、字段备注
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTableName(table);//处理获取到的这张表名称，转为为驼峰命名
            //this.outputBaseBean();
            String tableComment = tableComments.get(tableName);
            //创建对应的EntityBean
            buildEntityBean(columns, types, comments, tableComment);
            buildMapper();//构建Mapper文件
            //构建对应的Mybatis Xml 映射文件
            buildMapperXml(columns, types, comments);
        }
        conn.close();
    }

    public static void main(String[] args) {
        try {
            //String[] tables = new String[]{"t_mt_order","t_mt_order_extend","t_mt_order_bill","t_mt_order_balance","t_mt_order_refund"};
            String[] tables = new String[]{"t_bd_member_grade"};

            IBatisGenerator generator = new IBatisGenerator(new IBatisGeneratorInfo());
            // 设置表前缀
            generator.tablePre = "t_bd_";
            for (String table:tables) {
                generator.tableName = table;
                generator.generateForDatabase();
            }
            // 自动打开生成文件的目录
            // Runtime.getRuntime().exec("cmd /c start explorer E:\\");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
