CREATE TABLE ${source_table}(
      ${Field}  ${FieType} ,
<#list portList as portInfo>${portInfo.columnName}     ${portInfo.csType} ,</#list>

);
