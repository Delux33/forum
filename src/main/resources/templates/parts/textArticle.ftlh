<#macro textArticle>
    <#if article.textArticle??>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <div class="m-2">${article.textArticle}</div>
        </div>
    <#else>
        <#if isOwnerArticle>
            <div align="center">
                <h4>Здесь почему-то пусто, это нужно исправить!</h4>
            </div>
        <#else>
            <div align="center">
                <h4>Автор статьи еще не написал текст, ожидайте</h4>
            </div>
        </#if>
    </#if>
</#macro>