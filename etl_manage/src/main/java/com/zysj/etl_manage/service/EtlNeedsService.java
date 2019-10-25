package com.zysj.etl_manage.service;

import com.zysj.ts.tssync.entity.TagSyncEntity;

/**
 * @author 尚先生
 * @date 2019/9/6 14:51
 */
public interface EtlNeedsService {

    int etlGenerateService();
    int etlGenerateService(TagSyncEntity syncObj);
}
