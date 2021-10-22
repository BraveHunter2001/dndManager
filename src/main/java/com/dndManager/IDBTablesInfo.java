package com.dndManager;

import java.util.List;

public interface IDBTablesInfo {
    List<ICharacterLine> GetCharactersALL();
    List<ITaskLine> GetTasksByCharacterName(String nameCharacter);
    List<ITaskLine> GetTasksALL();
}
