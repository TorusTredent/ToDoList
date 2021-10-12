package service.Imp;

import controller.mainMenu.MainMenu;
import entity.User;
import repository.UserRepository;
import service.RegistrationService;
import verification.UniqVerification;
import verification.ValueVerification;

public class RegistrationServiceImp implements RegistrationService {

    ValueVerification valueVerification = new ValueVerification();
    UniqVerification uniqVerification = new UniqVerification();

    @Override
    public void createUser() {
        String inputLogin = inputLogin();
        String inputPassword = inputPassword(inputLogin);
        User user = new User(inputLogin, inputPassword);
        UserRepository.createUser(user);
        System.out.println("Регистрация выполнена");
    }

    private String inputLogin() {
        while (true) {
            System.out.println("Введите логин:");
            String inputLogin = valueVerification.checkString().toLowerCase();
            if (!uniqVerification.checkLoginUniq(inputLogin)){
                System.out.println("Такой логин уже существует, введите другой логин");
                System.out.println("Если хотите выйти введите 0: ");
                escapeFromLoop();
            } else {
                if (inputLogin.length() > 45) {
                    System.out.println("Размер логина превысил максимальное количество символов, введите другой логин");
                } else return inputLogin;
            }
        }
    }


    private String inputPassword(String login) {
        while (true) {
            System.out.println("Введите пароль:");
            String inputPassword = valueVerification.checkString().toLowerCase();
            if (inputPassword.length() > 20) {
                System.out.println("Размер пароля превысил максимальное количество символов, введите другой пароль");
            } else return inputPassword;
        }
    }

    private void escapeFromLoop() {
        int i = valueVerification.checkInt();

        if(i == 0) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.menu();
        }
    }
}
