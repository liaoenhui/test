{
"job":{
"content":[
{


"reader":{
"parameter":{
"password":"${source_password}",
"column":[
"${source_table_id}",<#list portList as portInfo>"${portInfo.columnName}"<#if portInfo_has_next>,</#if></#list>

],
"connection":[
{
"jdbcUrl":[
"${source_url}"
],
"table":[
"${source_tableName}"

]
}
],
"splitPk":"  ",
"username":"${source_name}"
},
"name":"${source_drives}"
},
"writer": {
"name": "postgresqlwriter",
"parameter": {
"column": ["${storage_csName}",<#list portLists as portInfo>"${portInfo.columnName}"<#if portInfo_has_next>,</#if></#list>],
"connection": [
{
"jdbcUrl": "jdbc:postgresql://47.100.123.80:5432/label_data",
"table": [ "${source_tableName}" ]
}
],
"password": "Zysj@123",
"postSql": ["insert into tb_obj_${source_objetId} as a select ${storage_csName} as item_id,concat(<#list portLists as portInfo>'${portInfo.columnName} =>' , ${portInfo.columnName}<#if portInfo_has_next>, ',' ,</#if></#list>)::hstore as data from ${source_tableName} on CONFLICT(item_id) do update set data = a.data||excluded.data"],
"preSql": ["TRUNCATE ${source_tableName}","create table if not exists tb_obj_${source_objetId} (item_id varchar(32) NOT NULL PRIMARY KEY,data hstore)"],
"username": "postgres"
}
}
}
],

"setting":{
"speed":{
"channel":"10"
}
}
}
}