package floodit.s4928803.floodit;

public class Game extends AbstractGame {

    static int GRID_SIZE = 72;
    private int round = 0;
    static int maxRounds = 35;
    private int newColour;


    Game(int width, int height) {
        super(width, height);

        for (int i = 0; i < mData.length; i++) {
            for (int j = 0; j < mData[i].length; j++) {
                mData[i][j] = rand.nextInt(colCode.length);
            }
        }
    }

    /*
    This method is called by the SettingsActivity class to set different round counters (difficulties)
     */
    void setRound(int maxRound) {
        maxRounds = maxRound;
    }


    @Override
    protected void setColor(int x, int y, int colour) {
        mData[x][y] = colour;
    }

    @Override
    public int getColor(int x, int y) {
        return mData[x][y];
    }

    /*
    Checks the most recently clicked colour against the entire array for similarity and then compares against rounds to define a win
     */
    @Override
    public boolean isWon() {
        for (int[] aMData : mData) {
            for (int j = 1; j < aMData.length; j++) {
                if (newColour != aMData[j]) {
                    return false;
                }
            }
        }
        if (round > maxRounds) {
            return false;
        }
        if (round < maxRounds) {
            System.out.println("You have won at round " + round);
        }
        return true;
    }

    /*
    Increments the round counter and prevents a round being registered if a flood did not occur
     */
    void playColour(int passedInColour) {
        int previousColour = mData[0][0];
        if (passedInColour != previousColour) {
            round++;
            floodAlgorithm(0, 0, passedInColour, previousColour);
        }
    }

    /*
    Floods to adjacent colours of the corresponding part of that array
     */
    private void floodAlgorithm(int x, int y, int newColour, int previousColour) {

        this.newColour = newColour;

        if (x >= mData.length) return;
        if (y >= mData[x].length) return;

        int colour = mData[x][y];

        if (colour != previousColour) return;
        if (colour == newColour) return;
        setColor(x, y, newColour);

        floodAlgorithm(x + 1, y, newColour, previousColour);
        floodAlgorithm(x, y + 1, newColour, previousColour);

        if (x > 0) {
            floodAlgorithm(x - 1, y, newColour, previousColour);
        }
        if (y > 0) {
            floodAlgorithm(x, y - 1, newColour, previousColour);
        }
    }

    @Override
    public int getRound() {
        System.out.println(round);
        return round;
    }
}
