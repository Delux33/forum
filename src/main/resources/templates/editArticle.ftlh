<#import "parts/common.ftlh" as c>
<#import "parts/textArticle.ftlh" as t>
<#include "parts/security.ftlh" />

<@c.page>
    <form method="post" action="/topics/${id_message}/${article.id}/article" enctype="multipart/form-data">
        <div class="form-floating mb-3">
            <input type="text" name="textArticle" class="form-control ${(textArticleError??)?string('is-invalid', '')}"
                   value="<#if textArticle??>${textArticle}<#else>${article.textArticle}</#if>"
                   placeholder="Напиши сюда текст для своей статьи"/>
            <#if textArticleError??>
                <div class="invalid-feedback">
                    ${textArticleError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Изменить</button>
            <a href="/topics/${id_message}/${article.id}" type="submit" class="btn btn-primary ml-2">Отмена</a>
        </div>
    </form>
    <@t.textArticle />
</@c.page>