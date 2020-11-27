<#import "commons.ftlh" as c>
<@c.page>
    <div class="article datatable">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td class="id">${article.id}</td>
                    <td>${article.title}</td>
                    <td class="button"
                        data-id=${article.id}
                        data-userId=${article.userId}>
                        <input type="button" value=${article.hidden?string('Show', 'Hide')}>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <script>
        $(".article tr .button").click(function () {
            const $button = $(this).find("input");
            ajax({
                    action: "changeVisibility",
                    id: $(this).attr("data-id"),
                    userId: $(this).attr("data-userId"),
                    secondAction: $button.val()
                }, function (response) {
                    if (response["article"]) {
                        $button.val(response["article"].hidden ? "Show" : "Hide");
                    }
                }
            );
        });
    </script>
</@c.page>
