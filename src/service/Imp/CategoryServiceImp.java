package service.Imp;

import controller.personalMenu.partsOfPersonal.CategoryMenu;
import entity.Category;
import entity.User;
import repository.CategoryRepository;
import service.CategoryService;
import verification.UniqVerification;
import verification.ValueVerification;

public class CategoryServiceImp implements CategoryService {

    ValueVerification valueVerification = new ValueVerification();
    UniqVerification uniqVerification = new UniqVerification();

    @Override
    public void addCategory() {
        String inputName = checkNewCategoryName();
        Category newCategory = new Category(inputName);
        CategoryRepository.addCategory(newCategory, User.getId());
        System.out.println("Добавление категории " + inputName + " завершено");
    }

    @Override
    public void updateCategoryByName() {
        String inputName = previewCheckCategory();
        String newName = checkNewCategoryName();
        CategoryRepository.updateCategory(inputName, newName, User.getId());
        System.out.println("Обновление категории завершено");
    }

    @Override
    public void getCategoryByName() {
        String inputName = previewCheckCategory();
        CategoryRepository.getCategoryByName(inputName, User.getId());
    }

    @Override
    public void getAllCategories() {
        if (uniqVerification.checkListIsEmpty("categories", User.getId())) {
            System.out.println("Список пуст!");
        } else {
            CategoryRepository.getAllCategoriesWithTasks(User.getId());
        }
    }

    @Override
    public void removeCategoryByName() {
        String inputName = previewCheckCategory();
        CategoryRepository.removeCategoryByName(inputName, User.getId());
        System.out.println("Удаление завершено");
    }

    @Override
    public void removeAllCategories() {
        if(uniqVerification.checkListIsEmpty("categories", User.getId())) {
            System.out.println("Список пуст! ");
        } else {
            CategoryRepository.removeAllCategories(User.getId());
            System.out.println("Удаление завершено");
        }
    }



    private String previewCheckCategory() {
        if (uniqVerification.checkListIsEmpty("categories", User.getId())) {
            System.out.println("Прости друг, но список пуст, попробуй сначала добавить хоть что-то");
            CategoryMenu categoryMenu = new CategoryMenu();
            categoryMenu.menu();
        } else {
            while (true) {
                System.out.println("Введите название категории: ");
                String inputName = valueVerification.checkString().toLowerCase();
                if (uniqVerification.checkNameUniq(inputName, "categories", User.getId())) {
                    System.out.println("Такая задача отсутствует! ");
                    System.out.println("Если желаете выйти из программы введите 0: ");
                    escapeFromLoop();
                } else {
                    if (inputName.length() > 100) {
                        System.out.println("Ах$ть, серьезно, ты где-нибедь видел категорию с количеством элементов > 100... Пожалуйста введите название поменьше  ^_^");
                    } else return inputName;

                }
            }
        }
        return null;
    }

    private String checkNewCategoryName(){
        int attemptCount = 0;
        while (true) {
            System.out.println("Введите новое название категории");
            String inputName = valueVerification.checkString().toLowerCase();
            if (!uniqVerification.checkNameUniq(inputName, "categories", User.getId())) {
                System.out.println("Введеное вами имя категории уже существует, введите пожалуйста другое");
                System.out.println("Если хотите вернуться в меню введите 0");
                escapeFromLoop();
                attemptCount++;

                /////////////////* ПАСХАЛКА *////////////////////
                if(attemptCount == 10){
                    System.out.println("Может сходишь попьешь чайку и придумаешь наконец новое название, задрал уже");
                }
                if(attemptCount == 15) {
                    CategoryRepository.getAllCategoriesWithTasks(User.getId());
                    System.out.println("Хххх, другалечек, посмотри список имен, которые уже есть... " +
                            "И пожалуйста подумай еще раз ^_^");
                }
                if(attemptCount == 20){
                    System.out.println("ВСЕ... ты перешел черту... ты настолько $$$$ , что не смог придумать " +
                            "название из всех букв алфавита, прости... это программа не для таких как ты...");
                    System.exit(0);
                }
                /////////////////* ПАСХАЛКА *////////////////////

            } else {
                if(inputName.length() > 100) {
                    System.out.println("Воу, серьезно -_- , имя больше 100 элементов, может попробуешь что поменьше," +
                            " это тебе не описание");
                } else return inputName;
            }
        }
    }

    private void escapeFromLoop() {
        int choice = valueVerification.checkInt();
        if (choice == 0) {
            CategoryMenu categoryMenu = new CategoryMenu();
            categoryMenu.menu();
        }
    }
}
