<#macro show path isTopic>
    <#include "security.ftlh" />
    <#if isTopic>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <#list messages as message>
                <div class="shadow-lg bg-body rounded mt-3">
                    <div class="bg-light border my-2">
                        <div class="row m-2">
                            <a href="${path}/${message.id}">${message.text}</a>
                            <div class="row m-2">
                                <#if (message.author.id == currentUserId) || isAdmin>
                                    <a href="${path}/edit/${message.id}">Редактировать</a>
                                    <form method="post" action="${path}/delete/${message.id}">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button type="submit" class="btn btn-danger">Удалить</button>
                                    </form>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            <#else>
                <div align="center">Темы отсутствуют, нужно что-то добавить</div>
            </#list>
        </div>
    <#else>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <#list articleTitle as article>
                <div class="bg-light border my-2">
                    <div class="row m-2">
                        <a href="${path}/${article.id}">${article.title}</a>
                        <div class="row m-2">
                            <#if (article.author.id == currentUserId) || isAdmin>
                                <a href="${path}/edit/${article.id}">Редактировать</a>
                                <form method="post" action="${path}/delete/${article.id}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </form>
                            </#if>
                        </div>
                    </div>
                </div>
            <#else>
                <div align="center">Добавь заголовок для своей статьи</div>
            </#list>
        </div>
    </#if>
</#macro>