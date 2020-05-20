package main.stock;

import main.goods.Pallet;
import main.manager.StockManager;

public class Elf implements IStockWorker {

    @Override
    public String computeNextOperation(int x, int y, Pallet[] registeredPallets, Stack[] stacks, Pallet inventory, Pallet requested) {
        int yValue = 0;
        for(int i = 0; i < stacks.length; i++){
            for(int j = 0; j < 3; j++){
                if(stacks[i].stack[j] == requested){
                    yValue = i;
                }
            }
        }
        if(inventory == null){
            if(x != StockManager.WIDTH || y != yValue) {
                if (x > 10) {
                    return "W";
                } else if (x < 10) {
                    return "E";
                }
                if (y < yValue) {
                    return "S";
                } else if (y > yValue) {
                    return "N";
                }
            }else{
                return "P";
            }
        }
        if(inventory != null){
            if(inventory.equals(requested)){
                if(x != 20 || y != 0){
                    if(x < 20){
                        return "E";
                    }
                    if(y > 0){
                        return "N";
                    }
                }else{
                    return "D";
                }
            }else{
                if(x == StockManager.WIDTH && y == yValue) {
                    return "S";
                }
                else{
                    boolean firstPossible = stacks[y].stack[0] == null;
                    boolean secondPossible = stacks[y].stack[0] != null && stacks[y].stack[1] == null && inventory.getWidth() < stacks[y].stack[0].getWidth() && inventory.getDepth() <stacks[y].stack[0].getDepth();
                    boolean thirdPossible = stacks[y].stack[0] != null && stacks[y].stack[1] != null && stacks[y].stack[2] == null && inventory.getWidth() < stacks[y].stack[1].getWidth() && inventory.getDepth() <stacks[y].stack[1].getDepth();
                    if(firstPossible || secondPossible || thirdPossible){
                        return "D";
                    }else{
                        if(y < stacks.length -1) {
                            return "S";
                        }else{
                            return "B";
                        }
                    }
                }
            }
        }
        return "";
    }
}
