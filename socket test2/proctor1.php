<?php 
class Proctor1 {

    private $addressServer;
    private $portServer;
    private $address;
    private $port;
    private $command;

    public function __construct($port, $address, $addressServer, $portServer, $command)
    {
        set_time_limit(0);
        $this->addressServer = $addressServer;
        $this->host = $host;
        $this->portServer = $portServer;
        $this->port = $port;
        $this->command = $command;
        $this->init();
    }

    private function init() {

        $request = new Request(
            $this->addressServer, $this->portServer, $this->command
        );


        if (! $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) {
            $this->showError('socket create');
        }
        echo "Server Created\n";


        if (!socket_bind($socket, $this->host, $this->port)) {
            $this->showError('socket bind');
        }
        echo "Server bind to $this->host and $this->port \n";

        if (!socket_listen($socket)) {
            $this->showError('socket listen');
        }
        echo "Listening\n";

        do {
            $conn = socket_accept($socket);
            echo "connection success\n";

            if(!$clientMessage = socket_read($conn, 10000, PHP_NORMAL_READ)){
                $this->showError('socket read');
            }

            echo "read success\n";
            echo $clientMessage;

        } while(true);
    }

    private function showError($message){
        echo ("Error: ".$message);
        exit(666);
    }
}
?>