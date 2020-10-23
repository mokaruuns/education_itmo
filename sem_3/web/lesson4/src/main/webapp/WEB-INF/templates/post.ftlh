<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>
<@c.page>
    <#assign postPosition = c.findBy(posts, "id", post_id!)!/>
    <#if postPosition?has_content>
        <#assign postNow = posts[postPosition]!/>
        <@c.article postNow/>
    <#else>
        No such post
    </#if>
</@c.page>
