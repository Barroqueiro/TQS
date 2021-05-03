package ui;

import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;
import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

public class DemoMain {

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {
    	Logger LOGGER = Logger.getLogger( DemoMain.class.getName() );
    	Random random = new Random();

    	boolean isLogSet = random.nextBoolean();;

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        LOGGER.log( Level.FINE, "Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        LOGGER.log( Level.FINE, "You played: ");
        if (isLogSet){
        	LOGGER.log( Level.FINE, thisWeek.format());
        }

	if (isLogSet){
        	LOGGER.log( Level.FINE, "Draw results: ");
        	LOGGER.log( Level.FINE, draw.getDrawResults().format());
        }


        LOGGER.log( Level.FINE, "Your score: ");
        for (Dip dip : draw.findMatches(thisWeek)) {
            if (isLogSet){
            	LOGGER.log( Level.FINE, dip.format());
            }

        }
    }
}
