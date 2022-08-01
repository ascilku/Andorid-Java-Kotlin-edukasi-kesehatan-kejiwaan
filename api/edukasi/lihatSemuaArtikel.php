<?php

    require_once('konek.php');
    $respons = array();


    $sql = "SELECT * FROM artikel";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('judul'=>$row['judul'],'deksripsi'=>$row['deksripsi'], 'pengarang'=>$row['pengarang'], 'url'=>$row['urll'] ));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>