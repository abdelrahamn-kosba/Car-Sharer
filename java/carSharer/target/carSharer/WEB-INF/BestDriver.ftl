<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="">
    <title>Bonus</title>
</head>
<style>
    tr,
    td {
        border: 2px solid black;
    }

    table {
        border: 3px solid black;
        margin: 5px;
    }

    table {
        display: table;
        border-collapse: separate;
        background-color: lightcyan;
        border-spacing: 50px;
    }

    body {
        background:white;
        border: 3px solid black;
        max-width: 500px;
        height: auto;
        position: relative;
        left: 600px;
    }
</style>



<body style="background-color: #20B2AA; margin-top: 70px;">


<div style="background-color: white; padding-top: 20px; text-align: center;">
    <form action='HauptSeite' onsubmit="submit()">
        <input value="Home" type="submit"
               style="background-color: black;border-color: white;color:white;text-align: center; width:100px;height:20px;margin-bottom: 20px;">
    </form>
    <h1 style="text-align: center;"><b>Offene Fahrten des "besten Fahrers"</b></h1>

    <div style="text-align: center;">

        <p style="text-align: center;">fahrer: ${benutzers!}</p>
        <p style="text-align: center;">Durchschnitterating: ${average!}</p>
        <form action="" onsubmit="submit()">
            <table>
                <#list fahrts as f>
                    <tr style="background-color: white;">

                        <td style="width:400px;height:20px; text-align: center;background-color:peachpuff">
                            <a href="FahrtDetails?fid=${f.fid}&bid=${user}"> <img src="http://localhost:9109/icons/${f.transportmittel}.png"
                                                                                  style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt="${f.transportmittel}.png"></a>
                            <p><b>Fahrt ID:</b>${f.fid  ! ''} </p>
                            <p><b>Von: </b>  ${f.startort !''}</p>
                            <p><b>Nach: </b> ${f.zielort  !''}</p>
                        </td>

                    </tr>
                </#list>
                <tr style="background-color: white;">

                </tr>
            </table>
        </form>
    </div>
</div>
</body>

</html>