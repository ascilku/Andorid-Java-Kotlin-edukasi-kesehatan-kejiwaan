<?php

    $host = 'localhost';
    $root = 'root';
    $pass = '';
    $db   = 'edukasi';

    $konnek =  mysqli_connect($host,$root,$pass,$db);

        if(!$konnek){
            echo "gagal";
        }

?>