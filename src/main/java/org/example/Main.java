package org.example;

import java.util.Scanner;

class Clothes {
    String name;
    int price;
    String size;
    String color;

    // 사이즈 선택
    void sizeChoice(int choice) {
        if (choice == 1) size = "S";
        else if (choice == 2) size = "M";
        else if (choice == 3) size = "L";
        else if (choice == 4) size = "XL";
    }

    // 컬러 선택
    void colorChoice(int choice) {
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
        
    //텍스트 길이 당 추가요금 계산
    void textLength(int length) {
        if (length > 10) {
            price += (length-10)*100;
        }
    }
    }


 class Top extends Clothes {}
class Bottom extends Clothes {}

class Tshirts extends Top {
    Tshirts() {
        name = "반팔티";
        price = 20000;
    }
}
class Sweatshirts extends Top {
    Sweatshirts() {
        name = "맨투맨";
        price = 25000;
    }
}

class Hoodie extends Top {
    Hoodie() {
        name = "후드티";
        price = 30000;
    }
}

class ZipUp extends Top {
    ZipUp() {
        name = "후드집업";
        price = 35000;
    }
}
class Denim extends Bottom {
    Denim() {
        name = "데님";
        price = 40000;
    }
}

class Slacks extends Bottom {
    Slacks() {
        name = "슬랙스";
        price = 40000;
    }
}

class CottonPants extends Bottom {
    CottonPants() {
        name = "면바지";
        price = 35000;
    }
}

class JoggerPants extends Bottom {
    JoggerPants() {
        name = "조거팬츠";
        price = 35000;
    }
}
//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Clothes clothesChoice = null;

        System.out.println("* 상의와 하의 중에 선택해주세요.");
        System.out.println(" 1. 상의");
        System.out.println(" 2. 하의");
        int typeChoice = sc.nextInt();

        if (typeChoice == 1) {
            System.out.println("* (상의) 어떤 옷을 커스텀하시겠습니까?");
            System.out.println(" 1. 반팔티 / 20000원");
            System.out.println(" 2. 맨투맨 / 25000원");
            System.out.println(" 3. 후드티 / 30000원");
            System.out.println(" 4. 후드집업 / 35000원");

            int topChoice = sc.nextInt();

            if (topChoice == 1) clothesChoice = new Tshirts();
            else if (topChoice == 2) clothesChoice = new Sweatshirts();
            else if (topChoice == 3) clothesChoice = new Hoodie();
            else if (topChoice == 4) clothesChoice = new ZipUp();
        }
        else if (typeChoice == 2) {
            System.out.println("* (하의) 어떤 옷을 커스텀하시겠습니까?");
            System.out.println(" 1. 데님 / 40000원");
            System.out.println(" 2. 슬랙스 / 40000원");
            System.out.println(" 3. 면바지 / 35000원");
            System.out.println(" 4. 조거팬츠 / 35000원");

            int bottomChoice = sc.nextInt();

            if (bottomChoice == 1) clothesChoice = new Denim();
            else if (bottomChoice == 2) clothesChoice = new Slacks();
            else if (bottomChoice == 3) clothesChoice = new CottonPants();
            else if (bottomChoice == 4) clothesChoice = new JoggerPants();
            }

        System.out.println("* 사이즈를 선택하세요.");
        System.out.println(" 1. S");
        System.out.println(" 2. M");
        System.out.println(" 3. L");
        System.out.println(" 4. XL");
        int sizeSelect = sc.nextInt();
        clothesChoice.sizeChoice(sizeSelect);

        System.out.println("* 색을 선택하세요");
        System.out.println("1. 흰색");
        System.out.println("2. 검정색");
        System.out.println("3. 회색");
        System.out.println("4. 빨간색 (+1000)");
        System.out.println("5. 주황색 (+1000)");
        System.out.println("6. 노란색 (+1000)");
        System.out.println("7. 초록색 (+1000)");
        System.out.println("8. 파란색 (+1000)");
        System.out.println("9. 남색 (+1000)");
        System.out.println("10. 보라색 (+1000)");

        int colorSelect = sc.nextInt();
        clothesChoice.colorChoice(colorSelect);


        sc.nextLine();
        String inputText;
        while (true) {
            System.out.println("* 원하는 문구를 입력하세요 (20글자 이내, 11글자부터 한 글자 당 +100원) : ");
            inputText = sc.nextLine();

            if (inputText.length() > 20) {
                System.out.println("20글자 이내로 다시 입력해주세요.");
            } else {
                clothesChoice.textLength(inputText.length());
                break;
            }
        }

        System.out.println("* 선택하신 정보입니다.");
        System.out.println(" - " + clothesChoice.name + " / " + clothesChoice.size + " / " + clothesChoice.color);
        System.out.println(" - 작성한 문구 : " + inputText);
        System.out.println(" => 최종 가격 : " + clothesChoice.price);

        while (true) {
            System.out.println("* 결제하실 금액을 투입해주세요.");
            System.out.println(" - 투입할 금액 : ");
            int inputMoney = sc.nextInt();

            if (inputMoney < clothesChoice.price) {
                System.out.println("금액이 부족합니다. 다시 입력해주세요.");
            } else {
                System.out.println(" - 거스름돈 : " + (inputMoney - clothesChoice.price));
                System.out.println("===== 이용해주셔서 감사합니다. =====");
                break;
            }
        }

    }
}