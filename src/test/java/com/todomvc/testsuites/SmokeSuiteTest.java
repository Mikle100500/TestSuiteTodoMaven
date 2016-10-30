package com.todomvc.testsuites;

import com.todomvc.TodosE2ETest;
import com.todomvc.categories.Buggy;
import com.todomvc.categories.Smoke;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses(TodosE2ETest.class)
@Categories.IncludeCategory(Smoke.class)
@Categories.ExcludeCategory(Buggy.class)
public class SmokeSuiteTest {
}