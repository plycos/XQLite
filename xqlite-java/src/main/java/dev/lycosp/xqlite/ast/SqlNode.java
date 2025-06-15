package dev.lycosp.xqlite.ast;

public interface SqlNode {
    <R> R accept(SqlVisitor<R> visitor);
}
