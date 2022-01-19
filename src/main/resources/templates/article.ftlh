<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row mb-3">
        <div class="col">
            <a href="/topics/${id_message}">Назад</a>
        </div>
    </div>
    <#if !article.textArticle??>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Написать статью
        </a>
        <div class="collapse" id="collapseExample">
            <div class="form-group">
                <form method="post" action="/topics/${id_message}/${article.id}" enctype="multipart/form-data">
                    <div class="form-floating mt-3">
                    <textarea class="form-control" name="textArticle" placeholder="Напиши сюда текст для своей статьи"
                              id="floatingTextarea2" style="height: 100px"></textarea>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group mt-3">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
    </#if>
    <#if article.textArticle??>
        <div class="shadow-lg p-3 mb-5 bg-body rounded mt-3">
            <div class="m-2">${article.textArticle}</div>
        </div>
    <#else>
        <div align="center">Здесь почему-то пусто, это нужно исправить!</div>
    </#if>
    </div>
</@c.page>