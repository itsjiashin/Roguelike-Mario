package game;

/**
 * A Wallet class that records the player's money/credit
 */
public class Wallet {

    /**
     * Declaring an int attribute for wallet to store the credit.
     */
    private int amount;

    /**
     * A singleton Wallet instance
     */
    private static Wallet instance;

    /**
     * Constructor to initialise wallet to have 0 credit.
     */
    private Wallet(){
        this.amount = 0;
    }

    /**
     * Get the singleton instance of Wallet
     * @return Singleton instance of Wallet
     */
    public static Wallet getInstance(){
        if(instance == null){
            instance = new Wallet();
        }
        return instance;
    }

    /**
     * Add credit into wallet
     * @param value Coin's value
     */
    public void addCoin(int value){
        amount += value;
    }

    /**
     * Deduct credit from wallet
     * @param value Amount to deduct
     */
    public void deductFromBalance(int value){
        amount -= value;
    }

    /**
     * Getter for wallet
     * @return Wallet's credit
     */
    public int getAmount() {
        return amount;
    }

    /**
     * To String method for wallet to show in menu
     * @return String indicating the wallet's credit
     */
    public String printWallet(){
        return "Wallet: $" + amount;
    }



}
