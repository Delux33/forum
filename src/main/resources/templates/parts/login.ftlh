<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row" >
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="User name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-5">
                    <input type="email" name="email" class="form-control" placeholder="1@2.3" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Phone number:</label>
                <div class="col-sm-5">
                    <input type="text" name="phoneNumber" class="form-control" placeholder="8(XXX)XXX-XX-XX"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">
            <#if isRegisterForm>
                Create
            <#else>
                Sign In
            </#if>
        </button>
        <#if !isRegisterForm>
            <a class="ml-3" href="/registration">Add new user</a>
        <#else>
            <small class="text-muted">
                <label class="col-form-label ml-1">Уже есть аккаунт?</label>
                <a class="text" href="/login"><span><b>Log In</b></span></a>
            </small>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>