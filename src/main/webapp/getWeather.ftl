<html lang="ru">
<#include "base.ftl">

<#macro title>Getting weather</#macro>

<#macro main__title>Getting weather</#macro>

<#macro main__content>
    <p>Example city: </p>
    <form action="getWeather" method="post">
        <label>
            <div>Сity:</div>
            <input type="text" name="town">
        </label>


        <input type="submit" value="Get weather">
    </form>

</#macro>

</html>