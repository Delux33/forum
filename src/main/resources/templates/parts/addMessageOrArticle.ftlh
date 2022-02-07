<#macro addTitle path isTopic>
    <#include "security.ftlh" />
    <#if !isTopic>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Добавить название статьи
        </a>
    <#else>
        <#if isAdmin || isModerator>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
               aria-expanded="false" aria-controls="collapseExample">
                Добавить тему
            </a>
        </#if>
    </#if>
    <#if !isTopic>
        <div class="collapse <#if titleArticleError??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" action="${path}" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(titleArticleError??)?string('is-invalid', '')}"
                               value="<#if title??>${title}</#if>" name="title"
                               placeholder="Введите название статьи"/>
                        <#if titleArticleError??>
                            <div class="invalid-feedback">
                                ${titleArticleError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    <#else>
        <div class="collapse <#if topicError??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" action="${path}" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(topicError??)?string('is-invalid', '')}"
                               value="<#if text??>${text}</#if>" name="text"
                               placeholder="Введите название темы"/>
                        <#if topicError??>
                            <div class="invalid-feedback">
                                ${topicError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>
</#macro>