<?php

    require_once('konek.php');
    $respons = array();

    $id_users = $_POST['id_users'];

    $sql = "SELECT * FROM chat join users on users.id = chat.id_pemilik  where chat.id_users = '$id_users' or chat.id_pemilik = '$id_users' ORDER BY chat.id DESC";

    $query = mysqli_query($konnek,$sql);
        while($row = mysqli_fetch_array($query)){
                array_push($respons,array('id'=>$row['id_users'], 'username'=>$row['username'], 'status'=>$row['status'] , 'isi'=>$row['isi']));

        }
        echo json_encode(array('value'=>"1", 'pesan' =>$respons));

?>