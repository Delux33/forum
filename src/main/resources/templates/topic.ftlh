<#import "parts/common.ftlh" as c>
<#import "parts/addMessageOrArticle.ftlh" as a>
<#import "parts/showMessagesOrArticles.ftlh" as s>

<@c.page>
    <@a.addTitle "/topics" true/>
    <@s.show "/topics" true/>
</@c.page>