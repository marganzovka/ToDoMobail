package com.example.tasksjava;

public class Const {
    public static final String HELPER_TASK = "Task";
    public static final String HELPER_ECUTED = "Ecuted";

    public static final String NAME_DATABASE_TASKECUTED = "ecutedTasks";
    public static final String NAME_DATABASE_TASK = "tasks";

    public static final String NAME_COLUMN = "task";

    public static final String TOAST_NOTNULL_EN = "Fill in the field";
    public static final String TOAST_NOTNULL_RU = "Заполните поле";

    public static final String ALERT_NAME_EN = "Do you want to delete it?";
    public static final String ALERT_NAME_RU = "Хотите удалить?";

    public static final String NO_BUTTON_EN = "No";
    public static final String YES_BUTTON_EN = "Yes";

    public static final String NO_BUTTON_RU = "Да";
    public static final String YES_BUTTON_RU = "Нет";

    public static final String CREATE_TASKS_TABLE = "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY, task TEXT)";
    public static final String DROP_TASK_TABLE = "DROP TABLE tasks";
    public static final String SELECT_TASK_TABLE = "SELECT * FROM tasks";

    public static final String CREATE_ECUTED_TABLE = "CREATE TABLE IF NOT EXISTS ecutedTasks (id INTEGER PRIMARY KEY, task TEXT)";
    public static final String DROP_ECUTED_TABLE = "DROP TABLE ecutedTasks";
    public static final String SELECT_ECUTED_TABLE = "SELECT * FROM ecutedTasks";
    public static final String ENGLISH_LANGUAGE = "Settings";




}
