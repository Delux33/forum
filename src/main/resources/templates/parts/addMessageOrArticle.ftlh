<#macro add path isTopic>
    <#if !isTopic>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Добавить название для статьи
        </a>
    <#else>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Добавить новую тему
        </a>
    </#if>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="${path}" enctype="multipart/form-data">
                <div class="form-group">
                    <#if !isTopic>
                        <input type="text" class="form-control" name="title" placeholder="Введите название статьи"/>
                    <#else>
                        <input type="text" class="form-control" name="text" placeholder="Введите название темы"/>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</#macro>