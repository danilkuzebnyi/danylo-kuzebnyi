package task1;

import java.time.LocalDateTime;
import java.util.*;

public class TaskManagerImpl implements TaskManager {
    Set<String> categories = new LinkedHashSet<>();
    Map<LocalDateTime, String> categoriesByDate = new TreeMap<>();
    List<Task> listTasksByDate = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    Map<LocalDateTime, List<Task>> tasksByDate = new TreeMap<>();
    Map<String, List<Task>> tasksByCategories = new LinkedHashMap<>();

    @Override
    public void add(LocalDateTime date, Task task) {
        categoriesByDate.put(date, task.getCategory());

        if (tasksByDate.containsKey(date)) {
            listTasksByDate = tasksByDate.get(date);
            listTasksByDate.add(task);
            tasksByDate.put(date, listTasksByDate);
        } else {
            List<Task> listTasksByDate = new ArrayList<>();
            listTasksByDate.add(task);
            tasksByDate.put(date, listTasksByDate);
        }
    }

    public void addTasksToCategories() {
        for (LocalDateTime dateTime : tasksByDate.keySet()) {
            for (int i = 0; i < tasksByDate.get(dateTime).size(); i++) {
                if (tasksByCategories.containsKey(tasksByDate.get(dateTime).get(i).getCategory())) {
                    tasks = tasksByCategories.get(tasksByDate.get(dateTime).get(i).getCategory());
                    tasks.add(tasksByDate.get(dateTime).get(i));
                    tasksByCategories.put(tasksByDate.get(dateTime).get(i).getCategory(), tasks);
                } else {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(tasksByDate.get(dateTime).get(i));
                    tasksByCategories.put(tasksByDate.get(dateTime).get(i).getCategory(), tasks);
                }
            }
        }
    }

    @Override
    public void remove(LocalDateTime date) {
        tasksByDate.remove(date);
    }

    @Override
    public Set<String> getCategories() {
        for (LocalDateTime dateTime : categoriesByDate.keySet()) {
            categories.add(categoriesByDate.get(dateTime));
        }
        return categories;
    }

    @Override
    public Map<String, List<Task>> getTasksByCategories(String... categories) {
        Map<String, List<Task>> tasksByFewCategories = new LinkedHashMap<>();
        Map<String, List<Task>> tasksByFewCategoriesSortedByDate = new LinkedHashMap<>();

        for (String category : categories) {
            tasksByFewCategories.put(category, tasksByCategories.get(category));
        }

        for (String key : tasksByCategories.keySet()) {
            if (tasksByFewCategories.containsKey(key)) {
                tasksByFewCategoriesSortedByDate.put(key, tasksByCategories.get(key));
            }
        }
        return tasksByFewCategoriesSortedByDate;
    }

    @Override
    public List<Task> getTasksByCategory(String category) {
        return tasksByCategories.get(category);
    }

    @Override
    public List<Task> getTasksForToday() {
        return tasksByDate.get(LocalDateTime.of(2021, 12, 6, 20, 0));
    }
}
