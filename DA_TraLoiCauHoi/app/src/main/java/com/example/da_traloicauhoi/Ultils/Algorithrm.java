package com.example.da_traloicauhoi.Ultils;

import java.util.Stack;

public class Algorithrm {

    public static String ConvertToFormatMoney(String money) {
        Stack<Character> stackMoney = new Stack<>();
        for (int i = money.length() - 1, j = 0; i >= 0; i--, j++) {
            if (j == 3) {
                stackMoney.push('.');
                j=0;
            }
            stackMoney.push(money.charAt(i));
        }

        String newMoney = "";
        while (!stackMoney.empty()) {
            newMoney = newMoney + stackMoney.pop();
        }
        return newMoney;
    }
}
