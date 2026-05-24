package org.example;

public class Top extends Clothes {
    // 상의만의 독자적인 기능: 소매 길이 선택
    public void sleeveChoice(int choice) {
        if (choice == 1) {
            lengthType = "반팔";
            // 기본 가격 유지 혹은 변경 없음
        } else if (choice == 2) {
            lengthType = "긴팔";
            price += 2000; // 긴팔은 2000원 추가 예시
        }
    }
}
