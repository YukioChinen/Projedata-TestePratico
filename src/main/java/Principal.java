import model.Funcionario;
import service.FuncionarioService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {

        // 3.1 Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = FuncionarioService.carregarFuncionarios();

        // 3.2 Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 Imprimir todos os funcionários com todas suas informações, sendo que:

        // • informação de data deve ser exibido no formato dd/mm/aaaa;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

        System.out.println("=== Funcionários ===");
        for (Funcionario f : funcionarios) {
            String data = f.getDataNascimento().format(formatter);
            String salario = nf.format(f.getSalario());

            System.out.println(
                    f.getNome() + " | " +
                    data + " | " +
                    salario + " | " +
                    f.getFuncao()
            );
        }

        // 3.4 Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")));
        }

        // 3.5 Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> agrupados =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir os funcionários, agrupados por função.
        System.out.println("\n=== Agrupados por função ===");
        agrupados.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\n=== Aniversariantes mês 10 e 12 ===");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                             f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));

        // 3.9 Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        // obs: cria uma lista de todos os funcionários mais velhos, caso haja mais de um com a mesma data de nascimento.
        LocalDate menorData = funcionarios.stream()
            .map(Funcionario::getDataNascimento)
            .min(LocalDate::compareTo)
            .get();

        List<Funcionario> maisVelhos = funcionarios.stream()
            .filter(f -> f.getDataNascimento().equals(menorData))
            .toList();

        LocalDate hoje = LocalDate.now();
        int idade = Period.between(menorData, hoje).getYears();

        System.out.println("\nMais velho(s):");

        maisVelhos.forEach(f ->
            System.out.println(f.getNome() + " - " + idade + " anos")
        );

        // 3.10 Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\n=== Ordem alfabética ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        // 3.11 Imprimir o total dos salários dos funcionários.
        BigDecimal total = BigDecimal.ZERO;

        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }
        System.out.println("Total salários: " + nf.format(total));

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n=== Salários mínimos ===");
        for (Funcionario f : funcionarios) {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd + " salários mínimos");
        }
    }
}