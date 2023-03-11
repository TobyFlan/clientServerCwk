import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;


public class Server 
{

	private boolean listening = true;
	private ServerSocket serverSocket = null;
	private AuctionProtocol aucProt = null;


	public Server(){


		try{

			//try make new socket on port 6001
			serverSocket = new ServerSocket(6001);

		}
		catch(IOException err){

			//sanity check on socket creation
			System.err.println("Error: could not listen on port: 6001.");
			System.exit(-1);

		}
		aucProt = new AuctionProtocol();

	}

	public void runServer(){

		Socket clientSocket = null;

		while(listening){

			try{
				clientSocket = serverSocket.accept();
			}
			catch(IOException e){
				System.err.println("Accept failed.\n");
				System.exit(1);
			}

			System.out.println("clientSocket port: "+clientSocket.getPort());
			try {
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
										new InputStreamReader(
											clientSocket.getInputStream()));
				String inputLine, outputLine;

				
				outputLine = aucProt.processInput(null);
				out.println(outputLine);

				while ((inputLine = in.readLine()) != null) {
					 outputLine = aucProt.processInput(inputLine);
					 out.println(outputLine);
					 if (outputLine.equals("Bye."))
						break;
				}
				out.close();
				in.close();
				clientSocket.close();
			}
			catch (IOException e) {
				System.out.println( e );
			}
		}

	}

	

	public static void main( String[] args ) throws IOException
	{

		Server s = new Server();
		s.runServer();

	}
}