<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="rawPost" type="ru.itmo.wp.form.RawPost" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form">
        <div class="header">Write Post</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="writePost"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input autofocus id="title" name="title" value="${rawPost.title!}"/>
                    </div>
                    <@c.error "rawPost.title"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text">${rawPost.text!}</textarea>
                    </div>
                    <@c.error "rawPost.text"/>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="tags">Tags</label>
                    </div>
<#--                    <div class="value">-->
<#--                        <textarea id="tags" name="tags">${rawPost.tags!}</textarea>-->
<#--                    </div>-->
                    <div class="value">
                        <input autofocus id="tags" name="tags" value="${rawPost.tags!}"/>
                    </div>
                    <@c.error "rawPost.tags"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Write">
                </div>
            </form>
        </div>
    </div>
</@c.page>
