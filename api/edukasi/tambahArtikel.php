<?php

    
    require_once('konek.php');
    $judul = $_POST['judul'];
    $deksripsi = $_POST['deksripsi'];
    $pengarang = $_POST['pengarang'];
    $urll = $_POST['url'];

    // $waktu = date("Y-m-d h:i:sa");
    
            
          

    $sql = "INSERT INTO artikel (judul,deksripsi,pengarang, urll) values ('$judul','$deksripsi','$pengarang','$urll')";
    if(mysqli_query($konnek,$sql));

                    $response["value"] = 1;
                    $response["pesan"] = "berhasil diinput";
                    echo json_encode($response);
             
        

?>