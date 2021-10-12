package service.Imp;

import controller.personalMenu.PersonalMenu;
import entity.User;
import repository.UserRepository;
import service.UserService;
import verification.UniqVerification;
import verification.ValueVerification;

public class UserServiceImp implements UserService {

    ValueVerification valueVerification = new ValueVerification();
    UniqVerification uniqVerification = new UniqVerification();

    @Override
    public void showLogin() {
        System.out.print("Ваш логин: ");
        UserRepository.showLogin(1);
    }

    @Override
    public void showPassword() {
        System.out.print("Ваш пароль: ");
        UserRepository.showPassword(User.getId());
    }

    @Override
    public void updateLogin() {
        String newLogin = checkNewLogin();
        UserRepository.updateLogin(newLogin, User.getId());
        System.out.println("Обновление выполнено");
    }

    @Override
    public void updatePassword() {
        String newPassword = returnNewPassword();
        UserRepository.updatePassword(newPassword, User.getId());
        System.out.println("Обновление выполнено");
    }

    private String checkNewLogin() {
        while (true) {
            System.out.println("Введите новый логин: ");
            String newLogin = valueVerification.checkString().toLowerCase();
            if (!uniqVerification.checkLoginUniq(newLogin)) {
                System.out.println("Такой логин уже используется, введите пожалуйста другой");
                System.out.println("Если хотите выйти введите 0:");
                escapeFromLoop();
            } else {
                if(newLogin.length() > 45) {
                    System.out.println("Вы превысили лимит элементов");
                    System.out.println("Введите логин меньшего размера");
                } else return newLogin;
            }
        }
    }

    private String returnNewPassword() {
        while(true) {
            System.out.println("Введите старый пароль: ");
            String oldPassword = valueVerification.checkString();
            if (uniqVerification.checkPasswordIsNotExist(oldPassword, User.getId())) {
                System.out.println("Пароль не верен, попробуйте еще раз");
                System.out.println("Если хотите выйти введите 0: ");
                escapeFromLoop();
            } else {
                while (true) {
                    System.out.println("Введите новый пароль: ");
                    String newPassword = valueVerification.checkString();
                    if (newPassword.length() > 20) {
                        System.out.println("Вы превысили лимит символов");
                    } else return newPassword;
                }
            }
        }
    }

    private void escapeFromLoop() {
        int i = valueVerification.checkInt();
        if(i == 0) {
            PersonalMenu personalMenu = new PersonalMenu();
            personalMenu.startMenu();
        }
    }
}
