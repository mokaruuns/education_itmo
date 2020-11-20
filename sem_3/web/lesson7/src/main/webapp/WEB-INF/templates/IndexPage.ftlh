<#import "commons.ftlh" as c>

<@c.page>
    <ul class="articles">
        <template>
            <li>
                <article class="article">
                    <div class="title"></div>
                    <div class="information"></div>
                    <div class="body"></div>
                    <div class="footer">
                        <div class="left">
                            <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                            <span class="positive-score">+173</span>
                            <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                        </div>
                        <div class="right">
                            <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                            <a class="date"></a>
                            <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                            <a href="#">68</a>
                        </div>
                    </div>
                </article>
            </li>
        </template>
        <li class="noData">
            No data
        </li>
    </ul>


    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");
            ajax({
                    action: "findAll"
                },
                function (response) {
                    response["articleViews"].forEach(function (articleView) {
                        $noData.hide();
                        const $tml = $($(".articles template").prop("content")).clone();
                        $tml.find(".title").text(articleView.article["title"]);
                        $tml.find(".body").text(articleView.article["text"])
                        $tml.find(".information").text("By " + articleView.user["login"]);
                        $tml.find(".date").text(articleView.article["creationTime"]);
                        $(".articles").append($tml);
                    });
                });
        })
    </script>
</@c.page>
