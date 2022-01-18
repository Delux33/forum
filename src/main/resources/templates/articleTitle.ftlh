<#import "parts/common.ftlh" as c>
<#import "parts/addMessageOrArticle.ftlh" as a>
<#import "parts/showMessagesOrArticles.ftlh" as s>

<@c.page>
    <@a.add "/topics/${id_message}" false/>
    <@s.show false/>
</@c.page>