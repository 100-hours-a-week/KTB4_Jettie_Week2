package org.example;

public class Clothes {
    public String name;
    public int price;
    public String size;
    public String color;
    public String lengthType; // 상의(반팔/긴팔), 하의(반바지/긴바지) 공통 상태

    // 사이즈 선택
    public void sizeChoice(int choice) {
        if (choice == 1) size = "S";
        else if (choice == 2) size = "M";
        else if (choice == 3) size = "L";
        else if (choice == 4) size = "XL";
    }

    // 컬러 선택
    public void colorChoice(int choice) {
        if (choice == 1) color = "흰색";
        else if (choice == 2) color = "검정색";
        else if (choice == 3) color = "회색";
        else if (choice == 4) color = "빨간색";
        else if (choice == 5) color = "주황색";
        else if (choice == 6) color = "노란색";
        else if (choice == 7) color = "초록색";
        else if (choice == 8) color = "파란색";
        else if (choice == 9) color = "남색";
        else if (choice == 10) color = "보라색";

        if (choice > 3) price += 1000;
    }

    // 텍스트 길이 당 추가요금 계산
    public void textLength(int length) {
        if (length > 10) {
            price += (length - 10) * 100;
        }
    }
}