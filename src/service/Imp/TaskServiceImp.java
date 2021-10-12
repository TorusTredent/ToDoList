package service.Imp;

import controller.personalMenu.partsOfPersonal.TaskMenu;
import entity.Status;
import entity.Task;
import entity.User;
import repository.CategoryRepository;
import repository.TaskRepository;
import service.TaskService;
import verification.*;

import java.util.ArrayList;


public class TaskServiceImp implements TaskService {

    ValueVerification valueVerification = new ValueVerification();
    UniqVerification uniqVerification = new UniqVerification();

    @Override
    public void addTask() {
        String newName = inputNewName();
        String newDescription = inputNewDescription().toLowerCase();
        String newStatus = chooseNewStatus();
        System.out.println("Если не хотите добавлять задачу в какую-либо категорию, нажмите 0");
        int selectedCategory = valueVerification.checkInt();
        if (selectedCategory != 0) {
            selectedCategory = chooseCategory(User.getId());
        }
        Task newTask = new Task(newName, newDescription, newStatus, selectedCategory);
        TaskRepository.addTask(newTask, User.getId());
        System.out.println("Добавление задачи выполнено!");
    }

    @Override
    public void updateTaskByName() {
        String inputName = previewCheck();
        String newName = inputNewName();
        String newDescription = inputNewDescription();
        String newStatus = chooseNewStatus();
        int newCategory = chooseCategory(User.getId());
        Task task = new Task(newName, newDescription, newStatus, newCategory);
        TaskRepository.updateTask(inputName, task, User.getId());
        System.out.println("Изменение выполнено! ");

    }

    @Override
    public void updateTaskStatusByName() {
        String inputName = previewCheck();
        String newStatus = chooseNewStatus();
        TaskRepository.updateTaskStatus(inputName, newStatus, User.getId());
        System.out.println("Обновление выполнено!");
    }

    @Override
    public void updateTaskCategoryByName() {
        String inputName = previewCheck();
        int newCategoryId = chooseCategory(User.getId());
        TaskRepository.updateTaskCategory(inputName, newCategoryId, User.getId());
        System.out.println("Обновление завершено");
    }

    @Override
    public void getTaskByName() {
        String inputName = previewCheck();
        TaskRepository.getTaskByName(inputName, User.getId());
        System.out.println("=======================================");
    }

    @Override
    public void getAllTasks() {
        if (uniqVerification.checkListIsEmpty("tasks", User.getId())) {
            System.out.println("Список пуст!");
        } else {
            TaskRepository.getAllTasks(User.getId());
            System.out.println("=======================================");
        }
    }

    @Override
    public void removeTaskByName() {
        String inputName = previewCheck();
        TaskRepository.removeTaskByName(inputName, User.getId());
        System.out.println("Задача удалено!");
    }

    @Override
    public void removeAllTasks() {
        if (uniqVerification.checkListIsEmpty("tasks", User.getId())) {
            System.out.println("Список пуст!");
        } else {
            TaskRepository.removeAllTasks(User.getId());
            System.out.println("Задачи удалены! ");
        }
    }



    private String previewCheck() {
        if (uniqVerification.checkListIsEmpty("tasks", User.getId())) {
            System.out.println("Список пуст!");
            TaskMenu taskMenu = new TaskMenu();
            taskMenu.menu();
        } else {
            while (true) {
                System.out.println("Введите название задачи: ");
                String inputName = valueVerification.checkString().toLowerCase();
                if (uniqVerification.checkNameUniq(inputName, "tasks", User.getId())) {
                    System.out.println("Такого имени не существует! ");
                    System.out.println("Хотите вернуться в меню?" + " введите 1 - да, 2 - нет");
                    escapeFromLoop();
                } else {
                    return inputName;
                }
            }
        }
        return null;
    }

    private String inputNewName() {
        while (true) {
            System.out.println("Введите новое название задачи: ");
            String newName = valueVerification.checkString().toLowerCase();
            if (!uniqVerification.checkNameUniq(newName, "tasks", User.getId())) {
                System.out.println("Такое имя уже существует! ");
                System.out.println("Если хотите вернуться в меню введите 0:");
                escapeFromLoop();
            } else if (newName.length() > 100) {
                System.out.println("Вы превысили максимальную длину строки, введите описание в пределах 100 символов");
            } else return newName;
        }
    }

    private String inputNewDescription() {
        while (true) {
            System.out.println("Введите новое описание задачи: ");
            String newDescription = valueVerification.checkString();
            if (newDescription.length() > 255) {
                System.out.println("Вы превысили максимальную длину строки, введите описание в пределах 255 символов ");
            } else return newDescription;
        }
    }

    private String chooseNewStatus() {
        System.out.println("Выберите статус: ");
        System.out.println("1) планируется");
        System.out.println("2) выполняется");
        System.out.println("3) закончено");
        int choose;
        do {
            choose = valueVerification.checkInt();
        } while (choose != 1 && choose != 2 && choose != 3);

        switch (choose) {
            case 1: {
                return String.valueOf(Status.PLANNING).toLowerCase();
            }
            case 2: {
                return String.valueOf(Status.PROCESSING).toLowerCase();
            }
            case 3: {
                return String.valueOf(Status.FINISHED).toLowerCase();
            }
        }
        return null;
    }

    private int chooseCategory(int userId) {
        ArrayList<Integer> listOfNumbersLines = CategoryRepository.getAllCategoriesAndNumberLines(userId);
        if (listOfNumbersLines.isEmpty()) {
            System.out.println("Список категорий пуст :(");
            return 0;
        }
        int categoryId;
        System.out.println("Выберите 1 из категорий: ");
        while (true) {
            categoryId = valueVerification.checkInt() - 1;
            if (categoryId < listOfNumbersLines.size() && categoryId >= 0) {
                return listOfNumbersLines.get(categoryId);
            }
            System.out.println("Такой категории нет!");
        }
    }

    private void escapeFromLoop() {
        int choice = valueVerification.checkInt();
        if (choice == 1) {
            TaskMenu taskMenu = new TaskMenu();
            taskMenu.menu();
        }
    }
}
