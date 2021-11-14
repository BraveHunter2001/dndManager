package com.dndManager;

import java.util.List;

public class CharactersTable implements ITable {
    List<ICharacterLine> characters;

    public CharactersTable(List<ICharacterLine> characters)
    {
        this.characters = characters;
    }

    @Override
    public String getHTMLTables() {
        String ans = "";

        for(var ch: characters)
        {
            ans+= ch.GetRowHTML();
        }

        return ans;
    }
}
