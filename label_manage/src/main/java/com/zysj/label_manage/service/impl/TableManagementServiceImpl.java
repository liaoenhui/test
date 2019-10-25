package com.zysj.label_manage.service.impl;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.TableManagementDao;
import com.zysj.label_manage.entity.ObjectTable;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.label_manage.service.TableManagementService;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/24 9:14
 */
@Service
public class TableManagementServiceImpl implements TableManagementService {
    @Autowired
    private TableManagementDao tableManagementDao;

    /**
     *
     * @param objectId  主键id
     * @return
     */
    @Override
    public R getTableRelationsInfo(String objectId) {
        List<Map<String,Object>> list = tableManagementDao.getTableRelationsInfo(objectId);
        List<Map<String,Object>> lists= tableManagementDao.getWhetherMasterTable(objectId);
        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list)).put("IS_MAIN_TABLE",lists);
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
    /**
     *查询主体(关系)下的关联主体
     * @param objectId  主键id
     * @return
     */
    @Override
    public R getRelatedSubject(String objectId) {
        List<Map<String,Object>> list = tableManagementDao.getRelatedSubject(objectId);
        List<Map<String,Object>> RelatedSubjectslist =  tableManagementDao.getRelatedSubjects((String) list.get(0).get("MAIN_OBJECT_ID"),(String) list.get(0).get("REF_OBJECT_ID"));
        if (RelatedSubjectslist != null && RelatedSubjectslist.size() != 0 ) {
            return R.ok().put("list", RelatedSubjectslist);
        }else {
            return R.ok("该关系下未存在关联主体").put("list",RelatedSubjectslist);
        }
    }
    /**
     * 删除标签表绑定数据
     * @param TagId
     * @return
     */
    @Override
    @Transactional
    public R deleteTagTableInfo(String TagId) {
        try {
            tableManagementDao.deleteTagTableInfo(TagId);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("解除绑定失败");
        }

    }
    /**
     * 删除表绑定数据
     * @param TableId
     * @return
     */
    @Override
    public R deleteTableInfo(String TableId) {
        List<Map<String,Object>> list =  tableManagementDao.getTableInfo(TableId);
        if (list.size()==0 ){
            try {
                tableManagementDao.deleteTableInfo(TableId);
                return R.ok();
            }catch (Exception e){
                // 事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
                return R.error("解除绑定失败");
            }
        }else {
            return R.error(1,"该表下存在数据无法解绑,请先解绑标签");
        }
    }
    /**
     *  查询主体内关系下拉
     *
     * @return
     */
    @Override
    public R getSubjectRelations() {
        List<Map<String,Object>> list = tableManagementDao.getSubjectRelations();
        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
    /**
     *  查询标签内类目下拉
     *
     * @return
     */
    @Override
    public R getTagTableInfo() {
        List<Map<String,Object>> list = tableManagementDao.getTagTableInfo();
        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }

    /**
     * 根据表id查询出来该表下的标签
     * @param objectId 主体id
     * @param surfaceId  表id
     * @param  isBounds 是否已绑定 （1为绑定 0为未绑定）
     * @return
     */

    @Override
    public R getRelatedTags(String surfaceId, String isBounds,String objectId) {
        List<Map<String,Object>> list = tableManagementDao.getRelatedTags(surfaceId,isBounds,objectId);
        List<Map<String,Object>> MasterTaglist =  tableManagementDao.getWhetherMasterTag(surfaceId,objectId);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list).put("countList",list.size()).put("IS_MAIN_TAG",MasterTaglist);
        }else {
            return R.ok("查询结果为空").put("list",list).put("countList",list.size()).put("IS_MAIN_TAG",MasterTaglist);
        }

    }
    /**
     * 根据表id和 标签字段模糊查询
     * @param surfaceId  表id
     * @param  tableName 字段名称
     * @return
     */

    @Override
    public R getVagueTags(String surfaceId, String tableName) {
        List<Map<String,Object>> list = tableManagementDao.getVagueTags(surfaceId,tableName);


        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("查询结果为空").put("list",list);
        }

    }
    /**
     *   根据表id以及主体id查询表下的标签以及字段是否存在该主体
     * @param objectId  主体id
     *@param  tableId 表id
     * @return
     */
    @Override
    public R getMainTagInfo(String objectId, String tableId) {
        List<Map<String,Object>> list = tableManagementDao.getMainTagInfo(objectId,tableId);
        if (list != null && list.size() != 0) {
            return R.ok("该关联主体已存在").put("list",list);
        }else {
            return R.ok("该关联主体可以添加").put("list",list);
        }
    }


    /**
     *
     * @param dataId 数据源id  tableName表名字模糊查询
     * @param tableName
     * @return
     */
    @Override
    public R getvagueTable(String dataId, String tableName) {
        List<Map<String,Object>> list = tableManagementDao.getvagueTable(dataId,tableName);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("查询结果为空").put("list",list);
        }}



    /**
     *
     * @param dataId 表id  tableName字段名字模糊查询
     * @param
     * @return
     */
    @Override
    public R getTableField(String dataId) {
        List<Map<String,Object>> list = tableManagementDao.getTableField(dataId);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("查询结果为空").put("list",list);
        }}

    /**
     *
     * @param SubjectId 主体id
     * @param tableName  表名
     * @return 返回一个R
     */
   @Override
    public R getSearchTable(String SubjectId, String tableName) {
        List<Map<String,Object>> list = tableManagementDao.getSearchTable(SubjectId,tableName);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list).put("LabelNumber",list.size());
        }else {
            return R.ok("查询结果为空");
        }}
    /**
     * 新增关系表
     * @param objectTable
     * @return
     */
    @Override
    @Transactional
    public R addTableRelationsInfo(ObjectTable objectTable) {


        objectTable.setCREATE_TIME(DateUtils.formatDate(new Date()));


        try {
            tableManagementDao.addTableRelationsInfo(objectTable);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }
    }
    /**
     * 新增标签表数据
     * @param tagObjectTag
     * @return
     */
    @Override
    @Transactional
    public R addTagTableInfo(TagObjectTag tagObjectTag) {

        tagObjectTag.setTAG_ID(UuidUtil.get32UUID());
        tagObjectTag.setCREATE_TIME(DateUtils.formatDate(new Date()));


        try {
            tableManagementDao.addTagTableInfo(tagObjectTag);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }
    }
}
