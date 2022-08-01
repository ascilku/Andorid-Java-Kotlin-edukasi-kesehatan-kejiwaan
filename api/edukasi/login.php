<?php

    require_once('konek.php');
    $respons = array();
        $username = $_POST['username'];
        $password = $_POST['password'];
      
        $sql = "SELECT * FROM users where username = '$username' and password = '$password'";
        $query = mysqli_fetch_array(mysqli_query($konnek,$sql));
            if(!isset($query)>0){
                
                echo json_encode(array('value'=>"0", 'pesan' =>[]));
            }else{
                
                $query = mysqli_query($konnek,$sql);
                while($row = mysqli_fetch_array($query)){
                        array_push($respons,array('id'=>$row['id'], 'username'=>$row['username'], 'status'=>$row['status']));

                }
                echo json_encode(array('value'=>"1", 'pesan' =>$respons));
            }
?>



