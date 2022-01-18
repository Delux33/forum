<#import "parts/common.ftlh" as c>
<#import "parts/addMessageOrArticle.ftlh" as a>
<#import "parts/showMessagesOrArticles.ftlh" as s>

<@c.page>
    <@a.add "/topics" true/>
    <@s.show true/>
</@c.page>