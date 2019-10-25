package com.zysj.label_manage.controller;

import com.zysj.label_manage.entity.DataSource;
import com.zysj.label_manage.service.DatasourceService;
import com.zysj.label_manage.util.DataSourceUtil;
import com.zysj.service_common.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@RestController
@RequestMapping("datasource")
@Api(tags = "获取数据源数据api")
public class DatasourceController {


    @Autowired
    private DatasourceService datasourceService;


    /**
     * 获取数据源版本信息
     */
    @RequestMapping(value = {"/getDatasourceEditionList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取数据库版本信息")
    @ApiImplicitParam(name = "dataTypename", value = "数据源类型", required = true, dataType = "String",paramType = "query")
    public R getDatasourceEditionList(@RequestParam(value="dataTypename") String dataTypename){
        return datasourceService.getDatasourceEditionList(dataTypename);
    }
    /**
     * 获取数据库类型信息
     */
    @RequestMapping(value = {"/getDatasourceTypeList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取数据库类型信息")
    public R getDatasourceTypeList(){
        return datasourceService.getDatasourceTypeList();
    }

    /**
     * 获取数据源列表
     */
    @RequestMapping(value = {"/getDatasourceList"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取数据源列表数据")
    public R getDatasourceList(){
        return datasourceService.getDatasourceList();
    }

    /**
     * 新增数据源
     * @param dataSource
     * @return
     */
    @ApiOperation(value = "新增数据源")
    @RequestMapping(value = {"/addDatasourceInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "dataSource", value = "数据源实体tb_DataSource", required = true, dataType = "DataSource")
    public R addDatasourceInfo(@RequestBody DataSource dataSource){
        return datasourceService.addDatasourceInfo(dataSource);
    }

    /**
     * 模糊查询数据源
     * @param name
     * @return
     */
    @ApiOperation(value = "模糊查询数据源英文名称")
    @RequestMapping(value = {"/getDatasource"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "name", value = "数据源的英文名称", required = true, dataType = "String",paramType = "query")
    public R getDatasource( @RequestParam(value="name") String name){
        return datasourceService.getDatasource(name);
    }

    /**
     * 测试新增数据源
     * @param dataSource
     * @return
     */
    @ApiOperation(value = "测试新增数据源")
    @RequestMapping(value = {"/addceshiDatasourceInfo"}, method = RequestMethod.POST)
    @ApiImplicitParam(name = "dataSource", value = "数据源实体tb_DataSource", required = true, dataType = "DataSource")
    public R addceshiDatasourceInfo(@RequestBody  DataSource dataSource){
        return DataSourceUtil.createWord(dataSource);
    }

    /**
     * 修改数据源
     * @param dataSource
     * @return
     */
    @ApiOperation(value = "修改数据源")
    @RequestMapping(value = {"/editDatasourceInfo"}, method = RequestMethod.POST)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "dataSource", value = "数据源实体tb_DataSource", required = true, dataType = "DataSource")
    })
    public R editDatasourceInfo(@RequestBody DataSource dataSource){
        return datasourceService.editDatasourceInfo(dataSource);
    }

    /**
     * 获取单个数据源信息
     * @return
     */

    @ApiOperation(value = "获取单个数据源信息")
    @ApiImplicitParam(name = "id",value = "主键id",required = true,dataType="String",paramType = "path")
    @RequestMapping(value = {"/getDatasourceInfo/{id}"}, method = RequestMethod.GET)
    public R getDatasourceInfo(@PathVariable("id")  String id){
        return datasourceService.getDatasourceInfo(id);
    }
}
