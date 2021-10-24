package com.dndManager;

import java.util.List;

public class TasksTable implements ITablesHTML
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
