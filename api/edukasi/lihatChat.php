<?php

    require_once('konek.php');
    $respons = array();

    $id_pemilik = $_POST['id_pemilik'];

    $sql = "SELECT * FROM chat join users on users.id = chat.id_users  where chat.id_pemilik = '$id_pemilik' ORDER BY chat.id DESC LIMIT 1";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('id'=>$row['id_users'], 'username'=>$row['username'], 'status'=>$row['status'] , 'isi'=>$row['isi']));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>