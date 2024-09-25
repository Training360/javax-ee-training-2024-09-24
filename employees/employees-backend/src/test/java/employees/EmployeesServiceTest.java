package employees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceTest {

    @Mock
    EmployeesDao employeesDao;

    @Spy
    EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @InjectMocks
    EmployeesService employeesService;

    @Test
    void findAll() {
        when(employeesDao.findAll()).thenReturn(List.of(
                new Employee(45L, "John Doe"),
                new Employee(66L, "Jane Doe")
        ));

        var result = employeesService.findAll();
//        assertEquals(List.of("John Doe", "Jane Doe"),
//                result.stream().map(EmployeeDto::name).toList());

        assertThat(result)
                .extracting(EmployeeDto::name, EmployeeDto::id)
                .containsExactlyInAnyOrder(
                        tuple("Jane Doe", 66L),
                        tuple("John Doe", 45L)
                );

        assertThat(result)
                .extracting(EmployeeDto::name)
                .containsExactly("John Doe", "Jane Doe");
    }

    @Test
    @DisplayName("Create employee")
    void create() {
        // Given
        when(employeesDao.create(any())).thenReturn(new Employee(45L, "John Doe"));

        // When
        var result = employeesService.create(new EmployeeDto(null, "John Doe"));

        // Then
//        verify(employeesDao).create(new Employee(null, "John Doe"));
        verify(employeesDao).create(argThat(employee -> employee.getName().equals("John Doe")));
        assertEquals(new EmployeeDto(45L, "John Doe"), result);
    }

    @Test
    @DisplayName("Throws exception when employee exists with same name")
    void createWithExistingName() {
        when(employeesDao.findEmployeeByName(any())).thenReturn(Optional.of(new Employee(66L, "Jack Doe")));

        var e = assertThrows(IllegalArgumentException.class,
                () -> employeesService.create(new EmployeeDto(null, "Jack Doe")));

        assertEquals("Employee already exists with id: 66", e.getMessage());
    }
}