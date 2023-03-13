
public class AuctionProtocol{

    private static final int WAITING = 0;
    private static final int SENTWELCOME = 1;
    private static final int CLOSING = 2;

    private int state = WAITING;

    public String processInput(String theInput){

        String theOutput = null;

        if(state == WAITING){

            theOutput = "Welcome to the Auctioning System on port 6001!" +
            " Would you like to:"+
            " 1. Bid"+
            " 2. Auction Item"+
            " 3. List Items";

            state = SENTWELCOME;
        
        }
        else if(state == SENTWELCOME){
            if(theInput.equalsIgnoreCase("bid")){
                theOutput = "BIDDING!";
                state = CLOSING;
            }
            else if(theInput.equalsIgnoreCase("Auction")){
                theOutput = "AUCTIONING!";
                state = CLOSING;
            }
            else if(theInput.equalsIgnoreCase("List")){
                theOutput = "LISTING!";
                state = CLOSING;
            }
            else{
                theOutput = "unknown input, try again";
                state = WAITING;
            }
        }
        else if(state == CLOSING){

            theOutput = "Bye.";
            state = WAITING;

        }

    return theOutput;

    }

}