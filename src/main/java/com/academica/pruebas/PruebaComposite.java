package com.academica.pruebas;

import com.academica.composite.Curso;
import com.academica.composite.ElementoAcademico;
import com.academica.composite.Estudiante;
import com.academica.composite.Facultad;
import com.academica.composite.Programa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Patron Composite - Jerarquia academica")
public class PruebaComposite {

    private Estudiante ana;
    private Estudiante luis;
    private Estudiante maria;
    private Curso calculo;

    @BeforeEach
    void setUp() {
        ana    = new Estudiante("Ana Torres",   4.5);
        luis   = new Estudiante("Luis Gomez",   3.0);
        maria  = new Estudiante("Maria Ruiz",   3.5);
        calculo = new Curso("Calculo I");
        calculo.agregar(ana);
        calculo.agregar(luis);
        calculo.agregar(maria);
    }

    // --- Pruebas del Composite a nivel Curso ---

    @Test
    @DisplayName("Promedio de un curso con 3 estudiantes")
    void promedioTresEstudiantes() {
        double esperado = (4.5 + 3.0 + 3.5) / 3.0;
        assertEquals(esperado, calculo.getPromedio(), 1e-6,
                "El promedio del curso debe ser la media aritmetica de los promedios");
    }

    @Test
    @DisplayName("Total de estudiantes en un curso")
    void totalEstudiantesCurso() {
        assertEquals(3, calculo.getTotalEstudiantes());
    }

    @Test
    @DisplayName("Estudiante (hoja) retorna promedio individual")
    void promedioHoja() {
        assertEquals(4.5, ana.getPromedio(), 1e-6);
        assertEquals(1, ana.getTotalEstudiantes());
    }

    // --- Pruebas del Composite a nivel Programa ---

    @Test
    @DisplayName("Promedio de un programa con 2 cursos")
    void promedioProgramaDosCursos() {
        Curso algebra = new Curso("Algebra Lineal");
        algebra.agregar(new Estudiante("Pedro Silva",  3.8));
        algebra.agregar(new Estudiante("Sofia Mora",   4.2));
        algebra.agregar(new Estudiante("Tomas Castro", 3.6));

        Programa sistemas = new Programa("Ingenieria de Sistemas");
        sistemas.agregar(calculo);
        sistemas.agregar(algebra);

        double esperado = (calculo.getPromedio() + algebra.getPromedio()) / 2.0;
        assertEquals(esperado, sistemas.getPromedio(), 1e-6,
                "El promedio del programa debe ser la media de los promedios de sus cursos");
    }

    @Test
    @DisplayName("Total de estudiantes en un programa con 2 cursos")
    void totalEstudiantesPrograma() {
        Curso algebra = new Curso("Algebra Lineal");
        algebra.agregar(new Estudiante("Pedro Silva",  3.8));
        algebra.agregar(new Estudiante("Sofia Mora",   4.2));

        Programa sistemas = new Programa("Ingenieria de Sistemas");
        sistemas.agregar(calculo);
        sistemas.agregar(algebra);

        assertEquals(5, sistemas.getTotalEstudiantes());
    }

    // --- Pruebas del Composite a nivel Facultad ---

    @Test
    @DisplayName("Promedio de una facultad con 2 programas")
    void promedioFacultadDosProgramas() {
        Programa sistemas  = new Programa("Ingenieria de Sistemas");
        sistemas.agregar(calculo);

        Curso fisica = new Curso("Fisica I");
        fisica.agregar(new Estudiante("Carmen Vega",  4.0));
        fisica.agregar(new Estudiante("Jorge Perez",  3.2));
        Programa electronica = new Programa("Ingenieria Electronica");
        electronica.agregar(fisica);

        Facultad ingenieria = new Facultad("Facultad de Ingenieria");
        ingenieria.agregar(sistemas);
        ingenieria.agregar(electronica);

        double esperado = (sistemas.getPromedio() + electronica.getPromedio()) / 2.0;
        assertEquals(esperado, ingenieria.getPromedio(), 1e-6,
                "El promedio de la facultad debe ser la media de sus programas");
    }

    @Test
    @DisplayName("Total de estudiantes a nivel Facultad (acumulacion recursiva)")
    void totalEstudiantesFacultad() {
        Programa sistemas = new Programa("Ingenieria de Sistemas");
        sistemas.agregar(calculo);   // 3 estudiantes

        Curso fisica = new Curso("Fisica I");
        fisica.agregar(new Estudiante("Carmen Vega", 4.0));
        fisica.agregar(new Estudiante("Jorge Perez", 3.2));
        Programa electronica = new Programa("Ingenieria Electronica");
        electronica.agregar(fisica); // 2 estudiantes

        Facultad ingenieria = new Facultad("Facultad de Ingenieria");
        ingenieria.agregar(sistemas);
        ingenieria.agregar(electronica);

        assertEquals(5, ingenieria.getTotalEstudiantes(),
                "La facultad debe acumular todos los estudiantes recursivamente");
    }

    @Test
    @DisplayName("Cliente trata hoja y composites con el mismo contrato")
    void clienteUniforme() {
        ElementoAcademico[] elementos = { ana, calculo };
        for (ElementoAcademico e : elementos) {
            assertTrue(e.getPromedio() >= 0.0);
            assertTrue(e.getTotalEstudiantes() >= 1);
        }
    }
}
