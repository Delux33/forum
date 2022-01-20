<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <#if user??>
        <div align="center">
            <h1 class="display-4">Привет ${name}!</h1>
            <hr class="my-1" width="590">
            <p class="lead">Это личинка форума с множеством багов, пользуйся на здоровье :)</p>
        </div>
    <#else>
        <div align="center">
            <h1 class="display-4">Привет, незнакомец</h1>
            <h3 class="display-4">Нажми кнопку ниже чтобы продолжить</h3>
            <a class="btn btn-primary" href="/login" role="button">Login</a>
        </div>
    </#if>
</@c.page>