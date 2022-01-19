<#import "parts/common.ftlh" as c>
<#import "parts/textArticle.ftlh" as t>
<#include "parts/security.ftlh" />

<@c.page>
    <form method="post" action="/topics/${id_message}/${article.id}/article" enctype="multipart/form-data">
        <div class="form-floating mt-3">
            <input type="text" name="textArticle" class="form-control"
                   value="<#if article.textArticle??>${article.textArticle}</#if>"
                   placeholder="Напиши сюда текст для своей статьи"/>
        </div>
        <div class="form-group">
            <input type="hidden" name="idArticle" value="${article.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Изменить</button>
        </div>
    </form>
    <@t.textArticle />
</@c.page>