<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/user" method="post">
        <div class="form-group">
            <label class="col-form-label">
                <h3>User name</h3>
            </label>
            <input class="form-control col-sm-3 mb-1" type="text" name="username" value="${user.username}" placeholder="Username">
        </div>
        <#list roles as role>
            <div class="form-check">
                <input class="form-check-input position-static mr-2 mb-3" type="checkbox"
                       name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-outline-success" type="submit">Save</button>
    </form>
</@c.page>