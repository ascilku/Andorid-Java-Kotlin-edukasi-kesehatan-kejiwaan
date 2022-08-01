<?php

    
    require_once('konek.php');
    $username = $_POST['username'];
    $password = $_POST['password'];
    $status = $_POST['status'];

    // $waktu = date("Y-m-d h:i:sa");
    
            
          

    $sql = "INSERT INTO users (username,password,status) values ('$username','$password','$status')";
    if(mysqli_query($konnek,$sql));

                    $response["value"] = 1;
                    $response["pesan"] = "berhasil diinput";
                    echo json_encode($response);
             
        

?>