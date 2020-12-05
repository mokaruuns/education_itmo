<#-- @ftlvariable name="u" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="user">
        <#if u??>
            <p class="id">id: ${u.id}</p>
            <p class="login">login: ${u.login}</p>
            <p class="creationTime">created: ${u.creationTime}</p>
        <#else>
            ${message}
        </#if>
    </div>
</@c.page>
