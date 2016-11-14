public class ResponseToClient {
    private String host;
    private int port;
    private Socket socket;
    private PrintStream theOut;
    private String resultLocation;


    public ResponseToClient(String path) {
        this.host = "jd-thesis.ecs.csun.edu";
        this.port = 8080;
        this.resultLocation = path;
    }


    public void init(){
        try{

            socket = new Socket(host, port);
            theOut = new PrintStream(socket.getOutputStream());

            sendCommand();

            socket.close();
            theOut.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void sendCommand()
    {
        theOut.println(Messages.type_response + Messages.seperator_client + resultLocation);
        System.out.println(Messages.type_response + Messages.seperator_client + resultLocation);
    }
}