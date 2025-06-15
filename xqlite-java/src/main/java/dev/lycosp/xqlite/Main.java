package dev.lycosp.xqlite;

import dev.lycosp.xqlite.api.SelectQuery;

import static dev.lycosp.xqlite.api.XQLite.select;
import static dev.lycosp.xqlite.api.XQLite.cols;
import static dev.lycosp.xqlite.api.XQLite.from;

public class Main {
    public static void main(String[] args) {
        SelectQuery selectQuery = select(
                cols("col1", "col2", "col3"),
                from("table")
        );
        System.out.println(selectQuery.render());
    }
}
