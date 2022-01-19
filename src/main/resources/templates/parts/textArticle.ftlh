<#macro textArticle>
    <#if article.textArticle??>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <div class="m-2">${article.textArticle}</div>
        </div>
    <#else>
        <div align="center">Здесь почему-то пусто, это нужно исправить!</div>
    </#if>
</#macro>