{
"job":{
"content":[
{


"reader":{
"parameter":{
"password":"${source_password}",
"column":[
"${source_table_id}",
<#list portList as portInfo>
    "${portInfo.columnName}"
    <#if portInfo_has_next>,</#if>
</#list>

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
"writer":{
"name":"mongodbwriter",
"parameter":{
"userPassword":"",

"userName":"",
"dbName":"",
"collectionName":"${storage_tableName}",
"address":[
"127.0.0.1:27017"
],

"column":[
{

"name":"${storage_csName}",
"type":"${storage_cstype}"
},
<#list portLists as portInfo>

    {

    "name":"${portInfo.columnName}",
    "type":"${portInfo.csType}"
    }


    <#if portInfo_has_next>,</#if>
</#list>

],
"writeMode": {
"isReplace": "true",
"replaceKey": "${storage_csName}"
}

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