<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>HauptSeite</title>
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
            border-spacing: 50px;
            background-color: lightCyan;

        }
        body{
            position:relative;
            left:700px;
            background-color:#20B1AA;
        }



        .button {
            display: flex;
            /* or display:grid */
            justify-content: center;
        }
    </style>

</head>


<body style="width:500px;height:500px; align-content: center; background-color:#20B2AA;">

<span style="border-color: black;text-align: center;">
        <div style="width:400px; height:20px; margin-left: 50px;">
            <a href="FahrtSuche"
               style="border-color:white;background-color: black;color:white;text-decoration: none;width:100px  ;height:15px;padding: 5px 5px;"><b>fahrt
                    suchen</b></a>&ensp;
            <a href="BestDriver"
               style="border-color:white;background-color: black;color:white;text-decoration: none; width:100px ;height:15px; padding: 5px 5px; "><b>Beste
                    Bewertung</b></a>
        </div>

    </span>

<h1 style="font-family: courier; text-align: center; border:1px solid black; background-color: gray ;color:white;position:relative;top:10px;">
    CarSharer</h1>
<h2 style="font-family: courier;text-align: center; border:1px solid  black;background-color:gray;color:white;margin-bottom:0px;">Meine
    reservierte Fahrten</h2>

<br> ${message ! ''}<br><br><br><br>
<form action="onsubmit="submit()" style="position:relative;bottom:30px;" >
<table align="center">

    <#list ReservFahrten as fahrts>

        <tr style="background-color: white;">

            <td style="width:400px;height:20px; text-align: center;background-color:peachpuff;">
                <a  href="FahrtDetails?fid=${fahrts.fid}&bid=${benutzer}"> <img src="http://localhost:9109/icons/${fahrts.transportmittel}.png"
                                                                                style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt=""></a>

                <p><b>Von:</b> ${fahrts.startort}</p>
                <p><b>Nach: </b>${fahrts.zielort}</p>
                <p><b>status: </b>${fahrts.status}</p>
            </td>

        </tr>

    </#list>

</table>
<div>
    <h2
            style="font-family: courier;text-align: center;border:1px solid black; background-color: gray ;color:white;">
        offene fahrten</h2>
</div>
<table>
    <#list offeneFahrten as Offenfahrt>
        <tr style="background-color: white;">

            <td style="width:400px;height:20px;text-align: center;background-color:peachpuff;">



                <a  href="FahrtDetails?fid=${Offenfahrt.fid}&bid=${benutzer}"> <img src="http://localhost:9109/icons/${Offenfahrt.transportmittel}.png"
                                                                                    style="width:60px; height:30px;border:1px solid black; margin-top: 10px;" alt=""></a>

                <p><b>Von: ${Offenfahrt.startort }</b></p>
                <p><b>Nach: ${Offenfahrt.zielort}</b></p>
                <p><b>Freie Plätze: ${Offenfahrt.freierPlaetze}</b></p>
                <p><b>fahrtkosten: ${Offenfahrt.fahrtkosten} &euro;</b></p>

            </td>

        </tr>
    </#list>



    </form>
</table>
<form class="button" action="FahrtErstellen" style=" onsubmit:="submit()">
<input type="submit" value="fahrt"
       style="text-align:center; background-color:black; border-color: white;color:whitesmoke; width:100px;height:30px;">
</form>

</div>
</body>


</html>