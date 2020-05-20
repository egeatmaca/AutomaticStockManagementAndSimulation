package main.stock;

import main.goods.Pallet;

public interface IStockWorker {

    /**
     * Computes and returns next operation of worker based on a given situation.
     * @param x - position of worker
     * @param y - position of worker
     * @param registeredPallets - pallets on conveyor
     * @param stacks - current stacks
     * @param inventory - current inventory or null if empty
     * @param requested - requested pallet
     * @return commands: "N" (orth), "S" (outh), "E" (ast), "W" (est), "P" (ick), "D" (rop), "" (no action)
     */
    public String computeNextOperation(int x, int y, Pallet[] registeredPallets, Stack[] stacks, Pallet inventory, Pallet requested);
}
