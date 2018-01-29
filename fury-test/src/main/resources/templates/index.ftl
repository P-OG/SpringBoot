<!DOCTYPE html>
<html>
<head lang="m">
    <meta charset="UTF-8"/>
    <title>FreeMarker测试页面</title>
</head>
<body>
    ${name}
    <#if sex==1>男
    <#elseif sex==2>女
    <#else>其他
    </#if>
    <#list userlist as user>
    ${user}
    </#list>
</body>
</html>