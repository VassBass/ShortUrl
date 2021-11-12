package com.vassbassapp.ShortUrl;

import com.vassbassapp.ShortUrl.model.UrlKeeper;
import com.vassbassapp.ShortUrl.service.UrlConverter;

import java.util.List;
import java.util.Scanner;

public class TerminalController {

    public void start(){
        printMainMessage();

        Scanner consoleIn = new Scanner(System.in);
        while (true){
            String request = consoleIn.next();
            if (request.equalsIgnoreCase("close") || request.equals("quit") || request.equalsIgnoreCase("quit")){
                consoleIn.close();
                System.exit(0);
            }else if (request.equalsIgnoreCase("help")) {
                printHelpMessage();
            }else if (request.equalsIgnoreCase("all")){
                List<UrlKeeper> list = ShortUrlApplication.service.getAllUrl();
                for (UrlKeeper keeper : list){
                    System.out.println("-");
                    System.out.println("longUrl: " + keeper.getLongUrl());
                    System.out.println("shortUrl: http://localhost:8080/" + keeper.getShortUrl());
                }
            }else {
                if (UrlConverter.isUrl(request)) {
                    UrlKeeper urlKeeper = ShortUrlApplication.service.addUrl(request, 6);
                    System.out.println("Ваша короткая ссылка:");
                    System.out.println("http://localhost:8080/" + urlKeeper.getShortUrl());
                }else {
                    System.out.println("Ваш ввод не является URL-адресом");
                }
                printMainMessage();
            }
        }
    }

    private static void printHelpMessage(){
        System.out.println("Что б посмотреть все сохраненные ссылки введите \"all\"");
        System.out.println("Что б закончить работу приложения введите \"close\", \"quit\" или \"exit\"");
    }

    private static void printMainMessage(){
        System.out.println("Что б узнать все команды введите \"help\"");
        System.out.println("Что б преобразовать длинную ссылку в короткую введите её в консоль");
    }
}
