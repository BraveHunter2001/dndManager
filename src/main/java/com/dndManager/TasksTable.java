package com.dndManager;

import java.util.List;

public class TasksTable implements ITables
{
    List<ITaskLine> tasks;

    public TasksTable(List<ITaskLine> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public String getHTMLTables() {
        String ans = "";
        ans += "<table>";
        ans += "<tr>" +
                "<th>Id</th>" +
                "<th>Name</th>"+
                "<th>Meet</th>"+
                "</tr>";
        for(var ts: tasks)
        {
            ans+= ts.GetRowHTML();
        }
        ans += "</table>";
        return ans;
    }
}
