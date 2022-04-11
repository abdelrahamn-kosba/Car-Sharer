<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/main.css">
    <script src="../../../../../../../../../DB/Front%20end/functions.js"></script>
</head>

<body   style=" text-align-last:center;position:relative;top:50px;line-height:1.5;right:300px;background-color:#20B2AA; ">

<a class="HOM" href='HauptSeite'
   style="background-color:black;Border-color:white;color:White; height:15px; width:100px; padding:5px 5px; text-decoration: none;position:relative;left:350px;top:20px;"><b>Home</b></a>
<h2 style="font-family:courier;border:1px solid black;width:40%; background-color:peachpuff;margin-left: 50%; margin-bottom:5px;position:relative;top:10px ">Fahrt erstellen </h2>

<div class="container2" style="border:1px solid black;background-color:peachpuff; width:40%;margin-left: 50%;margin-top: 20px;">
    <form action="FahrtErstellen?action=FahrtErstellen" target="_self" method="post" onsubmit="submit()">
        <label for="von" required> <b><br>Von:</b></label>
        <input type="text" id="von" name="von" placeholder="Stadt" style="text-align:center;" required> <br><br>
        <label for="nach" required > <b>Bis:</b> </label>
        <input type="text" id="nach" name="nach" placeholder="Stadt" style="text-align:center;" required><br><br>
        <span class="x"><label for="kapazität"  style="position:relative; right: 110px;" required> <b>Maximale kapazität:</b></label></span>
        <span class="z"><input type="number" id="kapazität" name="kapazität" min="1" max="10" placeholder="no" style="text-align:center;position:relative; right:60px;" required></span>
        <br><br>
        <span class="y"><label for="kosten" style="position:relative;right:20px;" required> <b>Fahrtkosten:</b> </label></span>
        <span class="f"><input type="number" id="kosten" name="kosten" min="0" placeholder="no" style="text-align:center;position:relative;right:20px;" required>
            &euro; </span><br><br>

        <span class="e"> <label for="Transportmittel" required> <b>Transportmittel:</b> </label></span>
        <input type="radio" id="1" name="Transportmittel" value="1" required>
        <label for="Auto">Auto</label>
        <input type="radio" id="2" name="Transportmittel" value="2" required>
        <label for="bus">Bus</label>
        <input type="radio" id="3" name="Transportmittel" value="3" required>
        <label for="kleintransporter">kleintransporter</label><br><br>

        <span class="g"><label for="date"> <b>Fahrtdatum: </b></label></span>
        <span class="s"><input type="date" id="demo" name="date"  required ></span>
        <script>
            <!-- Datum und Uhrzeit Einstellung-->
            var date = new Date();
            var tdate = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getUTCFullYear();
            if (tdate < 10) {
                tdate = '0' + tdate;
            }
            if (month < 10) {
                month = '0' + month;
            }
            var mindate = year + '-' + month + '-' + tdate;
            document.getElementById("demo").setAttribute('min',mindate);
            console.log(mindate);
        </script>

        <label for="time"> &nbsp;</label>
        <input type="time" id="time" name="time" value="12.00" min="00.00" max="24.00"><br><br>
        <span class="w" required><label for="Beschreibung"><b>Beschreibung:</b> </label></span><br>
        <textarea required class="q" id="Beschreibung" name="Beschreibung" rows="5" cols="30"
                  maxlength="50"> </textarea><br>
        <div style="margin-top: 0px;font-size: 14px;text-align: center;" id="nom"> Anzahl
            Zeichen verbleibt :50</div><br>
        <input class="o" type="submit" value="Erstellen" style="Background-Color:black
        ;Border-color:white;color:White;height:30px;margin-bottom:10px; "><br><br>


    </form>

    ${message ! ''}
</div>

<script>
    const noTextarea = document.getElementById('Beschreibung');
    const remain = document.getElementById('nom');
    const max = 50;
    noTextarea.addEventListener('input', () => {
        const remaining = max - noTextarea.value.length;
        const color = remaining < max * 0.1 ? 'red' : null;
        remain.textContent =  "Anzahl Zeichen verbleibt:"+ remaining ;
        remain.style.color = color;
    });


</script>
</bod>

</html>