import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.*;


public class Client extends Thread
{

	private Socket aucSocket = null;
	private PrintWriter socketOutput = null;
	private BufferedReader socketInput = null;

	public void run(String[] args){

		try{

			//try connect through socket port 6001
			aucSocket = new Socket("localhost", 6001);

			//chain a writing stream
			socketOutput = new PrintWriter(aucSocket.getOutputStream(), true);

			//chain a reading stream
			socketInput = new BufferedReader(new InputStreamReader(aucSocket.getInputStream()));


		}
		//else cant connect to host at port 6001
		catch(UnknownHostException e){

			System.err.println("Don't know about host.\n");
			System.exit(-1);

		}
		//else cant create wirter/reader
		catch(IOException e){

			System.err.println("Couldn't get I/O for the connection to host.\n");
			System.exit(-1);

		}

		//chain reader from keyboard
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		String fromServer;
		String fromUser;


		try{
			while((fromServer=socketInput.readLine())!=null){

				//read from server
				System.out.println( "Server: " + fromServer);
				if (fromServer.equals("Adios.")){
					break;
				}

				//send data to server
				fromUser = args[0];
				if(fromUser!=null){

					System.out.println("Client: " + fromUser);
					socketOutput.println(fromUser);

				}



			}

			socketOutput.close();
			socketInput.close();
			stdIn.close();
			aucSocket.close();
		}
		catch(IOException e){

			System.err.println("I/O exception during execution");
			System.exit(-1);

		}

	}

	public static void main( String[] args )
	{

			Client c = new Client();
			c.run(args);

	}
}