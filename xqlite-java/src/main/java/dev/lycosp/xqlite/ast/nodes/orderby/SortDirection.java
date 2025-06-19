package dev.lycosp.xqlite.ast.nodes.orderby;

public enum SortDirection {
    ASC {
        @Override
        public String toString() {
            return "ASC";
        }
    },
    DESC {
        @Override
        public String toString() {
            return "DESC";
        }
    }
}
