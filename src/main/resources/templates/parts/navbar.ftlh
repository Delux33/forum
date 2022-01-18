<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Delport</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <#if user?? && (currentUserId != -1)>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user-messages/${currentUserId}">My messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
                <form method="get" action="/user/search" class="form-inline ml-5">
                    <input type="text" name="name" maxlength="20" class="form-control mr-sm-2"
                           placeholder="Search users"/>
                    <button class="btn btn-outline-secondary" type="submit">
                        Go
                    </button>
                </form>
            </#if>
    </div>
    </ul>
    <#if user?? && (currentUserId != -1)>
        <#if isAdmin>
            <div class="navbar-text">
                <a class="list-of-users mr-4" href="/user"></a>
            </div>
        </#if>
        <div class="navbar-text">${name}</div>
        <a class="logout"><span><@l.logout /></span></a>
    <#else>
        <a class="login" href="/login"><span>Log In</span></a>
    </#if>
    </div>
</nav>