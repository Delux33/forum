<#import "parts/common.ftlh" as c>

<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новую тему
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите название темы"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="vstack gap-3">
        <#list messages as message>
            <div class="bg-light border my-3">
                <div class="m-2">
                    <span><a href="/">${message.text}</a></span>
                </div>
            </div>
        <#else>
            <br>
            Темы отсутствуют, нужно что-то добавить
        </#list>
    </div>
</@c.page>