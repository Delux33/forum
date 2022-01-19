<#macro show path isTopic>
    <#include "security.ftlh" />
    <#if isTopic>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <#list messages as message>
                <div class="bg-light border my-2">
                    <div class="m-2">
                        <a href="${path}/${message.id}">${message.text}</a>
                        <#if message.author.id == currentUserId || isAdmin>
                            <a href="${path}/edit/${message.id}">Редактировать</a>
                        </#if>
                    </div>
                </div>
            <#else>
                <div align="center">Темы отсутствуют, нужно что-то добавить</div>
            </#list>
        </div>
    <#else>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <#list articlesTitle as article>
                <div class="bg-light border my-2">
                    <div class="m-2">
                        <a href="${path}/${article.id}">${article.title}</a>
                    </div>
                </div>
            <#else>
                <div align="center">Добавь заголовок для своей статьи</div>
            </#list>
        </div>
    </#if>
</#macro>