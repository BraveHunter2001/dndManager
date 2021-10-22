package com.dndManager;

public class CharacterLine implements ICharacterLine{
    int id;
    String name, appearance, location, meetingStatus;

    public CharacterLine(int id, String name,String appearance, String location, String meetingStatus )
    {
        this.id = id;
        this.name = name;
        this.appearance = appearance;
        this.location = location;
        this.meetingStatus = meetingStatus;
    }

    public String GetRowHTML()
    {
        return "<tr>" +
                "<td>"+id+"</td>"+
                "<td>"+name+"</td>"+
                "<td>"+appearance+"</td>"+
                "<td>"+location+"</td>"+
                "<td>"+meetingStatus+"</td>"+
                "</tr>";
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
    public String GetApperance() {
        return appearance;
    }

    @Override
    public String GetLocation() {
        return location;
    }

    @Override
    public String GetStatus() {
        return meetingStatus;
    }
}
