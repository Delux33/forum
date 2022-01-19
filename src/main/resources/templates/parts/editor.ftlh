<#macro editor path isTopic>
    <#include "security.ftlh" />
    <#if isTopic>
        <div class="form-group mt-3">
            <form method="post" action="${path}" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="topic" class="form-control" value="<#if message??>${message.text}</#if>"
                           placeholder="Введите название темы"/>
                </div>
                <div class="form-group">
                    <input type="hidden" name="message" value="${message.id}"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </div>
            </form>
        </div>
    <#else>
        <div class="form-group mt-3">
            <form method="post" action="${path}" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="title" class="form-control" value="<#if article??>${article.title}</#if>"
                           placeholder="Введите название статьи"/>
                </div>
                <div class="form-group">
                    <input type="hidden" name="article" value="${article.id}"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </div>
            </form>
        </div>
    </#if>
</#macro>