package main.stock;

import main.goods.Pallet;
import main.manager.StockManager;

public class Wichtel implements IStockWorker {

    @Override
    public String computeNextOperation(int x, int y, Pallet[] registeredPallets, Stack[] stacks, Pallet inventory, Pallet requested) {
        if(inventory == null){
            if(x == 0 && y == 0){
                return "P";
            }else{
                if(x > 0){
                    return "W";
                }
                if(y > 0){
                    return "N";
                }
            }
        }else{
            if(x != StockManager.WIDTH){
                return "E";
            }else{
                boolean firstPossible = stacks[y].stack[0] == null;
                boolean secondPossible = stacks[y].stack[0] != null && stacks[y].stack[1] == null && inventory.getWidth() < stacks[y].stack[0].getWidth() && inventory.getDepth() <stacks[y].stack[0].getDepth();
                boolean thirdPossible = stacks[y].stack[0] != null && stacks[y].stack[1] != null && stacks[y].stack[2] == null && inventory.getWidth() < stacks[y].stack[1].getWidth() && inventory.getDepth() <stacks[y].stack[1].getDepth();
                if(firstPossible || secondPossible || thirdPossible){
                    return "D";
                }else{
                    if(y < stacks.length - 1) {
                        return "S";
                    }else{
                        return "B";
                    }
                }
            }
        }
    return "";
    }
}
