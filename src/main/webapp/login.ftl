<html lang="ru">
<#include "base.ftl">

<#macro title>Authorization</#macro>

<#macro main__title>Authorization</#macro>

<#macro main__content>
    <p>Example login and password for the test: LOGIN = "rostik" and PASSWORD = "52"</p>
    <form action="login" method="post">
        <label>
            Login:
            <input type="text" name="login">
        </label>
        <label>
            Password:
            <input type="password" name="password">
        </label>


        <input type="submit" value="Enter">
    </form>

</#macro>

</html>