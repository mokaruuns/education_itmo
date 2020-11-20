<#-- @ftlvariable name="title" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="body">
        <form method="post" action="">
            <input type="hidden" name="action" value="create"/>
            <div class="field">
                <div class="title">
                    <label for="title">Title</label>
                </div>
                <div class="value">
                    <input id="title" name="title" value="${title!}"/>
                </div>
            </div>
            <div class="field">
                <div class="text">
                    <label for="text">Text</label>
                </div>
                <div class="value">
                    <textarea id="text" name="text"></textarea>
                </div>
            </div>
            <div class="error">
                ${error!}
            </div>
            <div class="button-field">
                <input type="submit" value="Create">
            </div>
        </form>
    </div>
    <script>
        $(function () {
            $(".enter form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const $error = $(this).find(".error");
                ajax({
                        action: "create",
                        title,
                        text
                    }, function (response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        } else {
                            location.href = response["redirect"];
                            // alert("OK");
                        }
                    }
                );
                return false;
            });
        })
    </script>
</@c.page>
