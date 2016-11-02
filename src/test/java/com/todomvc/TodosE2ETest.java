package com.todomvc;

import com.todomvc.categories.Smoke;
import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.todomvc.helpers.Preconditions.precondition;


public class TodosE2ETest extends BaseTest{

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    @Category(Smoke.class)
    public void testTasksCommonFlow() {

        precondition().prepare();

        page.create("a");
        page.assertTasks("a");

        page.filterActive();
        page.assertVisibleTasks("a");

        page.create("b");
        page.toggleAll();
        page.assertNoVisibleTasks();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");

        page.toggle("b");
        page.assertVisibleTasks("a");

        page.clearCompleted();
        page.assertNoVisibleTasks();

        page.filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();
    }
}