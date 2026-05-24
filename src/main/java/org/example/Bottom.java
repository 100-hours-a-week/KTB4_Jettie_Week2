package org.example;

public class Bottom extends Clothes {
    public void pantsLengthChoice(int choice) {
        if (choice == 1) {
            lengthType = "반바지";
        } else if (choice == 2) {
            lengthType = "긴바지";
            price += 3000;
        }
    }
}
