<#macro editor path isTopic>
    <#include "security.ftlh" />
    <#if isTopic>
        <div class="form-group <#if topicError??>show</#if> mt-3">
            <form method="post" action="${path}" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="topic" class="form-control  ${(topicError??)?string('is-invalid', '')}"
                           value="<#if text??>${text}<#else>${message.text}</#if>"
                           placeholder="Введите название темы"/>
                    <#if topicError??>
                        <div class="invalid-feedback">
                            ${topicError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Изменить</button>
                    <a href="/topics" type="submit" class="btn btn-primary ml-2">Отмена</a>
                </div>
            </form>
        </div>
    <#else>
        <div class="form-group <#if titleArticleError??>show</#if> mt-3">
            <form method="post" action="${path}" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="title" class="form-control ${(titleArticleError??)?string('is-invalid', '')}"
                           value="<#if title??>${title}<#else>${article.title}</#if>"
                           placeholder="Введите название статьи"/>
                    <#if titleArticleError??>
                        <div class="invalid-feedback">
                            ${titleArticleError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Изменить</button>
                    <a href="/topics/${message.id}" type="submit" class="btn btn-primary ml-2">Отмена</a>
                </div>
            </form>
        </div>
    </#if>
</#macro>