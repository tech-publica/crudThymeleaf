package com.baeldung.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.baeldung.crud.controllers.UserController;
import com.baeldung.crud.entities.User;
import com.baeldung.crud.repositories.UserRepository;

public class UserControllerUnitTest {

    private static UserController userController;
    private static UserRepository mockedUserRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeAll
    public static void setUpUserControllerInstance() {
        mockedUserRepository = mock(UserRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserRepository);
    }

    @Test
    public void whenCalledIndex_thenCorrect() {
        assertThat(userController.showUserList(mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("John", "john@domain.com");

        assertThat(userController.showSignUpForm(user)).isEqualTo("add-user");
    }
    
    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(userController.addUser(user, mockedBindingResult, mockedModel)).isEqualTo("redirect:/index");
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.addUser(user, mockedBindingResult, mockedModel)).isEqualTo("add-user");
    }


    
    @Test
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(userController.updateUser(1l, user, mockedBindingResult, mockedModel)).isEqualTo("redirect:/index");
    }

    @Test
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        User user = new User("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.updateUser(1l, user, mockedBindingResult, mockedModel)).isEqualTo("update-user");
    }

}
