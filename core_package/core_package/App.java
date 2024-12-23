  package core_package;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Opcao;

        
            System.out.println("1 - Gravar Ficheiro");
            System.out.println("2 - Ler Ficheiro");
            System.out.println("3 - Encriptar Ficheiro");
            System.out.println("4 - Desencriptar Ficheiro");
            System.out.println("0 - Terminar");
            System.out.print("\nEscolha (0,4): ");

            Opcao = scanner.nextInt();

            switch (Opcao) {
                case 1:
                    GravarFicheiro();
                    break;
                case 2:
                    LerFicheiro();
                    break;
                case 3:
                    EncriptarFicheiro();
                    break;
                case 4:
                    DesencriptarFicheiro();
                    break;
                case 0:
                    System.out.println("\nA Encerrar Programa...");
                    if (Opcao !=0){
                    } else {
                        scanner.close();
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
    }

    //===========================================================

    public static void GravarFicheiro() {
        Scanner sc = new Scanner(System.in);

        // Solicitar o nome e o caminho completo do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        // Utilizar try-with-resources para garantir que o recurso será fechado
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            System.out.println("Introduza o texto a gravar no ficheiro (ENTER para terminar):");
            
            String conteudo;
            while (!(conteudo = sc.nextLine()).isEmpty()) { // Enquanto a linha não estiver vazia
                writer.write(conteudo);
                writer.newLine(); // Adiciona uma nova linha no ficheiro
            }

            System.out.println("Texto gravado com sucesso no ficheiro!");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao gravar no ficheiro: " + e.getMessage());
        } finally {
            sc.close(); // Fechar o Scanner
        }
    }

    private static void LerFicheiro(){
        Scanner sc = new Scanner(System.in);
    
        // Solicitar o nome e o caminho completo do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();
        
        // Tentar abrir e ler o ficheiro
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;
            System.out.println("\nConteúdo do ficheiro:\n");
            while ((conteudo = reader.readLine()) != null) {
                System.out.println(conteudo);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        sc.close();
    }
        
    private static void EncriptarFicheiro(){
        
    }

    private static void DesencriptarFicheiro(){
        
    }
}

