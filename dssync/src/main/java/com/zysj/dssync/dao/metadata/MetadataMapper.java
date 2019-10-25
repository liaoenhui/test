package com.zysj.dssync.dao.metadata;

import com.zysj.dssync.entity.metadata.ColumnMeta;
import com.zysj.dssync.entity.metadata.TableMeta;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MetadataMapper {
//    private String TABLE_ID;
//    private String TABLE_ENAME;
//    private String TABLE_CNAME;
//    private String TABLE_CHARSET;
//    private String DS_ID;
//    private String TABLE_UPDATETIME;
    @Update("<script>" +
            " INSERT INTO tb_datasource_table_incr(TABLE_ID,TABLE_ENAME,TABLE_CNAME,TABLE_CHARSET,DS_ID,TABLE_UPDATETIME)" +
            "      VALUES" +
            "      <foreach collection=\"tml\" item=\"tm\" separator=\",\">\n" +
            "      (#{tm.TABLE_ID},#{tm.TABLE_ENAME},#{tm.TABLE_CNAME},#{tm.TABLE_CHARSET},#{tm.DS_ID},#{tm.TABLE_UPDATETIME})\n" +
            "      </foreach>"+
            "</script>")
    void insertBatchTableMeta(@Param("tml") List<TableMeta> tml);

    @Update("<script>" +
            " INSERT INTO tb_datasource_table_column_incr(CS_ID,CS_ENAME,CS_CNAME,CS_TYPE,CS_LENGTH,CS_ISNULL,CS_ISPRIMARY,CS_CHARSET,TABLE_ID,CS_UPDATETIME,CS_TABLE_NAME)" +
            "      VALUES" +
            "      <foreach collection=\"cml\" item=\"cm\" separator=\",\">\n" +
            "      (#{cm.CS_ID},#{cm.CS_ENAME},#{cm.CS_CNAME},#{cm.CS_TYPE},#{cm.CS_LENGTH},#{cm.CS_ISNULL},#{cm.CS_ISPRIMARY},#{cm.CS_CHARSET},#{cm.TABLE_ID},#{cm.CS_UPDATETIME},#{cm.CS_TABLE_NAME}\n)\n" +
            "      </foreach>"+
            "</script>")
    void insertBatchColumnMeta(@Param("cml") List<ColumnMeta> cml);
    @Insert("call mergeTableMeta()")
    void mergeTableMeta();

    @Insert("call mergeColumnMeta()")
    void mergeColumnMeta();

    @Insert("call mergeMeta()")
    void mergeMeta();
}
