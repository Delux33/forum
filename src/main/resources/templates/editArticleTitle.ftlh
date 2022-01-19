<#import "parts/common.ftlh" as c>
<#import "parts/showMessagesOrArticles.ftlh" as s>
<#import "parts/editor.ftlh" as e>

<@c.page>
    <@e.editor "/topics/${message.id}/edit/${article.id}" false/>
    <@s.show "/topics/${message.id}" false/>
</@c.page>