<?php

    require_once('konek.php');
    $respons = array();

    // $id_lokasi = $_POST['id_lokasi'];

    $sql = "SELECT * FROM users";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('id'=>$row['id'], 'username'=>$row['username'], 'status'=>$row['status']));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>