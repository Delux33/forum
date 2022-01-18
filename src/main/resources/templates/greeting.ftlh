<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <#if user??>
        <h1 class="display-4">Dear ${name}!</h1>
        <hr class="my-1" align="left" width="465">
        <p class="lead">Thank you for joining us, you can start using this site</p>
    <#else>
        <h1 class="display-4">Привет, незнакомец</h1>
        <h3 class="display-4">Нажми кнопку ниже чтобы продолжить</h3>
        <a class="btn btn-primary" href="/login" role="button">Login</a>
    </#if>
</@c.page>