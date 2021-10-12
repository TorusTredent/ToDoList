package service;

public interface TaskService {
    void addTask();
    void updateTaskByName();
    void updateTaskStatusByName();
    void updateTaskCategoryByName();
    void getTaskByName();
    void getAllTasks();
    void removeTaskByName();
    void removeAllTasks();
}
