package com.example.questv3;

import junit.framework.TestCase;

public class QuestItemTest extends TestCase {
    private QuestItem quest1;
    private QuestItem quest2;
    //setup empty quest item
    protected void setUp() {
        quest1 = new QuestItem("", "", "", 0);
        quest1.setTitle("Do the laundry");
        quest1.setContent("Get the laundry done by tomorrow");
        quest1.setDate("2019 November 11");
        quest1.setIcon(1);

        quest2 = new QuestItem("", "", "", 0);
        quest2.setTitle("Dust the house");
        quest2.setContent("Dust the living room");
        quest2.setDate("2019 November 12");
        quest2.setIcon(2);
    }

    //tests all the getters
    public void testGetTitle(){
        assertEquals("Do the laundry", quest1.getTitle());
    }

    public void testGetTitleFalse() {assertEquals("Dust the house", quest1.getTitle());}

    public void testGetTitle2(){
        assertEquals("Dust the house", quest2.getTitle());
    }

    public void testGetContent(){ assertEquals("Get the laundry done by tomorrow", quest1.getContent());}

    public void testGetContentFalse(){ assertEquals("Dust the living room", quest1.getContent());}

    public void testGetContent2(){ assertEquals("Dust the living room", quest2.getContent());}

    public void testGetDate(){
        assertEquals("2019 November 11", quest1.getDate());
    }

    public void testGetDateFalse(){
        assertEquals("2019 November 12", quest1.getDate());
    }

    public void testGetDate2(){
        assertEquals("2019 November 12", quest2.getDate());
    }

    public void testGetIcon(){
        assertEquals(1, quest1.getIcon());
    }

    public void testGetIconFalse(){  assertEquals(0,quest1.getIcon()); }

    public void testGetIcon2(){
        assertEquals(2, quest2.getIcon());
    }

    public void testToString(){ assertEquals("Quest[title: Do the laundry, date: 2019 November 11, img: 1]", quest1.toString());}

    public void testToStringFalse(){ assertEquals("", quest1.toString());}

    public void testToString2(){ assertEquals("Quest[title: Dust the house, date: 2019 November 12, img: 2]", quest2.toString());}
}
