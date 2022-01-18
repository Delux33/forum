<#macro show isTopic>
    <#if isTopic>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <#list messages as message>
                <div class="bg-light border my-2">
                    <div class="m-2">
                        <span><a href="/topics/${message.id}">${message.text}</a></span>
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
                        <span>${article.title}</span>
                    </div>
                </div>
            <#else>
                <div align="center">Статьи отсутствуют</div>
            </#list>
        </div>
    </#if>
</#macro>