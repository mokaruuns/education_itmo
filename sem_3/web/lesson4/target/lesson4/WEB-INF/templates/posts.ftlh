<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>
<@c.page>
    <#list posts?reverse as p>
        <#if p.user_id==user_id>
            <@c.article p/>
        </#if>
    </#list>
</@c.page>
