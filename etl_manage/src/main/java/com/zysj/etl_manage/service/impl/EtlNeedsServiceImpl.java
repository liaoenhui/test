package com.zysj.etl_manage.service.impl;/**
 * Created by 尚先生 on 2019/9/6.
 */

import com.zysj.etl_manage.dao.EtlInfoDao;
import com.zysj.etl_manage.dao.EtlNeedsDao;
import com.zysj.etl_manage.entity.EtlParameter;
import com.zysj.etl_manage.entity.TagSh;
import com.zysj.etl_manage.service.EtlNeedsService;
import com.zysj.etl_manage.util.WordUtil;
import com.zysj.etl_manage.util.ZookUtil;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 尚先生
 * @date 2019/9/6 14:52
 */
@Service("etlNeedsService")
public class EtlNeedsServiceImpl implements EtlNeedsService {

    @Autowired
    EtlNeedsDao etlNeedsDao;
    @Autowired
    EtlInfoDao etlInfoDao;
    @Autowired
    private ZookeeperStoreService zookeeperStoreService;
    //JSON输出本地路径
    @Value("${etl.localPath}")
    private String localPath;

    @Override
    public int etlGenerateService() {

        //数据源url
        String datasourceurl = null;
        //数据源的插件名称
        String source_drivesurl = null;
        //字段名称
        String isNaInTag = null;
        //字段类型
        String isNaInType = null;
        int result = 1;
        //字段名称集合
        List isNaInTagList = new ArrayList();
        //字段类型集合
        List isNaInTypeList = new ArrayList();
        Map<String, Object> etlmap = new HashMap<String, Object>();
        List<Map<String, Object>> portList = new ArrayList<>();
        List<TagSh> TagShList = etlNeedsDao.etlTagSh(1);
        for (int i = 0; i < TagShList.size(); i++) {
            String tabid = TagShList.get(i).getTABLE_ID();
            List<EtlParameter> etlList = etlInfoDao.getEtlInfo(tabid);
            int etlurl = etlList.get(0).getDS_TYPE();
            int etl = etlList.size();
            switch (etlurl) {
                case 0:
                    datasourceurl = "jdbc:mysql://" + etlList.get(0).getDS_IP() + ":" + etlList.get(0).getDS_PORT() + "/" + etlList.get(0).getDATASOURCE_NAME();

                    source_drivesurl = "mysqlreader";
                    break;
                case 1:
                    datasourceurl = " jdbc:oracle:thin:@//" + etlList.get(0).getDS_IP() + ":" + etlList.get(0).getDS_PORT() + "/orcl." + etlList.get(0).getDATASOURCE_NAME();
                    source_drivesurl = "oraclereader";
                    break;
                //TODO 还少一些数据源判断 等待添加
            }
            for (int j = 0; j < etl; j++) {
                int csIsPRimAry = etlList.get(j).getIS_MAIN_TAG();

                switch (csIsPRimAry) {
                    case 0:
                        isNaInTag = etlList.get(j).getCOLUMN_NAME();
                        isNaInType = etlList.get(j).getCS_TYPE();
                        break;
                    case 1:
                        Map<String, Object> portMap1 = new HashMap<String, Object>();
                        portMap1.put("columnName", etlList.get(j).getCOLUMN_NAME());
                        portMap1.put("csType", etlList.get(j).getCS_TYPE());
                        portList.add(portMap1);
                        break;

                }
            }
            String source_password = etlList.get(0).getDS_PASSWORD();
            etlmap.put("source_password", source_password);

            etlmap.put("source_table_id", isNaInTag);
            etlmap.put("portList", portList);
            etlmap.put("source_url", datasourceurl);
            etlmap.put("source_objetId", etlList.get(0).getOBJECT_ID());
            etlmap.put("source_tableId", etlList.get(0).getTABLE_ID());
            etlmap.put("source_tableName", etlList.get(0).getTABLE_NAME());
            etlmap.put("source_name", etlList.get(0).getDS_USERNAME());
            etlmap.put("source_drives", source_drivesurl);

            etlmap.put("storage_tableName", etlList.get(0).getTABLE_ID());
            etlmap.put("storage_csName", isNaInTag);
            etlmap.put("storage_cstype", isNaInType);

            etlmap.put("portLists", portList);
            result = WordUtil.createWord(etlmap, "eventpgflowmysql.ftl", localPath + etlList.get(0).getOBJECT_ID(), etlList.get(0).getTABLE_ID() + ".json");
           if (result == 0) {
                ZookUtil.createWord(localPath + etlList.get(0).getOBJECT_ID() + "\\" + etlList.get(0).getTABLE_ID() + ".json", etlList.get(0).getOBJECT_ID() + "/" + etlList.get(0).getTABLE_ID());
            }

            etlNeedsDao.updateShStaTus(etlList.get(0).getSH_ID());

        }


        return result;
    }


    @Override
    public int etlGenerateService(TagSyncEntity syncObj){
        //数据源url
        String datasourceurl = null;
        //数据源的插件名称
        String source_drivesurl = null;
        //字段名称
        String isNaInTag = null;
        //字段类型
        String isNaInType = null;
        int result = 1;
        //字段名称集合
        List isNaInTagList = new ArrayList();
        //字段类型集合
        List isNaInTypeList = new ArrayList();
        Map<String, Object> etlmap = new HashMap<String, Object>();
        List<Map<String, Object>> portList = new ArrayList<>();
        String tabid = syncObj.getTableId();
        List<EtlParameter> etlList = etlInfoDao.getEtlInfo(tabid);
        EtlParameter ep = etlList.get(0);
        int etlurl = ep.getDS_TYPE();
        int etl = etlList.size();
        switch (etlurl) {
            case 0:
                datasourceurl = "jdbc:mysql://" + ep.getDS_IP() + ":" +ep.getDS_PORT() + "/" + ep.getDATASOURCE_NAME();

                source_drivesurl = "mysqlreader";
                break;
            case 1:
                datasourceurl = " jdbc:oracle:thin:@//" + ep.getDS_IP() + ":" + ep.getDS_PORT() + "/orcl." + ep.getDATASOURCE_NAME();
                source_drivesurl = "oraclereader";
                break;
            //TODO 还少一些数据源判断 等待添加
        }
        for (EtlParameter epi:etlList) {
            int csIsPRimAry = epi.getIS_MAIN_TAG();
            switch (csIsPRimAry) {
                case 1:
                    isNaInTag = epi.getCOLUMN_NAME();
                    isNaInType = epi.getCS_TYPE();
                    break;
                case 0:
                    Map<String, Object> portMap1 = new HashMap<String, Object>();
                    portMap1.put("columnName", epi.getCOLUMN_NAME());
                    portMap1.put("csType", epi.getCS_TYPE());
                    portList.add(portMap1);
                    break;
            }
        }
        String source_password = ep.getDS_PASSWORD();
        etlmap.put("source_password", source_password);

        etlmap.put("source_table_id", isNaInTag);
        etlmap.put("portList", portList);
        etlmap.put("source_url", datasourceurl);
        etlmap.put("source_objetId", ep.getOBJECT_ID());
        etlmap.put("source_tableId", ep.getTABLE_ID());
        etlmap.put("source_tableName", ep.getTABLE_NAME());
        etlmap.put("source_name", ep.getDS_USERNAME());
        etlmap.put("source_drives", source_drivesurl);

        etlmap.put("storage_tableName", ep.getTABLE_ID());
        etlmap.put("storage_csName", isNaInTag);
        etlmap.put("storage_cstype", isNaInType);

        etlmap.put("portLists", portList);
        result = WordUtil.createWord(etlmap, "eventpgflowmysql.ftl", localPath + ep.getOBJECT_ID(), ep.getTABLE_ID() + ".json");
        if (result == 0) {
            zookeeperStoreService.upload(localPath + ep.getOBJECT_ID() + "\\" + ep.getTABLE_ID() + ".json", ep.getOBJECT_ID() + "/" + ep.getTABLE_ID());
        }

        etlNeedsDao.updateScriptStaTus(syncObj.getOptId());
        return 0;
    }
}
