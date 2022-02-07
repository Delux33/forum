<#import "login.ftlh" as l>
<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <#if !user?? || (currentUserId == -1)>
        <a class="navbar-brand" href="/">Форум</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </#if>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <#if user?? && (currentUserId != -1)>
                <li class="nav-item">
                    <a class="nav-link" href="/topics">Список тем</a>
                </li>
            </#if>
    </div>
    <#if user?? && (currentUserId != -1)>
        <#if isAdmin>
            <div class="navbar-text">
                <a class="mr-4" href="/user">Список пользователей</a>
            </div>
        </#if>
        <div class="navbar-text">${name}</div>
        <a class="btn btn-primary-small"><span><@l.logout /></span></a>
    <#else>
    </#if>
</nav>