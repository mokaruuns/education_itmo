<#-- @ftlvariable name="p" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="post">
        <#if p??>
            <@c.post post=p/>
        <#else>
            ${message}
        </#if>
    </div>
    <#if user??>
        <div class="add_comment">
            <div class="form">
                <div class="header">Write comment</div>
                <div class="body">
                    <form method="post" action="">
                        <input type="hidden" name="action" value="writeComment"/>
                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text">${comment.text!}</textarea>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
    <div class="comments">
        <#if p??>
            <#if p.comments??>
                <#list p.comments as comment>
                    <@c.comment comment=comment></@c.comment>
                </#list>
            </#if>
        </#if>
    </div>
</@c.page>
