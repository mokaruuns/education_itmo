<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-vs-coronavirus-65.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if now_page?? && now_page==item.href>
                            <a class="selected" href="${item.href}">${item.name}</a>
                        <#else>
                            <a class="usual" href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as p>
            <section>
                <div class="header">Post #${p.id}</div>
                <div class="body">
                    ${p.text[0..*250]}
                    <#if [250, p.text?length]?min==250>...</#if>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${p.id?c}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2020 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user>
    <a href="/user?handle=${user.handle}">${user.name}</a>
</#macro>

<#function findBy items key value>
    <#assign i = 0>
    <#list items as item>
        <#if item[key] == value>
            <#return i/>
        </#if>
        <#assign i++>
    </#list>
</#function>

<#macro article po>
        <article>
            <div class="title"><a href="/post?post_id="${po.id}></a>${po.title}</div>
            <div class="information">By user_${po.id}, 2 days ago, translation</div>
            <div class="body">
                <#if now_page?? && now_page=="/index">
                    ${po.text[0..*250]}
<#--                    <#if [250, po.text?length]?min==250>...</#if>-->
                    <#if (po.text?length > 250)>...</#if>
                <#else>
                    ${po.text}
                </#if>
            </div>
            <#--                    <ul class="attachment">-->
            <#--                        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>-->
            <#--                        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>-->
            <#--                    </ul>-->
            <div class="footer">
                <div class="left">
                    <img src="/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    2 days ago
                    <img src="/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#"></a>
                </div>
            </div>
        </article>

</#macro>
