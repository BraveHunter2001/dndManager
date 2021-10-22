package com.dndManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTablesInfo implements IDBTablesInfo{
    DBFacade db = DBFacade.getInstance();

    public static DBTablesInfo getInstance()
    {
        if(instance == null) {
            instance = new DBTablesInfo();
        }
        return instance;
    }
    private static DBTablesInfo instance;
    @Override
    public List<ICharacterLine> GetCharactersALL() {
        try
        {
            var chrs = new ArrayList<ICharacterLine>();
            var res = db.ExecuteQuery("Select * FROM characters ORDER BY id ASC");

            while (res.next())
            {
                chrs.add( new CharacterLine(res.getInt("id"),
                        res.getString("name"),
                        res.getString("apperance"),
                        res.getString("location"),
                        res.getString("meetingstatus")
                        ));
            }
            return chrs;
        }catch (DBFacade.DBNotConnectedException | SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ITaskLine> GetTasksByCharacterName(String nameCharacter) {
        try
        {
            var tasks = new ArrayList<ITaskLine>();
            var res = db.ExecuteQuery("SELECT t.id, t.name, t.taskstatus\n" +
                    "FROM characters_tasks \n" +
                    "JOIN characters as ch\n" +
                    "\ton characters_tasks.character_id = ch.id\n" +
                    "JOIN tasks as t\n" +
                    "\ton characters_tasks.task_id = t.id\n" +
                    "WHERE ch.name = '"+ nameCharacter +"'");

            while (res.next())
            {
                tasks.add( new TaskLine(res.getInt("id"),
                        res.getString("name"),
                        res.getString("taskstatus")
                ));
            }
            return tasks;
        }catch (DBFacade.DBNotConnectedException | SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<ITaskLine> GetTasksALL() {
        return null;
    }
}
