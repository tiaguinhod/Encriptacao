package core_package;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int Opcao = 0;

        // Criar o scanner fora do loop
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Gravar Ficheiro");
            System.out.println("2 - Ler Ficheiro");
            System.out.println("3 - Encriptar Ficheiro");
            System.out.println("4 - Desencriptar Ficheiro");
            System.out.println("0 - Terminar");
            System.out.print("\nEscolha (0-4): ");

            // Validar entrada
            if (scanner.hasNextInt()) {
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
                        scanner.close();
                        break;
                    default:
                        System.out.println("\nOpção inválida. Por favor, escolha um número entre 0 e 4.");
                        break;
                }
            } else {
                System.out.println("\nEntrada inválida. Por favor, insira um número entre 0 e 4.");
                scanner.next();
            }
        } while (Opcao != 0);
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
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao gravar no ficheiro: " + e.getMessage());
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
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
        
   
    private static void EncriptarFicheiro() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        int k = 3; // Chave inicial de encriptação
        StringBuilder strFinal = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;
            System.out.println("\nConteúdo do ficheiro:\n");

            // Ler cada linha do ficheiro
            while ((conteudo = reader.readLine()) != null) {
                if (!conteudo.isEmpty()) {

                    conteudo = normalizeString(conteudo);  // Normalizar a string (com minúsculas)

                    // Encriptar cada caractere
                    for (char c : conteudo.toCharArray()) {
                        if (c >= 'a' && c <= 'z') {
                            int dc = c - 'a' + k;
                            dc = dc % 26;
                            strFinal.append((char) (dc + 'a'));
                            k = dc;
                        } else {
                            strFinal.append(c);
                        }
                    }
                }
            }

            System.out.println("Texto encriptado: \n" + strFinal.toString());

            // Gravar o texto encriptado no mesmo ficheiro
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
                writer.write(strFinal.toString());
                System.out.println("Texto encriptado gravado com sucesso no ficheiro!");
            } catch (Exception e) {
                System.out.println("Erro ao gravar o ficheiro: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }

    }

    private static String normalizeString(String input) {
        //Colocar a string em minúsculas
        return input.toLowerCase();
    }

    private static void DesencriptarFicheiro(){
        Scanner sc = new Scanner(System.in);

        // Solicitar o nome e o caminho completo do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        int k = 3; // Chave inicial usada na encriptação
        StringBuilder strFinal = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;

            // Ler cada linha do ficheiro
            while ((conteudo = reader.readLine()) != null) {
                if (!conteudo.isEmpty()) {
                    // Desencriptar cada caractere
                    for (char c : conteudo.toCharArray()) {
                        if (c >= 'a' && c <= 'z') {
                            int dc = c - 'a' - k;
                            if (dc < 0) {
                                dc = dc + 26;
                            }
                            strFinal.append((char) (dc + 'a'));
                            k = c - 'a';
                        } else {
                            strFinal.append(c);
                        }
                    }
                }
            }

        System.out.println("Texto desencriptado: \n" + strFinal.toString());

        // Gravar o texto desencriptado no mesmo ficheiro
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            writer.write(strFinal.toString());
            System.out.println("Texto desencriptado gravado com sucesso no ficheiro!");
        } catch (Exception e) {
            System.out.println("Erro ao gravar o ficheiro: " + e.getMessage());
        }
    } catch (Exception e) {
        System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
    }
    }
}