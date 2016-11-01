<html>
test
</html>
<?php 
//$host = "127.0.0.1";
//$port = 80;
// No Timeout 
set_time_limit(0);
ob_implicit_flush ();
//$socket = socket_create(AF_INET, SOCK_STREAM, 0) or die("Could not create socket\n");
//$bind= socket_bind($socket, $host, $port) or die("Could not bind to socket\n");

//$result = socket_listen($socket) or die("Could not set up socket listener\n")

//$spawn = socket_accept($socket) or die("Could not accept incoming connection\n");
//$input = socket_read($spawn, 1024) or die("Could not read input\n");
//$output = "testtesttest\n";
//socket_write($spawn, $output, strlen ($output)) or die("Could not write output\n"); 
//socket_close($spawn);
//socket_close($socket);

$tcp = getprotobyname("tcp");  

$socket = socket_create(AF_INET, SOCK_STREAM, $tcp)or die("Could not create socket\n"); 

socket_bind($socket, '130.166.68.119', 80)or die("Could not bind to socket\n");    
   
socket_listen($socket)or die("Could not set up socket listener\n");     
  

$buffer = "connect"; 
while (true) { 

    $connection = socket_accept($socket)or die("Could not accept incoming connection\n"); 
    if(!$connection){ 
        echo "connect faild"; 
    }else{ 
            echo "Socket connected\n"; 

            if ($buffer != "") { 
                echo "send data to client\n"; 
                socket_write($connection, $buffer . "\n"); 
                echo "Wrote to socket\n"; 
            } else { 
                echo "no data in the buffer\n" ; 
            } 

            while ($data = @socket_read($connection, 1024, PHP_NORMAL_READ)) { 
                    printf("Buffer: " . $data . "\n"); 
                    socket_write($connection, "success"); 
            } 
    } 
 
    socket_close($connection); 
    printf("Closed the socket\n"); 
} 
?>