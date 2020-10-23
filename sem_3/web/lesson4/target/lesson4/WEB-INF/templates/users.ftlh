<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Handle</th>
                <th>Id</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td><@c.userlink u/></td>
                    <td>${u.handle}</td>
                    <td>${u.id}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
<#--    <ul>-->
<#--        <#list users as u>-->
<#--            <li>-->
<#--                <#if user?? && user.id == u.id>*</#if>-->
<#--                <@c.userlink u/>-->
<#--            </li>-->
<#--        </#list>-->
<#--    </ul>-->
</@c.page>
