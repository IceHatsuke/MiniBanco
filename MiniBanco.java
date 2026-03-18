
import java.util.Scanner;

public class MiniBanco {

    static final double LIMITE_SAQUE = 1000.00;
    static final double TAXA_DE_SAQUE = 0.02;

    static double clacularTotalSaque(double valor){
        return valor + (valor * TAXA_DE_SAQUE);
    }

    static boolean dentroDoLimite(double valor){
        return valor <= LIMITE_SAQUE;
    }

    static boolean saldoSuficiente(double saldo, double valor){
        return saldo >= clacularTotalSaque(valor);
    }

    static double sacar(double saldo, double valor){
        return saldo - clacularTotalSaque(valor);
    }

    static void exibirMenu(){
        System.out.println("\n=== MiniBanco ===");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Consultar saldo");
        System.out.println("4 - Ver extrato");
        System.out.println("0 - Sair");
        System.out.println("Escolha: ");
    }

    static double depositar(double saldo, double valor){
        return saldo + valor;
    }


    static void exibirSaldo(double saldo){
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    static boolean valorValido(double valor){
        return valor > 0;
    }

    static void exibirExtrato(String[] extrato, int totalLinhas){
        System.out.println("\n--Extrato---------------");
        if(totalLinhas == 0){
            System.out.println("Nenhuma movimentação");
        }
        else{
            for(int i = 0; i < totalLinhas; i++){
                System.out.println("" + extrato[i]);
            }
        }
        System.out.println("-----------------------");
    }

    static int registrar(String[] extrato, int totalLinhas, String linha){
        extrato[totalLinhas] = linha;
        return totalLinhas + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] extrato = new String[50];
        int      totalLinhas = 0;

        double saldo  = 0.0;
        int    opcao  = -1;

        System.out.println("Digite o seu nome: ");
        String nome = scanner.next();
        System.out.printf("Olá, %s! Saldo inicial: R$ %.2f\n", nome, saldo);

        

        while (opcao != 0)
        {
            exibirMenu();
            opcao = scanner.nextInt();
            if(opcao == 1){
                System.out.println("Valor a depositar: R$ ");
                double valor = scanner.nextDouble();
                if(!valorValido(valor)){
                    System.out.println("Atenção, valor invalido. Valor deve ser maior que 0!");
                }
                else{
                    saldo = depositar(saldo,valor);
                    System.out.println("Deposito Realizado");
                    exibirSaldo(saldo);
                    totalLinhas = registrar(extrato, totalLinhas, String.format("DEPÓSITO +R$ %.2f => Saldo R$ %.2f", valor, saldo));
                }

                //System.out.println(" [Depositar - em breve]");
            }
            else if(opcao == 2){
                System.out.println("Valor a sacar: R$");
                double valorSaque = scanner.nextDouble();
                if(!valorValido(valorSaque)){
                    System.out.println("Atenção valor invalido");
                }
                else if(!dentroDoLimite(valorSaque)){
                    System.out.printf("Limite exedido. Maximo: R$ %.2f%n", LIMITE_SAQUE);
                }
                else if(!saldoSuficiente(saldo, valorSaque)){
                    System.out.printf("Saldo insuficiente. Necessario: R$ %.2f%n", clacularTotalSaque(valorSaque));
                }
                else{
                    double taxa = valorSaque * TAXA_DE_SAQUE;
                    saldo = sacar(saldo, valorSaque);
                    System.out.printf("Saque realizado. Taxa cobrada: R$ %.2f%n", taxa);
                    totalLinhas = registrar(extrato, totalLinhas, String.format("SAQUE -R$ %.2f => Saldo: R$ %.2f", valorSaque, saldo));
                }
            }
            else if(opcao == 3){
                exibirSaldo(saldo);
            }
            else if(opcao == 4){
                exibirExtrato(extrato, totalLinhas);
                //System.out.println(" [Extrato - em breve]");
            }
            else if(opcao == 0){
                exibirExtrato(extrato, totalLinhas);
                System.out.println("Até logo, " + nome + "!");
            }
            else{
                System.out.println("Opção invalida.");
            }
        }
    }
}

