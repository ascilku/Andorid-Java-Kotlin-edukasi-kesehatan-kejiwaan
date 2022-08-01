<?php

    
    require_once('konek.php');
    $id_users = $_POST['id_users'];
    $id_pemilik = $_POST['id_pemilik'];
    $isi = $_POST['isi'];
    $tgl = date('Y-m-d');

    // $waktu = date("Y-m-d h:i:sa");
    
            
          

    $sql = "INSERT INTO chat (id_users, id_pemilik ,isi,tgl) values ('$id_users','$id_pemilik','$isi','$tgl')";
    if(mysqli_query($konnek,$sql));

                    $response["value"] = 1;
                    $response["pesan"] = "berhasil diinput";
                    echo json_encode($response);
             
        

?>