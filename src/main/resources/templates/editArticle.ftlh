<#import "parts/common.ftlh" as c>
<#import "parts/showMessagesOrArticles.ftlh" as s>
<#import "parts/editor.ftlh" as e>

<@c.page>
    <@e.editor "/topics/edit/${message.id}" true/>
    <@s.show "/topics" true/>
</@c.page>