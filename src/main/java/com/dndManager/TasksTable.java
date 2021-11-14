package com.dndManager;

import java.util.List;

public class TasksTable implements ITable
{
    List<ITaskLine> tasks;

    public TasksTable(List<ITaskLine> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public String getHTMLTables() {
        String ans = "";
        for(var ts: tasks)
        {
            ans+= ts.GetRowHTML();
        }
        return ans;
    }
}
