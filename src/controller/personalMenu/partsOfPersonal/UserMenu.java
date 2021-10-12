package controller.personalMenu.partsOfPersonal;

import controller.personalMenu.PersonalMenu;
import service.Imp.UserServiceImp;
import verification.ValueVerification;

public class UserMenu {

    ValueVerification valueVerification = new ValueVerification();
    UserServiceImp user = new UserServiceImp();

    public void menu() {
        System.out.println("1) Показать логин");
        System.out.println("2) Показать пароль");
        System.out.println("3) Изменить логин");
        System.out.println("4) Изменить пароль");
        System.out.println("5) Вернуться в меню");
        startMenu();
    }

    private void startMenu() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                user.showLogin();
                menu();
            }
            case 2: {
                user.showPassword();
                menu();
            }
            case 3: {
                user.updateLogin();
                menu();
            }
            case 4: {
                user.updatePassword();
                menu();
            }
            case 5: {
                PersonalMenu personalMenu = new PersonalMenu();
                personalMenu.startMenu();
            }
            default: {
                System.out.println("Такого пукта нет!");
                menu();
            }
        }
    }

}
