package com.huivip.steel.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class GroupWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addGroup() {
        beginAt("/groupform");
        assertTitleKeyMatches("groupDetail.title");
        clickButton("save");
        assertTitleKeyMatches("groupList.title");
        assertKeyPresent("group.added");
    }

    @Test
    public void listGroups() {
        beginAt("/groups");
        assertTitleKeyMatches("groupList.title");

        // check that table is present
        assertTablePresent("groupList");
    }

    @Test
    public void editGroup() {
        beginAt("/groupform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("groupDetail.title");
    }

    @Test
    public void saveGroup() {
        beginAt("/groupform?id=" + getInsertedId());
        assertTitleKeyMatches("groupDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("groupDetail.title");
        assertKeyPresent("group.updated");
    }

    @After
    public void removeGroup() {
        beginAt("/groupform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("groupList.title");
        assertKeyPresent("group.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/groups");
        assertTablePresent("groupList");
        Table table = getTable("groupList");
        // Find link in last row, skip header row
        for (int i = 1; i < table.getRows().size(); i++) {
            Row row = table.getRows().get(i);
            if (i == table.getRowCount() - 1) {
                return row.getCells().get(0).getValue();
            }
        }
        return "";
    }

    private void assertTitleKeyMatches(String title) {
        assertTitleEquals(messages.getString(title) + " | " + messages.getString("webapp.name"));
    }
}
