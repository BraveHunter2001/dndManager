package com.dndManager;

import java.util.List;

public class CharactersTable implements ITables{
    List<ICharacterLine> characters;

    public CharactersTable(List<ICharacterLine> characters)
    {
        this.characters = characters;
    }

    @Override
    public String getHTMLTables() {
        String ans = "";
        ans += "<table>";
        ans += "<tr>" +
                "<th>Id</th>" +
                "<th>Name</th>"+
                "<th>Apperance</th>"+
                "<th>Location</th>"+
                "<th>Meet</th>"+
                "</tr>";
        for(var ch: characters)
        {
            ans+= ch.GetRowHTML();
        }
        ans += "</table>";
        return ans;
    }
}
