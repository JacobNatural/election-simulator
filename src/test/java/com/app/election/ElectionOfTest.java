package com.app.election;

import com.app.elections.Elections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.app.data_provider.DataProvider.CANDIDATE1;
import static com.app.data_provider.DataProvider.VOTER1;

public class ElectionOfTest {

    @Test
    @DisplayName("When candidates are null")
    public void test1(){
        Assertions.assertThatThrownBy(
                () -> Elections.of(null, Set.of(VOTER1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Candidates cannot be null");
    }

    @Test
    @DisplayName("When candidates are empty")
    public void test2(){
        Assertions.assertThatThrownBy(
                        () -> Elections.of(Set.of(), Set.of(VOTER1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Candidates cannot be empty");
    }

    @Test
    @DisplayName("When voters are null")
    public void test3(){
        Assertions.assertThatThrownBy(
                        () -> Elections.of(Set.of(CANDIDATE1), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Voters cannot be null");
    }

    @Test
    @DisplayName("When voters are empty")
    public void test4(){
        Assertions.assertThatThrownBy(
                        () -> Elections.of(Set.of(CANDIDATE1), Set.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Voters cannot be empty");
    }

    @Test
    @DisplayName("When a elections is correctly initialized")
    public void test5(){
        Assertions.assertThatNoException().isThrownBy(
                () -> Elections.of(Set.of(CANDIDATE1), Set.of(VOTER1))
        );
    }
}
