<?php

    
    require_once('konek.php');
    $lokasi = $_POST['lokasi'];

    // $waktu = date("Y-m-d h:i:sa");
    
            
          

    $sql = "INSERT INTO lokasi (lokasi) values ('$lokasi')";
    if(mysqli_query($konnek,$sql));

                    $response["value"] = 1;
                    $response["pesan"] = "berhasil diinput";
                    echo json_encode($response);
             
        

?>