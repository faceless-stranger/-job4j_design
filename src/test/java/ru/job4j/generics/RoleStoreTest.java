package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Programmer"));
        Role result = store.findById("2");
        assertThat(result.getRoleName()).isEqualTo("Programmer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Programmer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsHR() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("HR");
    }

    @Test
    void whenReplaceThenRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Programmer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        boolean rsl = store.replace("10", new Role("10", "Programmer"));
        assertThat(rsl).isFalse();
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("HR");
        result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsHR() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("HR");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        boolean rsl = store.replace("1", new Role("1", "Programmer"));
        assertThat(rsl).isTrue();
        assertThat(store.findById("1").getRoleName()).isEqualTo("Programmer");
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        boolean rsl = store.replace("10", new Role("10", "Programmer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "HR"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}