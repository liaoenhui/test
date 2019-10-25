package com.zysj.dssync.service.metadata;

import com.zysj.dssync.entity.Datasource;
import com.zysj.dssync.entity.metadata.ColumnMeta;
import com.zysj.dssync.entity.metadata.TableMeta;
import org.apache.http.client.utils.DateUtils;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetadataRetriever {
    private static final String ALL_PATTERN = "%";
    public static List<TableMeta> getAllTables(DatabaseMetaData dbMetaData, Datasource ds){
        return getTables(dbMetaData,ds,ALL_PATTERN);
    }
    public static List<TableMeta> getTables(DatabaseMetaData dbMetaData, Datasource ds,String tableNamePattern ){
        List<TableMeta> tml = new ArrayList<TableMeta>();
        try {
            String[] types = {"TABLE"};
            ResultSet rs =  dbMetaData.getTables(null, ds.getDsEname(), tableNamePattern, types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME"); // 表名
                String tableType = rs.getString("TABLE_TYPE"); // 表类型
                String remarks = rs.getString("REMARKS"); // 表备注
//                System.out.println(tableName + "-" + tableType + "-" + remarks);
                TableMeta tm = new TableMeta();
                tm.setDS_ID(ds.getDsId());
                tm.setTABLE_CNAME(remarks);
                tm.setTABLE_ENAME(tableName);
                tm.setTABLE_UPDATETIME(DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
                tm.setTABLE_ID(ds.getDsId()+"."+ds.getDsEname()+"."+tableName);
                tml.add(tm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tml;
    }
    public static List<ColumnMeta> getAllColumns(DatabaseMetaData dbMetaData, String schemaName, String dsId){
        return getTableColumns(dbMetaData,schemaName,ALL_PATTERN,ALL_PATTERN,dsId);
    }
    /**
     * 获得表或视图中的所有列信息
     */
    public static List<ColumnMeta> getTableColumns(DatabaseMetaData dbMetaData, String schemaName, String tableNamePattern,String columnNamePattern, String dsid) {
        List<ColumnMeta> cml = new ArrayList<ColumnMeta>();
        try{
            ResultSet rs = dbMetaData.getColumns(null, schemaName, tableNamePattern, columnNamePattern);
            while (rs.next()){
                String tableCat = rs.getString("TABLE_CAT");//表目录（可能为空）
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表的架构（可能为空）
                String tableName_ = rs.getString("TABLE_NAME");//表名
                String columnName = rs.getString("COLUMN_NAME");//列名
                int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
                int columnSize = rs.getInt("COLUMN_SIZE");//列大小
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");//小数位数
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//基数（通常是10或2）
                int nullAble = rs.getInt("NULLABLE");//是否允许为null
                String remarks = rs.getString("REMARKS");//列描述
                String columnDef = rs.getString("COLUMN_DEF");//默认值
                int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql数据类型
                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL日期时间分?
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char类型的列中的最大字节数
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //表中列的索引（从1开始）

                /**
                 * ISO规则用来确定某一列的为空性。
                 * 是---如果该参数可以包括空值;
                 * 无---如果参数不能包含空值
                 * 空字符串---如果参数为空性是未知的
                 */
                String isNullAble = rs.getString("IS_NULLABLE");

                /**
                 * 指示此列是否是自动递增
                 * 是---如果该列是自动递增
                 * 无---如果不是自动递增列
                 * 空字串---如果不能确定它是否
                 * 列是自动递增的参数是未知
                 */
                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
                ColumnMeta cm = new ColumnMeta();
                cm.setCS_CNAME(remarks);
                cm.setCS_ENAME(columnName);
                cm.setCS_ID(dsid+"."+schemaName+"."+tableName_+"."+columnName);
                cm.setCS_ISNULL(nullAble+"");
//                cm.setCS_ISPRIMARY();
                cm.setCS_LENGTH(columnSize+"");
                cm.setCS_TYPE(dataTypeName);
                cm.setTABLE_ID(dsid+"."+schemaName+"."+tableName_);
                cm.setCS_TABLE_NAME(tableName_);
                cm.setCS_UPDATETIME(DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

                cml.add(cm);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cml;
    }
    /**
     * 获得一个表的主键信息
     */
    public static void getAllPrimaryKeys(DatabaseMetaData dbMetaData, String schemaName, String tableName) {
        try{
            ResultSet rs = dbMetaData.getPrimaryKeys(null, schemaName, tableName);
            while (rs.next()){
                String columnName = rs.getString("COLUMN_NAME");//列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                String pkName = rs.getString("PK_NAME"); //主键名称
//                System.out.println(columnName + "-" + keySeq + "-" + pkName);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
