<?php

    require_once('konek.php');
    $respons = array();


    $sql = "SELECT * FROM lokasi";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('id'=>$row['id'], 'lokasi'=>$row['lokasi'] ));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>