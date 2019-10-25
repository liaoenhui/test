package com.zysj.label_manage.service.impl;
/**
 * Created by 尚先生 on 2019/10/8.
 */

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.DetailsSubjectDao;
import com.zysj.label_manage.entity.ObjectTagClass;
import com.zysj.label_manage.entity.TagDic;
import com.zysj.label_manage.entity.TagObjectTag;
import com.zysj.label_manage.service.DetailsSubjectService;
import com.zysj.label_manage.util.PinyinConvert;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @author 尚先生
 * @date 2019/10/8 13:44
 */
@Service
public class DetailsSubjectServiceImpl implements DetailsSubjectService{
    @Autowired
    DetailsSubjectDao detailsSubjectDao;
    /**
     * 根据id来获取类目
     * @param TAG_CLASS_ID  标签的ID
     * @param OBJECT_ID 主体id
     * @return
     */
    @Override
    public R getTagNode(String OBJECT_ID,String TAG_CLASS_ID) {
        List<Map<String,Object>> list = detailsSubjectDao.getTagNode(OBJECT_ID,TAG_CLASS_ID);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok().put("list",list);
        }

    }

    /**
     * 根据主体id获取主体的详情
     * @param  OBJECT_ID 主键的id
     * @return
     */
    @Override
    public R getSubjectDetails(String OBJECT_ID) {
        List<Map<String,Object>> list = detailsSubjectDao.getSearchTable(OBJECT_ID);
        List<Map<String,Object>> lists =  detailsSubjectDao.getSearchTag(OBJECT_ID);
        if (list != null && list.size() != 0) {
            return R.ok().put("list",list).put("ObJctTag",lists);
        }else {
            return R.ok().put("list",list).put("ObJctTag",lists);
        }

    }
    /**
     *根据类目id来获取到该类目下的标签数据
     * @param OBJECT_ID 主体id
     * @param TAG_CLASS_ID  类目id
     * @return
     */
    @Override
    public R getTagDetails(String OBJECT_ID,String TAG_CLASS_ID) {
        List<Map<String,Object>> list = detailsSubjectDao.getTagDetails(OBJECT_ID,TAG_CLASS_ID);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok().put("list",list);
        }

    }

    /**
     * 新增字典表数据
     * @param
     * @return
     */
    @Override
    @Transactional
    public R addTagDicInfo(List<TagDic>  list) {

      for (int i=0; i<list.size() ;i++){
          list.get(i).setDic_code(PinyinConvert.getPingYin( list.get(i).getDic_name()));
      }
        try {
            detailsSubjectDao.addTagDicInfo(list);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }
    }
    /**
     * 修改字典表
     * @param list
     * @return
     */
    @Override
    @Transactional
    public R editTagDicInfo(List<TagDic> list) {
        for (int i=0; i<list.size() ;i++){
            list.get(i).setDic_code(PinyinConvert.getPingYin( list.get(i).getDic_name()));
        }
        try {
            detailsSubjectDao.editTagDicInfo(list);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("修改失败");
        }

    }
    /**
     * 修改标签表数据（添加字典表的id到标签）
     * @param tagObjectTag
     * @return
     */
    @Override
    public R editModifyLabelsInfo(TagObjectTag tagObjectTag) {
        try {
            detailsSubjectDao.editModifyLabelsInfo(tagObjectTag);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("修改失败");
        }

    }

    /**
     * 获取字典表信息
     * @return map
     */
    @Override
    public R getDictionaryList(String dic_name) {
        List<Map<String,Object>> list = detailsSubjectDao.getDictionaryList(dic_name);

        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
    /**
     * 获取字典表信息
     * @return map
     */
    @Override
    public R getDictionaryTableList(String dic_name) {

        List<Map<String,Object>> list = detailsSubjectDao.getDictionaryTableList(dic_name);

        if (list != null && list.size() != 0) {
            return R.ok("该字典值已存在不可以重复添加").put("list", list.size());
        }else {
            return R.ok("该字典值不存在可以添加").put("list",list.size());
        }
    }
    /**
     * 获取该标签code是否存在
     * @return map
     */
    @Override
    public R getDictionaryTagList(String tag_code,String objectId,String tag_id) {

        List<Map<String,Object>> list = detailsSubjectDao.getDictionaryTagList(tag_code,objectId,tag_id);

        if (list != null && list.size() != 0) {
            return R.ok("该标签code已存在不可以重复添加").put("list", list.size());
        }else {
            return R.ok("该标签code不存在可以添加").put("list",list.size());
        }
    }
    /**
     * 获取字典表信息
     * @return map
     */
    @Override
    public R getDictionaryListInfo(String dic_name) {
        List<Map<String,Object>> list = detailsSubjectDao.getDictionaryListInfo(dic_name);

        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
    /**
     * 打算用做关系图谱
     * @return
     *
     */

    @Override
    public R getObjectTagList() {
        List<Map<String,Object>> SubjectList = detailsSubjectDao.getObjectTagList();
        List<Map<String,Object>> SubjectTagList = detailsSubjectDao.getSubjectTagList();
        return R.ok().put("node",SubjectList).put("links",SubjectTagList);
    }
    /**
     * 用做单个关系主体图谱
     * @return
     *
     */
    //TODO 根据单个主体获取主体 关系 标签 图谱 后续需要完善
    @Override
    public R getObjectTagInfoList(String objectId ) {
        List<Map<String,Object>> SubjectList = detailsSubjectDao.getObjectTagList();
        List<Map<String,Object>> SubjectTagList = detailsSubjectDao.getSubjectTagList();
        List<Map<String,Object>> InfoSubjectList = new ArrayList<Map<String,Object>>(SubjectList);
        List<Map<String,Object>> InfoSubjectTagList = new ArrayList<Map<String,Object>>(SubjectTagList);
        List<Map<String,Object>> resultSubjectList  = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> resultSubjectTagList  = new ArrayList<Map<String,Object>>();
        recurse(objectId,InfoSubjectList,InfoSubjectTagList,resultSubjectList,resultSubjectTagList);
        System.out.println(resultSubjectList);
        System.out.println(resultSubjectTagList);
        return R.ok().put("node",resultSubjectList).put("links",resultSubjectTagList);

    }

    //TODO 需要修改

    /**
     *   该部分主要用于递归出所有跟主体（id）相关的标签以及关系 (主体)
     * @param objId  主体id
     * @param casheSubjectList 用于递归以及清除的 主体（关系） 集合
     * @param casheSubjectTagList 用于递归以及清除的 标签（关系） 集合
     * @param resultSubjectList  返回的 node 集合
     * @param resultSubjectTagList 返回的 links 集合
     */
    private void recurse(String objId,List<Map<String,Object>> casheSubjectList,List<Map<String,Object>> casheSubjectTagList,List<Map<String,Object>> resultSubjectList,List<Map<String,Object>> resultSubjectTagList){


        for(Map<String,Object> node:casheSubjectList) {
            if (objId.equals(node.get("id"))) {
                resultSubjectList.add(node);
           }
        }
        if(objId.indexOf("tag_")==0){ return;}
        for(Iterator<Map<String,Object>> nodeIter = casheSubjectTagList.iterator();nodeIter.hasNext();){
            Map<String,Object> node  = nodeIter.next();
            if (objId.equals(node.get("source"))) {
                resultSubjectTagList.add(node);
                nodeIter.remove();
                List<Map<String,Object>> casheSubjectListRe = new ArrayList<Map<String,Object>>(casheSubjectList);
                List<Map<String,Object>> casheSubjectTagListRe = new ArrayList<Map<String,Object>>(casheSubjectTagList);
                recurse(node.get("target").toString(),casheSubjectListRe,casheSubjectTagListRe,resultSubjectList,resultSubjectTagList);
            }
            if (objId.equals(node.get("target"))) {
                resultSubjectTagList.add(node);
                nodeIter.remove();
                List<Map<String,Object>> casheSubjectListRe = new ArrayList<Map<String,Object>>(casheSubjectList);
                List<Map<String,Object>> casheSubjectTagListRe = new ArrayList<Map<String,Object>>(casheSubjectTagList);
                recurse(node.get("source").toString(),casheSubjectListRe,casheSubjectTagListRe,resultSubjectList,resultSubjectTagList);
            }
        }



    }



    /**
     * 新增标签类目
     * @param objectTagClass
     * @return
     */
    @Override
    public R addLabelCategory(ObjectTagClass objectTagClass) {


        objectTagClass.setTAG_CLASS_ID(UuidUtil.get32UUID());
        objectTagClass.setCREATE_TIME(DateUtils.formatDate(new Date()));

        try {
            detailsSubjectDao.addLabelCategory(objectTagClass);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }
    }
}
