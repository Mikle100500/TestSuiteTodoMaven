package com.todomvc;

import com.todomvc.categories.Buggy;
import com.todomvc.helpers.Preconditions;
import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.todomvc.helpers.Preconditions.precondition;


public class TodosOperationsAtAllFilterTest extends BaseTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate() {

        precondition().atAllFilter().prepare();

        page.create("a");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEdit() {

        precondition().activeTasks("a").atAllFilter().prepare();

        page.startEdit("a", "a edited").pressEnter();
        page.assertTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete() {

        precondition().activeTasks("a", "a to delete").atAllFilter().prepare();

        page.delete("a to delete");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testComplete() {

        precondition().activeTasks("a", "b").atAllFilter().prepare();

        page.toggle("a");
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivate() {

        precondition().completedTasks("a").atAllFilter().prepare();

        page.toggle("a");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {

        precondition().activeTasks("a", "b").atAllFilter().prepare();

        page.toggleAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a", "b", "c").atAllFilter().prepare();

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testSwitchToActive() {

        precondition().activeTasks("a").completedTasks("b").activeTasks("c").atAllFilter().prepare();

        page.filterActive();
        page.assertVisibleTasks("a", "c");
        page.assertItemsLeft(2);
    }

    @Test
    public void testSwitchToCompleted() {

        precondition().activeTasks("a", "b").completedTasks("c", "d").atAllFilter().prepare();

        page.filterCompleted();
        page.assertVisibleTasks("c", "d");
        page.assertItemsLeft(2);
    }

    @Test
    @Category(Buggy.class)
    public void testCancelEditByEsc() {

        precondition().completedTasks("a").atAllFilter().prepare();

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditByTab() {

        precondition().activeTasks("a").atAllFilter().prepare();

        page.startEdit("a", "a edited").pressTab();
        page.assertTasks("a edited");
        page.assertItemsLeft(1);
    }
}
