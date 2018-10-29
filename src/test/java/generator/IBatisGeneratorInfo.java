package generator;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月17日 15:28
 */
public class IBatisGeneratorInfo {

    /**
     * 包名，从com具体到模块名称之前，相对路径，会针对到
     */
    private String packageName = "com.uc.training";
    /**
     * 按不同系统的模块名称
     */
    private String sysModuleName = "smadmin";
    /**
     * 按系统中的不同子模块名称
     */
    private String moduleName = ".sys";
    /**
     * 表名称的前缀，用于生成实体类名称去掉前缀名称让你更
     */
    private String tablePre="t_sys_";
    /**
     * 表名称
     */
    private String tableName;


    public IBatisGeneratorInfo(){
    }

    public static void main(String[] args) {
        try {
            //String[] tables = new String[]{"t_mt_order","t_mt_order_extend","t_mt_order_bill","t_mt_order_balance","t_mt_order_refund"};
            String[] tables = new String[]{"t_sys_menu"};

            IBatisGeneratorInfo info=new IBatisGeneratorInfo();
            IBatisGenerator generator;
            for (String table:tables) {
                info.setTableName(table);
                generator = new IBatisGenerator(info);
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

    /*public IBatisGeneratorInfo(String sysModuleName, String packageName, String moduleName,String tableName) {
        this.sysModuleName = sysModuleName;
        this.packageName = packageName;
        this.moduleName = moduleName;
    }*/
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTablePre() {
        return tablePre;
    }

    public void setTablePre(String tablePre) {
        this.tablePre = tablePre;
    }

    public String getSysModuleName() {
        return sysModuleName;
    }

    public void setSysModuleName(String sysModuleName) {
        this.sysModuleName = sysModuleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
