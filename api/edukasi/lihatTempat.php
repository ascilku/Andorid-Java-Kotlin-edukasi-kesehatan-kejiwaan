<?php

    require_once('konek.php');
    $respons = array();

    $id_lokasi = $_POST['id_lokasi'];

    $sql = "SELECT * FROM tempat where id_lokasi = '$id_lokasi'";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('nama_tempat'=>$row['nama_tempat'], 'alamat'=>$row['alamat'] , 'jam'=>$row['jam'], 'telepon'=>$row['telepon'] ));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>