<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="functions.js"></script>
</head>
<style>
    tr,
    td {
        border: 2px solid black;
    }

    table {
        border: 3px solid #000000;
        margin: 5px;
    }

    table {
        display: table;
        border-collapse: separate;
        border-spacing: 50px;
        background-color:lightcyan;

    }


    .container {
        max-width: 500px;
        margin: auto;
        background-color: white;
        padding: 10px;
    }
</style>

<body style="background-color: #20B2AA;">
<form action='HauptSeite' onsubmit="submit()" style="text-align: center;">
    <input value="Home" type="submit"
           style="background-color: black;border-color: white;color:white;text-align: center; width:100px;height:20px;margin-bottom:0px;margin-left:10px;">
</form>
<div class="container" style="border:1px solid black;width:40%; height:auto;background-color: white;margin-top: 15px;">
    <div style="background-color:thistle; text-align:center;">
        <form action="FahrtSuche" method="post" target="_self" style="text-align:center;"><br><br>
            <label required for="start"><b>Start: </b></label>
            <input type="text" id="start" name="start" value="" onchange="start1()"
                   style="text-align:center;" required>&ensp;
            <label required for="ziel"><b>Ziel: </b></label>
            <input type="text" id="ziel" name="ziel" value="" onchange="ziel1()"
                   style="text-align:center;"required ><br><br>
            <div class="d"><label for="ab"><b>ab: </b></label>
                <input type="date" id="ab" name="ab" required >
            </div>
            <!-- die Uhrzeit und das Datum Einstellung -->
            <script>
                var date = new Date();
                var tdate = date.getDate();
                var month = date.getMonth() + 1;
                var year = date.getUTCFullYear();
                if (tdate < 10) {
                    rdate = "0" + tdate;
                }
                if (month < 10) {
                    month = "0" + month;
                }
                var mindate = year + "-" + month + "-" + tdate;
                document.getElementById("ab").setAttribute('min', mindate);
                console.log(mindate);
            </script><br>
            <form action="...." onsubmit="link()">
                <input class="a" type="submit" value="Suchen" onclick="myResults()"
                       style="Background-Color:black;Border-color:white;color:White;text-align:center;width:70px;height:30px;"><br><br>
            </form>
    </div>
    <hr>


    <div id="Ergebnis">
        <h3 style="text-align: center;"><b>Suchergebnisse</b> </h3>

        <br>${message ! ''}</br>
        <table style="background-color: thistle;">

            <#list fahrten as f  >
                <tr style="background-color: white;">
                    <td style="width:400px;height:20px;text-align: center;background-color:peachpuff;">
                        <a href='FahrtDetails?bid=${user}&fid=${f.fid}'><img src="http://localhost:9109/icons/${f.transportmittel}.png"
                                                                             style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt="${f.transportmittel}.png"></a>
                        <br><b>Von:</b> ${f.startort ! 'keine'}<br>
                        <br><b>Nach:</b> ${f.zielort ! 'keine'}<br>
                        <br><b>Kosten:</b> ${f.fahrtkosten ! 'keine'}<br>

                    </td>
                </tr>

            </#list>
        </table>
    </div>


</div>

</div>

</body>

</html>