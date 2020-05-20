package main.gui;

import main.goods.Pallet;
import main.manager.IncomingPalletManager;
import main.manager.StockManager;
import main.stock.Elf;
import main.stock.Stack;

import java.util.ArrayList;

public class ElfController extends Helper {

    /**
     * Elf
     */
    Elf elf = new Elf();

    /**
     * List of all ElfControllers
     */
    static ArrayList<ElfController> elfList = new ArrayList<>();

    /**
     * score
     */
    static int score = 0;

    public ElfController(IncomingPalletManager incomingPalletManager, Stack[] stacks) {
        x = StockManager.WIDTH * 2;
        this.incomingPalletManager = incomingPalletManager;
        this.stacks = stacks;
        elfList.add(this);
    }

    public void performElfOperation() {
        Pallet[] registeredPallets = incomingPalletManager.getPallets();
        assignRequestedPallet();

        String cmd = elf.computeNextOperation(x, y, registeredPallets, stacks, inventory, requestedPallet);
        if (cmd == null || cmd.length() == 0) {
            return;
        }
        System.out.println("Elf Command: " + cmd);
        switch (cmd.charAt(0)) {
            case 'N':
                if (y > 0) {
                    --y;
                }
                return;
            case 'S':
                if (y < stacks.length -1) {
                    ++y;
                }
                return;
            case 'E':
                if (x < 2* StockManager.WIDTH) {
                    ++x;
                }
                return;
            case 'W':
                if (x > StockManager.WIDTH) {
                    --x;
                }
                return;
            case 'P': //Pick
                if (x == StockManager.WIDTH && y >= 0 && y < stacks.length) {
                    if (stacks[y].get(0) != null) {
                        inventory = stacks[y].remove();
                    }
                }
                return;
            case 'D': //Drop
                if (x == 2 * StockManager.WIDTH && y == 0) {
                    if (inventory != null && inventory.equals(requestedPallet)){
                        inventory = null;
                        requestedPallet = null;
                        score++;
                        assignRequestedPallet();
                    }
                }
                if (x == StockManager.WIDTH) {
                    if (stacks[y].add(inventory)) {
                        inventory = null;
                    }
                }
                return;
            case 'B': //Beginning
                if(y == stacks.length - 1){
                    for(int i = 0; i < stacks.length -1; i++){
                        --y;
                    }
                }
                return;
            default:
                return;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void performElfOperationForEveryElf() {
        for (ElfController ec : elfList) {
            ec.performElfOperation();
        }
    }

    public static ArrayList<ElfController> getElfList() {
        return elfList;
    }

    public static int getScore() {
        return score;
    }

    public Pallet getInventory() {
        return inventory;
    }

    private void assignRequestedPallet() {
        if (requestedPallet != null || stacks == null) {
            return;
        }
        ArrayList<Pallet> available = new ArrayList<>();
        for (int i = 0; i < stacks.length; i++) {
            for (int j = 0; j < StockManager.STACKHEIGHT; j++) {
                if (stacks[i].get(j) != null) {
                    available.add(stacks[i].get(j));
                }
            }
        }

        if (available.size() == 0) {
            Pallet[] registered = incomingPalletManager.getPallets();
            int length = registered.length;
            if (length > 20) length = 20;
            for (int i = 0; i < length; i++) {
                if (registered[i] != null) {
                    available.add(registered[i]);
                }
            }
        }

        if (available.size() > 0) {
            int idx = r.nextInt(available.size());
            requestedPallet = available.get(idx);
        }
    }
}
