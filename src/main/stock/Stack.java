package main.stock;

import main.goods.Pallet;
import main.manager.StockManager;

public class Stack {

    /**
     * Stack with max. 3 pallets
     */
    Pallet[] stack = new Pallet[StockManager.STACKHEIGHT];

    /**
     * Drops a pallet on the stack
     * @param pallet - pallet to drop
     * @return true if successful
     */
    public boolean add(Pallet pallet) {
        for (int i = 0; i < stack.length; i++) {
            if (stack[i] == null) {
                //There is a free slot
                if (i - 1 >= 0) {
                    //There is some other pallet below
                    Pallet prev = stack[i-1];
                    if (pallet.getWidth() < prev.getWidth() && pallet.getDepth() < prev.getDepth()) {
                        //Pallet is smaller than the pallet below, can be placed
                        stack[i] = pallet;
                        return true;
                    }
                } else {
                    //There is no other pallet in the stack, given pallet can be placed
                    stack[i] = pallet;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes top pallet from the stack
     * @return top pallet or null if the stack is empty
     */
    public Pallet remove() {
        for (int i = stack.length-1; i >=0 ; i--) {
            if (stack[i] != null) {
                Pallet p = stack[i];
                stack[i] = null;
                return p;
            }
        }
        return null;
    }

    /**
     * Returns pallet (or null) in a stack based on a given index
     * @param idx - given index
     * @return pallet or null
     */
    public Pallet get(int idx) {
        if (idx < stack.length && idx >= 0) {
            return stack[idx];
        }
        return null;
    }

    /**
     * Checks if a given pallet can be placed on the stack
     * @param pallet - given pallet
     * @return - true, if pallet can be dropped here
     */
    public boolean isPlacingPossible(Pallet pallet) {
        if (this.get(0) == null) {
            //empty stack
            return true;
        } else {
            //Check 1th level
            if (this.get(1) == null) {
                //Found an empty slot, check if pallet can be placed here
                if (this.get(0).getWidth() > pallet.getWidth()) {
                    //pallet in inventory is smaller
                    return true;
                }
            } else {
                //Check 2nd level
                if (this.get(2) == null) {
                    //Found an empty slot, check if pallet can be placed here
                    if (this.get(1).getWidth() > pallet.getWidth()) {
                        //pallet in inventory is smaller
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Pallet[] getStack() {
        return stack;
    }
}
