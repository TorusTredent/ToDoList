package service.Imp;

import controller.mainMenu.MainMenu;
import entity.User;
import repository.UserRepository;
import service.SingInService;
import verification.UniqVerification;
import verification.ValueVerification;

public class SingInServiceImp implements SingInService {

    ValueVerification valueVerification = new ValueVerification();
    UniqVerification uniqVerification = new UniqVerification();

    @Override
    public void singIn() {
        String login = inputLogin();
        User.setId(returnIdUser(login));
        inputPassword();
    }



    private String inputLogin() {
        if(uniqVerification.checkListLoginIsEmpty()) {
            System.out.println("Список пуст, пройдите регистрацию");
            MainMenu mainMenu = new MainMenu();
            mainMenu.menu();
        } else {
            while (true) {
                System.out.println("Введите логин: ");
                String inputLogin = valueVerification.checkString().toLowerCase();
                if (uniqVerification.checkLoginUniq(inputLogin)) {
                    System.out.println("Такого логина нет, попробуйте ввести снова");
                    System.out.println("Если хотите выйти введите 0: ");
                    escapeFromLoop();
                } else return inputLogin;
            }
        }
        return null;
    }

    private int returnIdUser(String login) {
        return UserRepository.returnIdUser(login);
    }

    private void inputPassword() {
        while(true) {
            System.out.println("Введите пароль: ");
            String inputPassword = valueVerification.checkString();
            if (uniqVerification.checkPasswordIsNotExist(inputPassword, User.getId())) {
                System.out.println("Пароль не подходит, попробуйте ввести снова");
                System.out.println("Если хотите выйти введите 0: ");
                escapeFromLoop();
            } else {
                System.out.println("Выполняется вход ...");
                break;
            }
        }
    }


    private void escapeFromLoop() {
        int choice = valueVerification.checkInt();
        if (choice == 0) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.menu();
        }
    }

}
