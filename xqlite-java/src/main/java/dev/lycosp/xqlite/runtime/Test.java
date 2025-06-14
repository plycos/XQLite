package dev.lycosp.xqlite.runtime;

public class Test {
    public static void main(String[] args) {
        final QuerySpec querySpec = QuerySpec.of("select * from dual", 1, 2, 3);
        System.out.println(querySpec);
    }
}
