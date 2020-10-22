<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>
<@c.page>
    <#assign userPosition = c.findBy(users, "handle", handle!"null")!/>
    <#if !userPosition?has_content>
        <#assign userPosition = c.findBy(users, "id", user_id!-1)!/>
    </#if>
    <#assign userNow = users[userPosition]!/>
    <#assign userBefore = users[userPosition - 1]!/>
    <#assign userAfter = users[userPosition + 1]!/>
    <#if userNow?has_content>
        <div>
            <div class="userbox">
                <div class="usertext">
                    <div class="userlink">
                        <a href="/user?handle=${userNow.handle}">${userNow.name}</a>
                    </div>
                    <div class="userdate">
                        <li>id: ${userNow.id}</li>
                        <li>handle: ${userNow.handle}</li>
                        <li>name: ${userNow.name}</li>
                    </div>
                </div>
                <div class="image">
                    <img src="/img/no-title.png" alt="no-title">
                </div>
            </div>
            <div class="arrows">
                <#if userBefore?has_content>
                    <a href="/user?handle=${userBefore.handle}">←</a>
                    <#else> <b>←</b>
                </#if>
                <#if userAfter?has_content>
                    <a href="/user?handle=${userAfter.handle}">→</a>
                <#else> <b>→</b>
                </#if>
            </div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
