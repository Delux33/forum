<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container-fluid h-100 bg-light">
        <div class="row justify-content-center align-items-center">
            <h3>List of users</h3>
        </div>
        <div class="form-group">
            <table class="table table-sm">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">User</th>
                    <th scope="col">Role</th>
                    <th scope="col">Edit</th>
                </tr>
                <tbody>
                <#list users as user>
                    <tr>
                        <td><a href="/user-messages/${user.id}">${user.username}</a></td>
                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                        <td><a class="edit" href="/user/${user.id}"/></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>