package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.FuncionarioDTO;
import model.Funcionario;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuncionarioService {

    public static List<Funcionario> carregarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = FuncionarioService.class
                    .getClassLoader()
                    .getResourceAsStream("funcionarios.json");

            FuncionarioDTO[] lista = mapper.readValue(is, FuncionarioDTO[].class);

            Arrays.stream(lista).forEach(dto -> {
                funcionarios.add(new Funcionario(
                        dto.nome,
                        LocalDate.parse(dto.dataNascimento),
                        dto.salario,
                        dto.funcao
                ));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}