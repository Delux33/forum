<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="form-group"><b>Add new user</b></div>
    ${message!}
    <@l.login "/registration" true />
</@c.page>