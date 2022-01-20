<#import "parts/common.ftlh" as c>
<#import "parts/addMessageOrArticle.ftlh" as a>
<#import "parts/showMessagesOrArticles.ftlh" as s>

<@c.page>
    <div class="row mb-3">
        <div class="col">
            <a href="/topics">
                <span class="lnr lnr-arrow-left"></span>
            </a>
        </div>
    </div>
    <@a.addTitle "/topics/${id_message}" false/>
    <@s.show "/topics/${id_message}" false/>
</@c.page>