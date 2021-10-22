package com.dndManager;

public class TaskLine implements ITaskLine{
    int id;
    String name, taskStatus;

    public TaskLine(int id, String name, String taskStatus )
    {
        this.id = id;
        this.name = name;
        this.taskStatus = taskStatus;
    }
    @Override
    public int GetId() {
        return id;
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public String GetStatus() {
        return taskStatus;
    }

    @Override
    public String GetRowHTML() {
       return "<tr>" +
                "<td>"+id+"</td>"+
                "<td>"+name+"</td>"+
                "<td>"+taskStatus+"</td>"+
                "</tr>";
    }
}
