<?php

    
    require_once('konek.php');
    $id_lokasi = $_POST['id_lokasi'];
    $nama_tempat = $_POST['nama_tempat'];
    $alamat = $_POST['alamat'];
    $jam = $_POST['jam'];
    $telepon = $_POST['telepon'];

    // $waktu = date("Y-m-d h:i:sa");
    
            
          

    $sql = "INSERT INTO tempat (id_lokasi,nama_tempat,alamat, jam, telepon) values ('$id_lokasi','$nama_tempat','$alamat','$jam','$telepon')";
    if(mysqli_query($konnek,$sql));

                    $response["value"] = 1;
                    $response["pesan"] = "berhasil diinput";
                    echo json_encode($response);
             
        

?>