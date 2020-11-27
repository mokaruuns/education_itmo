<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user??>
                    <#if user.admin == true>
                        <th>Admin</th>
                    </#if>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?? >
                        <#if user.admin == true >
                            <td class="user_admin">
                                <div class="user_admin_value"></div>
                                <a href="#" class="user_admin_toggle"></a>
                            </td>
                        </#if>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");
            ajax({
                    action: "findAll"
                },
                function (response) {
                    $noData.hide();
                    response["users"].forEach(function (user) {
                        const $tmp = $($(".users template").prop("content")).clone();
                        $tmp.find(".user_id").text(user["id"]);
                        $tmp.find(".user_login").text(user["login"]);
                        if (response["user"] != null && response["user"]["admin"]) {
                            $tmp.find(".user_admin .user_admin_value").text(user["admin"] ? "true" : "false");
                            $tmp.find(".user_admin .user_admin_toggle").text(user["admin"] ? "disable" : "enable");
                        }
                        $tmp.find(".user_admin").attr("data-userId", user["id"]);
                        $(".users tbody").append($tmp);
                    });
                    $(".user_admin .user_admin_toggle").click(function () {
                        const $link = $(this).parent();
                        const isAdmin = $link.find(".user_admin_value").text();
                        ajax({
                                action: "changePermissions",
                                userId: $link.attr("data-userId"),
                                secondAction: isAdmin === "false",
                            }, function (response) {
                                if (response["changedUser"]) {
                                    $link.find(".user_admin_value").text(response["changedUser"]["admin"] ? "true" : "false");
                                    $link.find(".user_admin_toggle").text(response["changedUser"]["admin"] ? "disable" : "enable")
                                }
                            }
                        );
                    });
                });
        })
    </script>
</@c.page>
