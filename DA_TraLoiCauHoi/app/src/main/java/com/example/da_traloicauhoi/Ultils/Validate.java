package com.example.da_traloicauhoi.Ultils;

public class Validate {
    public static boolean Validate_UserName(String username) {
        username.toLowerCase();
        if (username.length() < 10 || username.length() > 20) {
            return false;
        } else {
            for(int i = 0 ; i < username.length() ; i++){
                char character = username.charAt(i);
                if ((character < 'a' ||  character> 'z') && (character  < '0' || character > '9')) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean Validate_Password(String password) {
        if (password.length() < 8 && password.length() > 15)
            return false;
        int slChuHoa = 0, slChuThuong = 0, slChuSo = 0;
        for (int i = 0; i < password.length(); i++) {
            char kitu = password.charAt(i);
            if (kitu >= '0' && kitu <= '9') {
                slChuSo++;
            } else if (kitu >= 'a' && kitu <= 'z') {
                slChuThuong++;
            } else if (kitu >= 'A' && kitu <= 'Z') {
                slChuHoa++;
            }
            else
                return false;
        }
        if(slChuHoa == 0 || slChuSo == 0 || slChuThuong == 0)
            return false;
        return true;
    }
    public static boolean Validate_Email(String email) {

        String[] emails = email.split("@");
        //kiểm tra dấu @
        if (emails.length < 2)
            return false;
        //kiểm tra độ dài username
        if(emails[0].length() < 10 || emails[0].length() > 15 )
            return false;
        //kiểm tra ký tự đặc biệt
        for (int i = 0; i < emails[0].length(); i++) {
            char character = emails[0].charAt(i);
            if((character < 'a' ||  character> 'z') && (character  < '0' || character > '9') && (character < 'A' ||  character> 'Z') && character != '_')
                return false;
        }
        return true;
    }
}
