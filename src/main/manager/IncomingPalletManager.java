package main.manager;

import main.goods.Pallet;

public class IncomingPalletManager {

    private Pallet[] pallets;

    IncomingPalletManager() {
        pallets = new Pallet[0];
    }

    public Pallet[] getPallets() {
        return pallets;
    }

    /**
     * Registers given pallets
     * @param pallets - pallets to register
     */
    public void registerPallets(Pallet... pallets) {
        if (pallets == null || pallets.length == 0) {
            return;
        }

        int nullcounter = 0;
        for(int i = 0; i < this.pallets.length; i++){
            if(this.pallets[i] == null){
                nullcounter++;
            }else{
                break;
            }
        }

        Pallet[] oldPallets = this.pallets;
        Pallet[] newPallets = new Pallet[oldPallets.length - nullcounter + pallets.length];

        for (int i = 0, j = nullcounter; i < newPallets.length; i++) {
            if (i < oldPallets.length - nullcounter) {
                newPallets[i] = oldPallets[j++];
            } else {
                newPallets[i] = pallets[i- (oldPallets.length - nullcounter)];
            }
        }
        this.pallets = newPallets;
        //Remove leading null values in this.pallets here
    }

    /**
     * Removes a given Pallet from registered pallets
     * @param pallet - pallet to remove
     * @return removed pallet or null (if given pallet wasn't in array)
     */
    public Pallet removePallet(Pallet pallet) {
        for(int i = 0; i < this.pallets.length; i++){
            if(this.pallets[i].equals(pallet)){
                this.pallets[i] = null;
                return pallet;
            }
        }
        return null;
    }
}