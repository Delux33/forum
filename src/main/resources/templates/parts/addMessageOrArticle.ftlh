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
        <div class="collapse <#if titleError??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" action="${path}" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                               value="<#if titleError??>${article.title}</#if>" name="title"
                               placeholder="Введите название статьи"/>
                        <#if titleError??>
                            <div class="invalid-feedback">
                                ${titleError}
                            </div>
                        </#if>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    <#else>
        <div class="collapse <#if textError??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" action="${path}" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                               value="<#if textError??>${message.text}</#if>" name="text"
                               placeholder="Введите название темы"/>
                        <#if textError??>
                            <div class="invalid-feedback">
                                ${textError}
                            </div>
                        </#if>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>
</#macro>