/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daolv.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public CartObject() {

    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String itemID, int quantity) {
        //1 check item exist
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        if (quantity <= 0) {
            return;
        }
        //2. check item exist
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check item existed
        if (this.items.containsKey(itemID)) {
            quantity += this.items.get(itemID);
        }//end item has already existed
        //put item to items
        this.items.put(itemID, quantity);
    }

    public void removeItemFromCart(String itemID) {
        //1 check item exist
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        //2. chesk items existed
        if (this.items == null) {
            return;
        }
        //3. check item existed
        if (this.items.containsKey(itemID)) {
            this.items.remove(itemID);
            if (this.items.isEmpty()){
                this.items = null;
            }
        }
        
    }
}
