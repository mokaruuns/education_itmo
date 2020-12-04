<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="user">
        <#if user??>
            <p class="id">id: ${user.id}</p>
            <p class="login">login: ${user.login}</p>
            <p class="creationTime">created: ${user.creationTime}</p>
        <#else>
            ${message}
        </#if>
    </div>
</@c.page>
