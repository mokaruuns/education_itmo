[#-- @ftlvariable name="articles" type="ru.itmo.web.lesson7.model.Ar[]" --]

<#import "commons.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="articles">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as u>
                <tr>
                    <td><@c.userlink u/></td>
                    <td>${u.handle}</td>
                    <td>${u.id}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
