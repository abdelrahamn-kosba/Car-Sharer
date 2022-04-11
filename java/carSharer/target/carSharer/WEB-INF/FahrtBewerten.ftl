<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/main.css">
</head>

<body >
<div style="background-color:gray;height:1000px;">
    <a class="HOM" href='HauptSeite'
       style="background-color:black;Border-color:white;color:White; height:15px; width:100px; padding:5px 5px; text-decoration: none;position:relative;left:850px;top:120px;"><b>Home</b></a>
    <h2 style="font-family:courier;text-align:center;border:1px solid black;width:40%;position:relative;top:110px;left:30%;background-color:peachpuff;">Fahrt Bewertung</h2>
    <div class="container2" style="border:1px solid black; width:40%;line-height:1.5;position:relative;top:100px;left:30%;background-color:peachpuff;">
        <form class="bo" action="FahrtBewerten?action=bewert" target="_self" style="text-align:center;" method="post">
            <label for="Bewertungstext"><b><br>Bewertungstext:</b></label><br><br>
            <textarea id="Beschreibung" name="Beschreibung" rows="5" cols="50"
                      style="text-align:left;" required> Die Fahrt war super! </textarea><br>
            <label for="Bewertungsrating"><b>Bewertungsrating:</b></label><br><br>
            <input type="radio" id="1" name="Bewertungsrating" value="1" required>
            <label for="1">1</label>
            <input type="radio" id="2" name="Bewertungsrating" value="2" required>
            <label for="2">2</label>
            <input type="radio" id="3" name="Bewertungsrating" value="3" required >
            <label for="3">3</label><br><br>
            <input type="radio" id="4" name="Bewertungsrating" value="4" required>
            <label for="4">4</label>
            <input type="radio" id="5" name="Bewertungsrating" value="5" required>
            <label for="5">5</label><br><br>
            <input type="submit" value="Bewerten"
                   style="Background-Color:black;Border-color:white;color:White;text-align:center;width:100px;height:25px;">

        </form>
        <br><br>
        <div style="text-align: center">${message ! ''}</div>

    </div>
</div>

</body>

</html>