<#import "./common.ftlh" as c>
<@c.page>
    <#list posts?reverse as p>
        <@c.article p/>
    </#list>
</@c.page>
