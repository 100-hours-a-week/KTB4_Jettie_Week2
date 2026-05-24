package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static volatile boolean paymentComplete = false;    // 결제 완료 여부
    private static volatile boolean timeout = false; // 시간 제한 여부
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Clothes clothesChoice = null;

        int typeChoice = readSafeInt(
                "* 상의와 하의 중에 선택해주세요.\n 1. 상의  2. 하의", 1, 2
        );

        if (typeChoice == 1) {
            int topChoice = readSafeInt(
                    "* (상의) 어떤 옷을 커스텀하시겠습니까?\n 1. 반팔티 / 20000원  2. 니트 / 35000원  3. 셔츠 / 30000원", 1, 3
            );
            if (topChoice == 1) clothesChoice = new Tshirts();
            else if (topChoice == 2) clothesChoice = new Knit();
            else if (topChoice == 3) clothesChoice = new Shirts();

            int sleeveSelect = readSafeInt(
                    "* 소매 길이를 선택하세요.\n 1. 반팔버전  2. 긴팔버전 (+2000원)", 1, 2
            );
            if (clothesChoice instanceof Top) {
                ((Top) clothesChoice).sleeveChoice(sleeveSelect);
            }

        } else if (typeChoice == 2) {
            int bottomChoice = readSafeInt(
                    "* (하의) 어떤 옷을 커스텀하시겠습니까?\n 1. 데님 / 40000원  2. 슬랙스 / 40000원  3. 면바지 / 35000원  4. 조거팬츠 / 35000원", 1, 4
            );
            if (bottomChoice == 1) clothesChoice = new Denim();
            else if (bottomChoice == 2) clothesChoice = new Slacks();
            else if (bottomChoice == 3) clothesChoice = new CottonPants();
            else if (bottomChoice == 4) clothesChoice = new JoggerPants();

            int pantsLengthSelect = readSafeInt(
                    "* 바지 길이를 선택하세요.\n 1. 반바지버전  2. 긴바지버전 (+3000원)", 1, 2
            );
            if (clothesChoice instanceof Bottom) {
                ((Bottom) clothesChoice).pantsLengthChoice(pantsLengthSelect);
            }
        }

        int sizeSelect = readSafeInt(
                "* 사이즈를 선택하세요.\n 1. S  2. M  3. L  4. XL", 1, 4
        );
        clothesChoice.sizeChoice(sizeSelect);

        int colorSelect = readSafeInt(
                "* 색을 선택하세요\n1. 흰색  2. 검정색  3. 회색  4. 빨간색(+1000)  5. 주황색(+1000) \n6. 노란색(+1000)  7. 초록색(+1000)  8. 파란색(+1000)  9. 남색(+1000)  10. 보라색(+1000)\n", 1, 10
        );
        clothesChoice.colorChoice(colorSelect);

        String inputText;
        while (true) {
            System.out.println("* 원하는 문구를 입력하세요 (20글자 이내, 11글자부터 한 글자 당 +100원) : ");
            System.out.print(" 입력 : ");
            inputText = sc.nextLine();

            if (inputText.length() > 20) {
                System.out.println("[오류] 20글자 이내로 다시 입력해주세요.\n");
            } else {
                clothesChoice.textLength(inputText.length());
                break;
            }
        }

        System.out.println("\n* 선택하신 정보입니다.");
        System.out.println(" - " + clothesChoice.name + " (" + clothesChoice.lengthType + ") / " + clothesChoice.size + " / " + clothesChoice.color);
        System.out.println(" - 작성한 문구 : " + inputText);
        System.out.println(" => 최종 가격 : " + clothesChoice.price);
        System.out.println("\n[경고] 10초 내에 결제하지 않으면 주문이 취소됩니다.");


        Clothes finalClothesChoice = clothesChoice; // 익명 객체(스레드) 내 사용을 위한 final 복사본

        Thread timeoutThread = new Thread(() -> {
            try {
                for (int i = 10; i > 0; i--) {
                    if (paymentComplete) return;

                    System.out.println("\n** " + i + "초 남았습니다 **");
                    Thread.sleep(1000);
                }

                synchronized (lock) {
                    if (!paymentComplete) {
                        timeout = true;
                        System.out.println("\n결제 시간 초과로 주문이 취소되었습니다.");
                        System.exit(0);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        timeoutThread.start();

        // 결제
        while (true) {
            if (timeout) {
                System.exit(0);
            }

            System.out.println("* 결제하실 금액을 투입해주세요.");
            System.out.print(" 투입할 금액 : ");

            String moneyStr = sc.nextLine().trim();

            synchronized (lock) {
                if (timeout) {
                    System.out.println("\n[오류] 입력 중에 시간이 만료되었습니다.");
                    System.exit(0);
                }

                try {
                    int inputMoney = Integer.parseInt(moneyStr);

                    if (inputMoney < finalClothesChoice.price) {
                        System.out.println("[오류] 금액이 부족합니다. 다시 입력해주세요.\n");
                    } else {
                        paymentComplete = true;
                        System.out.println(" - 거스름돈 : " + (inputMoney - finalClothesChoice.price));
                        System.out.println("===== 결제가 완료되었습니다. 이용해주셔서 감사합니다. =====");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[오류] 금액을 정확하게 입력해주세요.\n");
                }
            }
        }
    }

    // 숫자, 문자 입력 부분에서 잘못된 입력 거르기
    private static int readSafeInt(String message, int min, int max) {
        while (true) {
            System.out.println(message);
            String inputStr = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(inputStr);

                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("[오류] " + min + "~" + max + " 사이의 번호를 입력해주세요.\n");

            } catch (NumberFormatException e) {
                System.out.println("[오류] 숫자가 아닌 문자가 입력되었습니다. 다시 입력해주세요.\n");
            }
        }
    }
}
